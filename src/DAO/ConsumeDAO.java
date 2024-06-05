package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class ConsumeDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;

	// 소비태그 1위부터 3위까지
	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "SELECT count(*) "
					+ " from ( SELECT SUM(price) FROM export "
					+ " WHERE user_id = ? AND substr(day,0,5) = substr(sysdate,0,5) and type_id in(1,2,3,4,7) GROUP BY type_id )";

			pt = conn.prepareStatement(countSql);
			pt.setInt(1, UsersModel.user.getId());
			rs = pt.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
			} else {
				return result;
			}
			// 조회하는 sql문 작성
			String sql = "select sum(price) as 합계, type "
					+ " from export join extype on extype.id = export.type_id "
					+ " where user_id = ? and substr(day,0,5) = substr(sysdate,0,5) and export.type_id in(1,2,3,4,7) "
					+ " group by type_id, type order by sum(price) desc";

			pt = conn.prepareStatement(sql);
			pt.setInt(1, UsersModel.user.getId());
			ResultSet rs = pt.executeQuery();

			// 2차원 배열을 선언
			result = new String[row][2];

			// 2차원 배열의 index를 (공간의 번호)
			// 저장하는 변수
			int index = 0;
			while (rs.next()) {
				// 결과를 받아와서 테이블에 추가하는
				// 명령문!
				result[index][0] = rs.getString("합계");
				result[index][1] = rs.getString("type");

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
