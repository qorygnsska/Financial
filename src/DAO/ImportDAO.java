package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.ExportModel;
import Model.ImportModel;
import Model.UsersModel;

public class ImportDAO {
	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	private ResultSet rs2;

	private LoginDAO loginDAO = new LoginDAO();
	private AmountDAO amountDAO = new AmountDAO();

	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from import where user_id = ?";

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

			String sql = "select day, price, im.type, memo " + " from users u " + " join import i on i.user_id = u.id "
					+ " join imtype im on im.id = i.type_id " + " where u.user_id = ? order by day desc";

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
	
	// 수입 내역 추가
	public boolean add(ImportModel importModel) {
		System.out.println("(ImportDAO) 수입 내역 추가 중");
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "insert into import(user_id, price, day, type_id, memo) values(?, ?, ?, ?, ?)";

		try {

			pt = conn.prepareStatement(sql);
			pt.setInt(1, importModel.getId());
			pt.setInt(2, importModel.getPrice());
			pt.setString(3, importModel.getDay());
			pt.setInt(4, importModel.getType_id());
			pt.setString(5, importModel.getMemo());

			int num = pt.executeUpdate();

			if (num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(ImportModel importModel) {
		int sqlnum=0;
		System.out.println("importdao 실행");
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from import where user_id=?";
		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, importModel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			rs=pt.executeQuery();

			if (rs.next()) {
			 sqlnum=rs.getInt("idnum");
			
			}
			
			String sql1 = "update import set price=?, day=?, type_id=?, memo=? where user_id=? and id=?";
			pt = conn.prepareStatement(sql1);
			
			pt.setInt(1, importModel.getPrice());
			pt.setString(2, importModel.getDay());
			pt.setInt(3, importModel.getType_id());
			pt.setString(4, importModel.getMemo());
			pt.setInt(5, importModel.getId());
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

	public boolean delete(ImportModel importmodel) {
		int sqlnum = 0;
		System.out.println("importdao 실행");
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from import where user_id = ? ";
		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, importmodel.getIdnum());
			pt.setInt(2, UsersModel.user.getId());
			rs = pt.executeQuery();

			if (rs.next()) {
			 sqlnum=rs.getInt("idnum");
			}
			
			String sql1 = "delete import where user_id = ? and id = ?";
			pt = conn.prepareStatement(sql1);
		
			pt.setInt(1, importmodel.getId());
			pt.setInt(2, sqlnum);

			int num = pt.executeUpdate();

			if (num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 고정수입 날짜 비교
	public void check() {
		String today = LocalDate.now().toString();
		today = today.replace('-', '/');
		today = today.substring(2, 10);

		String checkday = "";
		int price = 0;
		String memo = "";

		conn = DBUtil.getConnection();
		String sql = "select * from import where type_id = 3 and user_id = ?";

		try {

			pt = conn.prepareStatement(sql);

			pt.setInt(1, UsersModel.user.getId());

			rs = pt.executeQuery();

			while (rs.next()) {

				price = rs.getInt("price");
				checkday = rs.getString("day");
				memo = rs.getString("memo");

				String sql2 = "select * from import where price = ? and day = to_char(sysdate) and memo = ? and type_id = 3 and user_id = ?";

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
			ArrayList<ImportModel> list = new ArrayList<ImportModel>();
			String today = LocalDate.now().toString();
			today = today.replace('-','/');
			today = today.substring(2, 10);
			 
			conn = DBUtil.getConnection();

			String sql = "insert into import(user_id, price, day, type_id, memo) values(?, ?, ?, ?, ?)";
			try {
				pt = conn.prepareStatement(sql);
				
				pt.setInt(1, UsersModel.user.getId());
				pt.setInt(2, price);
				pt.setString(3, today);
				pt.setInt(4, 3);
				pt.setString(5, memo);
				
				pt.executeUpdate();
				
				String sql2 = "select * from import where id = (select max(id) from import where type_id = 3 and user_id = ?)";
				
				pt = conn.prepareStatement(sql2);
				pt.setInt(1, UsersModel.user.getId());
				
				rs = pt.executeQuery();
				JOptionPane.showMessageDialog(null, "고정수입 \"" + memo + "\"" + price + "원 입금", "고정수입", JOptionPane.PLAIN_MESSAGE);
				
				while(rs.next()) {
					list.add(new ImportModel(rs.getInt("id"), rs.getString("day"), rs.getInt("price"),  rs.getInt("type_id"), rs.getString("memo")));
				}
				
				for(int i = 0; i < list.size(); i++) {
					amountDAO.fiminsert(list.get(i));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

}