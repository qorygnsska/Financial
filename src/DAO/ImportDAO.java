package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class ImportDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;

	private LoginDAO loginDAO = new LoginDAO();

	public String[][] select(){
		String[][] result = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String countSql = "select count(*) from import";
			
			pt = conn.prepareStatement(countSql);
			rs = pt.executeQuery();
			
			int row = 0;
			
			System.out.println("s1");
			if(rs.next()) {
				row = rs.getInt(1);
				System.out.println(row);
			}else {
				return result;
			}
			System.out.println("s2");
			
			String sql = "select day, price, im.type, memo " +
						 " from users u " + 
						 " join import i on i.user_id = u.id " + 
						 " join imtype im on im.id = i.type_id " +
						 " where u.user_id = ? ";
			System.out.println("s3");
			
			pt = conn.prepareStatement(sql);
			pt.setString(1, UsersModel.user.getUser_id());
			System.out.println("s4");
			ResultSet rs = pt.executeQuery();
			
			result = new String[row][4];
			
			int index = 0;
			
			while(rs.next()) {
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			System.out.println("s5");
			rs.close();
			pt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
