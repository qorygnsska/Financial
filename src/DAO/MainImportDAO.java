package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainImportDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	
	public Object[][] select() {
		Object[][] result = null;
		System.out.println("다오 셀렉트");

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from amount";

			pt = conn.prepareStatement(countSql);
			rs = pt.executeQuery();

			int row = 0;

			if (rs.next()) {
				row = rs.getInt(1);
				System.out.println(row);
			} else {
				return result;
			}

			// 조회하는 sql문 작성
			String sql = "select day, price, type from amount where user_id = ?";
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1, 5);
			rs = pt.executeQuery();

			// 2차원 배열을 선언
			result = new String[row][3];

			// 2차원 배열의 index를 (공간의 번호)
			// 저장하는 변수
			int index = 0;

			System.out.println("와일문 시작 전");
			while (rs.next()) {
				
				// 결과를 받아와서 테이블에 추가하는
				// 명령문!
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
//				result[index][3] = rs.getString("content");
//				result[index][4] = rs.getString("memo");
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
