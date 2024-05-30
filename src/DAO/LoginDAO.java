package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

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

	

	public Object idfind(String jumin) {
		
		String id=null;
		System.out.println("아이디 찾기 DAO 실행!");
		
		con = DBUtil.getConnection();
		String sql = "select user_id from users where jumin=?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, jumin);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("user_id"));
				
				id=rs.getString("user_id");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}



	public Object pwfind(String jumin, String uid) {
		String pw=null;
		System.out.println("비번 찾기 DAO 실행!");
		System.out.println(jumin);
		System.out.println(uid);

		con = DBUtil.getConnection();
		String sql = "select user_pass from users where jumin=? and user_id=?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, jumin);
			ps.setString(2, uid);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("user_pass"));
				
				pw=rs.getString("user_pass");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pw;
	}

}
