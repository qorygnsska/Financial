package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class UsersDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	UsersModel user;

	public UsersModel save(String id, String pass) {
		con = DBUtil.getConnection();
		String sql = "select * from users where user_id = ? and user_pass = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pass);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UsersModel(rs.getInt("id"), rs.getString("user_id"), rs.getString("user_pass"),
						rs.getString("name"), rs.getString("jumin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
