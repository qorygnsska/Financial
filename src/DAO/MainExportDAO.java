package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class MainExportDAO {
	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	
	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from export where user_id = ?";

			pt = conn.prepareStatement(countSql);
			pt.setInt(1, UsersModel.user.getId());
			rs = pt.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
//				System.out.println(row);
			} else {
				return result;
			}

			// 조회하는 sql문 작성
			String sql = "select day, price, type from export join extype on extype.id = export.type_id where user_id = ? ";

			pt = conn.prepareStatement(sql);
			pt.setString(1, UsersModel.user.getUser_id());

			ResultSet rs = pt.executeQuery();

			// 2차원 배열을 선언
			result = new String[row][3];

			// 2차원 배열의 index를 (공간의 번호)
			// 저장하는 변수
			int index = 0;

			while (rs.next()) {
				// 결과를 받아와서 테이블에 추가하는
				// 명령문!
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				index++;
			}
			// 닫기
			rs.close();
			pt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
