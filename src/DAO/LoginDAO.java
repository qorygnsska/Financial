package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public boolean login(String id, String pass) {
		boolean result = false;
		System.out.println("로그인 DAO 실행!");
		
		con = DBUtil.getConnection();
		String sql = "select * from users where user_id = ? and user_pass = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
