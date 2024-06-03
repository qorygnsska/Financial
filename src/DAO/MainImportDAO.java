package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class MainImportDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	
	public String[][] select() {
		String[][] result = null;
		System.out.println("다오 셀렉트");

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from import where user_id = ? and substr(day,0,5) = substr(sysdate,0,5)";

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
			String sql = "select day, price, type from import join imtype on imtype.id = import.type_id where user_id = ? and substr(day,0,5) = substr(sysdate,0,5) order by day desc";
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1, UsersModel.user.getId());
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
	
	// 이번달 수입
		public int thisMonth() {
			int money = 0;
			
			try {

				conn = DBUtil.getConnection();

				String sql = "select nvl(sum(price),0)\r\n" + 
						"from import\r\n" + 
						"where user_id = ? and substr(day,0,5) = substr(SYSDATE,0,5)";
				
				pt = conn.prepareStatement(sql);
				pt.setInt(1, UsersModel.user.getId());
				rs = pt.executeQuery();

				
				if (rs.next()) {
					money = rs.getInt(1);
				
				} else {
					return money;
				}

				
				// 닫기
				rs.close();
				pt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return money;
		}
		
		
		// 저번달 수입
		public int beforeMonth() {
			int money = 0;
			
			try {

				conn = DBUtil.getConnection();

				String sql = "select nvl(sum(price),0)\r\n" + 
						"from import\r\n" + 
						"where user_id = ? and substr(day,0,5) = substr(to_char(add_months(sysdate,-1)),0,5)";
				
				pt = conn.prepareStatement(sql);
				pt.setInt(1, UsersModel.user.getId());
				rs = pt.executeQuery();

				
				if (rs.next()) {
					money = rs.getInt(1);
				
				} else {
					return money;
				}

				
				// 닫기
				rs.close();
				pt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return money;
		}
}
