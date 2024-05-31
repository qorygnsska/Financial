package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.ExportModel;
import Model.UsersModel;

public class ExportDAO {
	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;

	private LoginDAO loginDAO = new LoginDAO();

	public String[][] select(){
		String[][] result = null;
		
		try {
			
			conn = DBUtil.getConnection();
			
			String countSql = "select count(*) from export where user_id = ?";
			
			pt = conn.prepareStatement(countSql);
			pt.setInt(1, UsersModel.user.getId());
			rs = pt.executeQuery();
			
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
						 " where u.user_id = ? ";
			pt = conn.prepareStatement(sql);
			pt.setString(1, UsersModel.user.getUser_id());
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
			rs.close();
			pt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 지출 내역 추가
	public boolean add(ExportModel exportModel) {
		System.out.println("(ExportDAO) 지출 내역 추가 중");
		boolean result = false;
		
		conn = DBUtil.getConnection();
		String sql = "insert into export(user_id, price, day, type_id, memo) values(?, ?, ?, ?, ?)";
		
		try {
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1, exportModel.getId());
			pt.setInt(2, exportModel.getPrice());
			pt.setString(3, exportModel.getDay());
			pt.setInt(4, exportModel.getType_id());
			pt.setString(5, exportModel.getMemo());
			
			int num = pt.executeUpdate();
			
			if(num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean update(ExportModel exportModel) {
		System.out.println("exportdao 실행");
		boolean result = false;

		conn = DBUtil.getConnection();
		String sql = "update export set price=?, day=?,type_id=?, memo=? where user_id=? and id=?";
		try {

			pt = conn.prepareStatement(sql);
			pt.setInt(1, exportModel.getPrice());
			pt.setString(2, exportModel.getDay());
			pt.setInt(3, exportModel.getType_id());
			pt.setString(4, exportModel.getMemo());
			pt.setInt(5, exportModel.getId());
			pt.setInt(6, exportModel.getIdnum());
			int num = pt.executeUpdate();
			System.out.println(exportModel.getId());
			System.out.println(exportModel.getIdnum());
			if (num > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}