package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.ExportModel;
import Model.UsersModel;

public class ExportDAO {
	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	private ResultSet rs2;
	private ResultSet rs3;

	private LoginDAO loginDAO = new LoginDAO();
	
	private AmountDAO amountDAO = new AmountDAO();

	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from export where user_id = ?";

			pt = conn.prepareStatement(countSql);
			pt.setInt(1, UsersModel.user.getId());
			rs = pt.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
			} else {
				return result;
			}

			String sql = "select day, price, ex.type, memo " + " from users u " + " join export e on e.user_id = u.id "
					+ " join extype ex on ex.id = e.type_id " + " where u.user_id = ? order by day desc";
			pt = conn.prepareStatement(sql);
			pt.setString(1, UsersModel.user.getUser_id());
			ResultSet rs = pt.executeQuery();

			result = new String[row][4];

			int index = 0;

			while (rs.next()) {
				result[index][0] = rs.getString("day");
				int price = rs.getInt("price");
				String realprice = String.format("%,d원", price);
				result[index][1] = realprice;
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			rs.close();
			pt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 지출 내역 추가
	public boolean add(ExportModel exportModel) {
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "insert into export(user_id, price, day, type_id, memo) values(?, ?, ?, ?, ?)";

		try {

			pt = conn.prepareStatement(sql);
			pt.setInt(1, exportModel.getId());
			pt.setInt(2, exportModel.getPrice());
			pt.setString(3, exportModel.getDay());
			pt.setInt(4, exportModel.getType_id());
			pt.setString(5, exportModel.getMemo());

			int num = pt.executeUpdate();

			if (num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(ExportModel exportModel) {
		int sqlnum = 0;
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id=?";
		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, exportModel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			rs = pt.executeQuery();

			if (rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			String sql1 = "update export set price=?, day=?, type_id=?, memo=? where user_id=? and id=?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportModel.getPrice());
			pt.setString(2, exportModel.getDay());
			pt.setInt(3, exportModel.getType_id());
			pt.setString(4, exportModel.getMemo());
			pt.setInt(5, exportModel.getId());
			pt.setInt(6, sqlnum);

			int num = pt.executeUpdate();

			if (num > 0) {

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean delete(ExportModel exportmodel) {
		int sqlnum = 0;
		boolean result = false;
		
		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id = ?";
		
		try {
			
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, exportmodel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			rs = pt.executeQuery();
			
			if(rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			
			String sql1 = "delete export where user_id = ? and id = ?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportmodel.getId());
			pt.setInt(2, sqlnum);
			
			int num = pt.executeUpdate();
			
			if(num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 고정지출 날짜 비교
	public void check() {
		String today = LocalDate.now().toString();
		today = today.replace('-','/');
		today = today.substring(2, 10);
		
		String checkday = "";
		int price = 0;
		String memo = "";
		
		conn = DBUtil.getConnection();
		String sql = "select * from export where type_id = 6 and user_id = ?";
		
		try {
			
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, UsersModel.user.getId());
			
			rs = pt.executeQuery();
			
			while (rs.next()) {
			
				price = rs.getInt("price");
				checkday = rs.getString("day");
				memo = rs.getString("memo");

				String sql2 = "select * from export where price = ? and day = to_char(sysdate) and memo = ? and type_id = 6 and user_id = ?";
				
				pt = conn.prepareStatement(sql2);
				
				pt.setInt(1, price);
				pt.setString(2, memo);
				pt.setInt(3, UsersModel.user.getId());

				rs2 = pt.executeQuery();

				if (!rs2.next()) {
					if (checkday.substring(6, 8).equals(LocalDate.now().toString().substring(8, 10))) {
						insert(price, memo);	
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
	}
	
	// 고정지출의 일마다 데이터베이스에 값 넣기
	public void insert(int price, String memo) {
		ArrayList<ExportModel> list = new ArrayList<ExportModel>();
		String today = LocalDate.now().toString();
		today = today.replace('-','/');
		today = today.substring(2, 10);
		 
		conn = DBUtil.getConnection();

		String sql = "insert into export(user_id, price, day, type_id, memo) values(?, ?, ?, ?, ?)";
		try {
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, UsersModel.user.getId());
			pt.setInt(2, price);
			pt.setString(3, today);
			pt.setInt(4, 6);
			pt.setString(5, memo);
			
			pt.executeUpdate();
			
			String sql2 = "select * from export where id = (select max(id) from export where type_id = 6 and user_id = ?)";
			
			pt = conn.prepareStatement(sql2);
			pt.setInt(1, UsersModel.user.getId());
			
			rs3 = pt.executeQuery();
			JOptionPane.showMessageDialog(null, "고정지출 \"" + memo + "\" " + price + "원 출금", "고정지출", JOptionPane.PLAIN_MESSAGE);
			
			while(rs3.next()) {
				list.add(new ExportModel(rs3.getInt("id"), rs3.getString("day"), rs3.getInt("price"),  rs3.getInt("type_id"), rs3.getString("memo")));
			}
			
			for(int i = 0; i < list.size(); i++) {
				amountDAO.fexinsert(list.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean dayupdate(ExportModel exportModel) {
		int sqlnum = 0;
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id=? and day=?";
		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, exportModel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			pt.setString(3, exportModel.getDay() );
			rs = pt.executeQuery();

			if (rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			String sql1 = "update export set price=?, day=?, type_id=?, memo=? where user_id=? and id=?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportModel.getPrice());
			pt.setString(2, exportModel.getDay());
			pt.setInt(3, exportModel.getType_id());
			pt.setString(4, exportModel.getMemo());
			pt.setInt(5, exportModel.getId());
			pt.setInt(6, sqlnum);

			int num = pt.executeUpdate();

			if (num > 0) {

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean monthupdate(ExportModel exportModel) {
		int sqlnum = 0;
		String mdate=exportModel.getDay().substring(0,5);
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id=? and substr(day, 1, 5) >= ? and substr(day, 1, 5) <=?";
		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, exportModel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			pt.setString(3, mdate);		
			pt.setString(4, mdate);
			rs = pt.executeQuery();

			if (rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			String sql1 = "update export set price=?, day=?, type_id=?, memo=? where user_id=? and id=?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportModel.getPrice());
			pt.setString(2, exportModel.getDay());
			pt.setInt(3, exportModel.getType_id());
			pt.setString(4, exportModel.getMemo());
			pt.setInt(5, exportModel.getId());
			pt.setInt(6, sqlnum);

			int num = pt.executeUpdate();

			if (num > 0) {

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	public boolean daydelete(ExportModel exportmodel) {
		int sqlnum = 0;
		boolean result = false;
		
		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id = ? and day=?";
		
		try {
			
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, exportmodel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			pt.setString(3, exportmodel.getDay() );
			rs = pt.executeQuery();
			
			if(rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			
			String sql1 = "delete export where user_id = ? and id = ?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportmodel.getId());
			pt.setInt(2, sqlnum);
			
			int num = pt.executeUpdate();
			
			if(num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean monthdelete(ExportModel exportmodel) {
		int sqlnum = 0;
		boolean result = false;
		String mdate=exportmodel.getDay().substring(0,5);
		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from export where user_id = ?  and substr(day, 1, 5) >= ? and substr(day, 1, 5) <=?";
		
		try {
			
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, exportmodel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			pt.setString(3, mdate );
			pt.setString(4, mdate );
			rs = pt.executeQuery();
			
			if(rs.next()) {
				sqlnum = rs.getInt("idnum");
			}
			
			String sql1 = "delete export where user_id = ? and id = ?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, exportmodel.getId());
			pt.setInt(2, sqlnum);
			
			int num = pt.executeUpdate();
			
			if(num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}