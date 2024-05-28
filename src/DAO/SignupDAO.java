package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class SignupDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public boolean signup(UsersModel user) {
		System.out.println("signup DAO 실행");
		boolean result = false;
		
		con = DBUtil.getConnection();
		String sql = "insert into users(user_id, user_pass, name, jumin) values(?, ?, ?, ?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getUser_pass());
			ps.setString(3, user.getName());
			ps.setString(4, user.getJumin());
			
			int num = ps.executeUpdate();
			
			if(num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
