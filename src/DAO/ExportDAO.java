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

	private LoginDAO loginDAO = new LoginDAO();
	
	AmountDAO amountDAO = new AmountDAO();

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
				System.out.println(row);
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
		System.out.println("(ExportDAO) 지출 내역 추가 중");
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
		System.out.println("exportdao 실행");
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
				System.out.println("sdfsdf" + sqlnum);
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
		System.out.println("exportdao 실행");
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
		System.out.println(today);
		today = today.substring(2, 10);
		System.out.println(today);
		
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
			
				System.out.println("너 export에 typeid 6랑 userid1인게 있네");
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
					System.out.println("너 데이터베이스에 price랑 메모 일치하는 값이 없네! 추가할게!!");
					if (checkday.substring(6, 8).equals(LocalDate.now().toString().substring(8, 10))) {
						System.out.println("오 오늘의 일자와 데이터베이스의 일자가 같네 insert 실행!");
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
		System.out.println("넘어왔어");
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
			System.out.println("insert로 값 넣었어!!");
			
			String sql2 = "select * from export where id = (select max(id) from export where type_id = 6 and user_id = ?)";
			
			pt = conn.prepareStatement(sql2);
			pt.setInt(1, UsersModel.user.getId());
			
			rs = pt.executeQuery();
			JOptionPane.showMessageDialog(null, "고정지출 \"" + memo + "\" 출금", "고정지출", JOptionPane.PLAIN_MESSAGE);
			
			while(rs.next()) {
				System.out.println("가장 최근에 넣은 값 list에 넣을게~");
				list.add(new ExportModel(rs.getInt("id"), rs.getString("day"), rs.getInt("price"),  rs.getInt("type_id"), rs.getString("memo")));
			}
			
			for(int i = 0; i < list.size(); i++) {
				System.out.println("amount로 출동!!");
				amountDAO.fexinsert(list.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}