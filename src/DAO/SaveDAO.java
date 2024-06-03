package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.SaveModel;
import Model.UsersModel;

public class SaveDAO {

	private Connection conn;
	private PreparedStatement pt;
	private ResultSet rs;
	
	public String[][] select() {
		String[][] result = null;

		try {

			conn = DBUtil.getConnection();

			String countSql = "select count(*) from saveprice "
					+ " join users on users.id = saveprice.user_id "
					+ " where saveprice.user_id = ?";

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
			String sql = "select day, price, type, nvl(memo, '없음') as memo from saveprice"
					+ " join extype on extype.id = saveprice.type_id"
					+ " join users on users.id = saveprice.user_id"
					+ " where users.id = ? and type = '저축'";
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1, UsersModel.user.getId());
			ResultSet rs = pt.executeQuery();

			// 2차원 배열을 선언
			result = new String[row][4];

			// 2차원 배열의 index를 (공간의 번호)
			// 저장하는 변수
			int index = 0;
			while (rs.next()) {
				// 결과를 받아와서 테이블에 추가하는
				// 명령문!
				System.out.println();
				result[index][0] = rs.getString("day");
				result[index][1] = rs.getString("price");
				result[index][2] = rs.getString("type");
				result[index][3] = rs.getString("memo");
				index++;
			}
			
			// 닫기
			rs.close();
			pt.close();
			conn.close();

		} catch (Exception e) {
//			System.out.println("저축테이블에 데이터가 없음");
			e.printStackTrace();
		}

		return result;
	}
	
	
	 // 지출상세에서 저축 추가하면 저축테이블에 값 저장
	public void insert(SaveModel model) {
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into saveprice(user_id, price, day, type_id, memo)\r\n" + 
					"values (?,?,?,?,?)";
			
			pt = conn.prepareStatement(sql);
			pt.setInt(1,UsersModel.user.getId());
			pt.setInt(2, model.getPrice());
			pt.setString(3, model.getDay());
			pt.setInt(4, model.getType_id());
			pt.setString(5, model.getMemo());
			
			int row = pt.executeUpdate();
			if(row > 0) {
				System.out.println("추가 성공");
			}
			
		} catch (Exception e) {}
	}
	
	public void update(SaveModel model) {
		
		int sqlid = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select DISTINCT  NTH_VALUE(id, ?) OVER(order by day desc ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING)as idnum from saveprice where user_id = ?";
			
			pt = conn.prepareStatement(sql);
			
			pt.setInt(1, model.getRowNum());
			pt.setInt(2, UsersModel.user.getId());
			
			rs = pt.executeQuery();
			
			
			if(rs.next()) {
				sqlid = rs.getInt(1);
			}else {
				System.out.println("없는데?");
			}
			
			
			String sql2 = "update saveprice set price=?, day=?, type_id=?, memo=? where user_id=? and id=?";
			
			pt = conn.prepareStatement(sql2);
			pt.setInt(1, model.getPrice());
			pt.setString(2, model.getDay());
			pt.setInt(3, model.getType_id());
			pt.setString(4, model.getMemo());
			pt.setInt(5, UsersModel.user.getId());
			pt.setInt(6, sqlid);
			
			int row = pt.executeUpdate();
			
			if(row > 0) {
				 System.out.println("저축 수정 완료");
			}
			
		} catch (Exception e) {
		}
	}
}
