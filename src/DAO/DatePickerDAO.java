package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatePickerDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public boolean seracht(String[] datelist) {
		boolean result = false;
		System.out.println("test DAO 실행!");
		
		con = DBUtil.getConnection();
		String sql = "select * from import where day=? or day=?";
		String date1 = datelist[0];
		String date2= datelist[1];
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1,date1);
			ps.setString(2,date2);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getInt("price"));
				System.out.println(rs.getString("day"));
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
