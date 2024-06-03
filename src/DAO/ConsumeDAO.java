package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class ConsumeDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;

	
	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "SELECT count(*)\r\n" + 
					"from (\r\n" + 
					"SELECT SUM(price)\r\n" + 
					"FROM export\r\n" + 
					"WHERE\r\n" + 
					"user_id = ?\r\n" + 
					"GROUP BY type_id\r\n" + 
					")";

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
			String sql = "select sum(price) as 합계, type from export\r\n" + 
					"join extype on extype.id = export.type_id\r\n" + 
					"where user_id = ? and substr(day,0,4) = substr(sysdate,0,4)\r\n" + 
					"group by type_id, type\r\n" + 
					"order by sum(price) desc";
			
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
				System.out.println();
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
