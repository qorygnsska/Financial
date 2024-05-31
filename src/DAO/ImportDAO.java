package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.ImportModel;
import Model.UsersModel;

public class ImportDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;

	private LoginDAO loginDAO = new LoginDAO();

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
					+ " join imtype im on im.id = i.type_id " + " where u.user_id = ? ";

			pt = conn.prepareStatement(sql);
			pt.setString(1, UsersModel.user.getUser_id());
			ResultSet rs = pt.executeQuery();

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
			pt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

<<<<<<< HEAD
	public boolean update(ImportModel importmodel) {
=======
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
>>>>>>> master
		System.out.println("importdao 실행");
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "update import set price=?, day=?,type_id=?, memo=? where user_id=? and id=?";
		try {

			pt = conn.prepareStatement(sql);
<<<<<<< HEAD
			pt.setInt(1, importmodel.getPrice());
			pt.setString(2, importmodel.getDay() );
			pt.setInt(3, importmodel.getType_id());
			pt.setString(4, importmodel.getMemo());
			pt.setInt(5, importmodel.getId());
			pt.setInt(6, importmodel.getIdnum());
=======
			pt.setInt(1, importModel.getPrice());
			pt.setString(2, importModel.getDay());
			pt.setInt(3, importModel.getType_id());
			pt.setString(4, importModel.getMemo());
			pt.setInt(5, importModel.getId());
			pt.setInt(6, importModel.getIdnum());
>>>>>>> master
			int num = pt.executeUpdate();

			if (num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
