package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;
import View.ImportView;

public class DatePickerDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
static String date1=null;
static  String date2;
	public boolean importseracht(String[] datelist) {
		boolean result = false;
		System.out.println("DatePicker DAO 실행!");

		con = DBUtil.getConnection();
		String sql = "select day,price,im.type ,memo from import e\r\n" + "join imtype im on e.type_id=im.id\r\n"
				+ "where day=?";
		 date1 = datelist[0];

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, date1);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("day") + " ");
				System.out.print(rs.getInt("price") + " ");
				System.out.print(rs.getString("type") + " ");
				System.out.print(rs.getString("memo"));
				System.out.println();

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean exportseracht(String[] datelist) {
		boolean result = false;
		System.out.println("DatePicker DAO 실행!");

		con = DBUtil.getConnection();
		String sql = "select day,price,ex.type ,memo from export e\r\n" + "join extype ex on e.type_id=ex.id\r\n"
				+ "where day=?";
		 date1 = datelist[0];

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, date1);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("day") + " ");
				System.out.print(rs.getInt("price") + " ");
				System.out.print(rs.getString("type") + " ");
				System.out.print(rs.getString("memo"));
				System.out.println();

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 일별 수입조회 DAO
	public String[][] getImportdayselect() {
		String[][] result = null;

		try {

			con = DBUtil.getConnection();

			String countSql = "select count(*) from import where user_id = ?";

			ps = con.prepareStatement(countSql);
			ps.setInt(1, UsersModel.user.getId());
			rs = ps.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
				System.out.println(row);
			} else {
				return result;
			}

			String sql = "select day, price, im.type, memo " + " from users u " + " join import i on i.user_id = u.id "
					+ " join imtype im on im.id = i.type_id " + " where u.user_id = ? and day=? order by day asc";

			ps = con.prepareStatement(sql);
			ps.setString(1, UsersModel.user.getUser_id());
			ps.setString(2, date1);
			ResultSet rs = ps.executeQuery();

				System.out.println("===================실행값==========="+date1);
			result = new String[row][4];

			int index = 0;
			
			while (rs.next()) {
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
	//캘린더에서  일별 지출조회  DAO
	public String[][] getExportdayselect() {
String[][] result = null;
		
		try {
			
			con = DBUtil.getConnection();
			
			String countSql = "select count(*) from export where user_id = ?";
			
			ps = con.prepareStatement(countSql);
			ps.setInt(1, UsersModel.user.getId());
			rs = ps.executeQuery();
			
			int row = 0;
			
			if(rs.next()) {
				row = rs.getInt(1);
				System.out.println(row);
			}else {
				return result;
			}
			
			String sql = "select day, price, ex.type, memo " +
						 " from users u " + 
						 " join export e on e.user_id = u.id " + 
						 " join extype ex on ex.id = e.type_id " +
						 " where u.user_id = ? and day=? order by day asc";
			ps = con.prepareStatement(sql);
			ps.setString(1, UsersModel.user.getUser_id());
			ps.setString(2, date1);
			ResultSet rs = ps.executeQuery();
			
			result = new String[row][4];
			
			int index = 0;
			
			while(rs.next()) {
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			rs.close();
			ps.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//수입 월별 조회 dao
	public String[][] getImportmonthselect() {
		String[][] result = null;
	
					
		try {

			con = DBUtil.getConnection();

			String countSql = "select count(*) from import where user_id = ?";

			ps = con.prepareStatement(countSql);
			ps.setInt(1, UsersModel.user.getId());
			rs = ps.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
				System.out.println(row);
			} else {
				return result;
			}

			String sql =  "select day, price, im.type, memo " + " from users u " + " join import i on i.user_id = u.id "
					+ " join imtype im on im.id = i.type_id " + " where u.user_id = ?  and substr(day, 1, 5)=?  order by day desc";
			
			 
			ps = con.prepareStatement(sql);
			ps.setString(1, UsersModel.user.getUser_id());
			ps.setString(2, date2);
			System.out.println("선택한 월 결과" + date2);
			
			
		
			ResultSet rs = ps.executeQuery();

				
			result = new String[row][4];

			int index = 0;

			while (rs.next()) {
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
	
	//수입 월 데이터가 있는지 확인하는 dao
	public boolean importmonthseracht(String[] datelist) {
		boolean result = false;
		System.out.println("DatePicker DAO 실행!");

		con = DBUtil.getConnection();
		String sql = "select day, price, im.type, memo\r\n" + 
				"from users u\r\n" + 
				"join import i on i.user_id = u.id\r\n" + 
				"join imtype im on im.id = i.type_id\r\n" + 
				"where substr(day, 1, 5) >= ? and substr(day, 1, 5) <= ? order by day asc";
		 date1 = datelist[0];
		 
		  date2=date1.substring(0,5);
		 System.out.println("확인하기============="+date2);
		 
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, date2);
			ps.setString(2, date2);
			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("day") + " ");
				System.out.print(rs.getInt("price") + " ");
				System.out.print(rs.getString("type") + " ");
				System.out.print(rs.getString("memo"));
				System.out.println();

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	
	}

}
