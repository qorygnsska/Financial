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
		System.out.println("DatePicker DAO 실행!");
		
		con = DBUtil.getConnection();
		String sql = "select * from export where day=?";
		String date1 = datelist[0];
		
		System.out.println(datelist[0]);
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1,date1);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.print(rs.getString("day"));
				System.out.print(rs.getInt("price"));
				System.out.print(rs.getInt("type_id"));
				System.out.print(rs.getString("memo"));
				
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
