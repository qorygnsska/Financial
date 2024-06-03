package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UsersModel;

public class AmountDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public Object[][] selecet() {
		Object[][] result = null;
		System.out.println("Amount DAO의 select 실행!!");

		con = DBUtil.getConnection();
		String sql = "select day, price, type, content, memo from amount where user_id = ? order by day desc"; // 접속중인 아이디의 데이터값 찾기
		
		String sql2 = "select count(*) from amount where user_id = ?"; // 접속중인 아이디의 데이터 개수 sql

		// 접속중인 아이디의 총액 sql
		String sql3 = "select (select sum(price) from amount where user_id = ? and (type = '수입' or type = '고정수입') ) - (select sum(price) from amount where user_id = ? and (type = '지출' or type = '고정지출')) as 합 from amount where rownum <= 1";

		try {
			ps = con.prepareStatement(sql2);
			
			ps.setInt(1, UsersModel.user.getId());
	
			rs = ps.executeQuery();

			int row = 0;
			if (rs.next()) {
				row = rs.getInt(1);
			}

			ps = con.prepareStatement(sql3);
			
			ps.setInt(1, UsersModel.user.getId());
			ps.setInt(2, UsersModel.user.getId());

			rs = ps.executeQuery();
			int sum = 0;
			if (rs.next()) {
				sum = rs.getInt(1);
			}

			ps = con.prepareStatement(sql);
			ps.setInt(1, UsersModel.user.getId());
			rs = ps.executeQuery();
			result = new String[row][5];
			int index = 0;
			while (rs.next()) {
				if (rs.getString("type").equals("수입") || rs.getString("type").equals("고정수입")) {
					result[index][0] = rs.getString("day");

					int price = rs.getInt("price");
					String realprice = "+" + String.format("%,d원", price);
					result[index][1] = realprice;

					result[index][2] = rs.getString("content");
					result[index][3] = rs.getString("memo");

					String money = String.format("%,d원", sum);
					result[index][4] = money;
					sum -= rs.getInt("price");
					index++;

				} else if (rs.getString("type").equals("지출") || rs.getString("type").equals("고정지출")) {
					result[index][0] = rs.getString("day");

					int price = rs.getInt("price");
					String realprice = "-" + String.format("%,d원", price);
					result[index][1] = realprice;

					result[index][2] = rs.getString("content");
					result[index][3] = rs.getString("memo");

					String money = String.format("%,d원", sum);
					result[index][4] = money;
					sum += rs.getInt("price");
					index++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// memo가 있는 amount에 값 넣기!!
	public void insert(int user_id, int price, String day, String type, String content, String memo) {
		con = DBUtil.getConnection();
		String sql = "insert into amount(user_id, price, day, type, content, memo) values(?, ?, ?, ?, ?, ?)";

		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, user_id);
			ps.setInt(2, price);
			ps.setString(3, day);
			ps.setString(4, type);
			ps.setString(5, content);
			ps.setString(6, memo);
			
			int res = ps.executeUpdate();
			
			if(res > 0) {
				System.out.println("저장 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// memo가 없는 amount에 값 넣기!!
	public void insert(int user_id, int price, String day, String type, String content) {
		con = DBUtil.getConnection();
		String sql = "insert into amount(user_id, price, day, type, content) values(?, ?, ?, ?, ?)";

		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, user_id);
			ps.setInt(2, price);
			ps.setString(3, day);
			ps.setString(4, type);
			ps.setString(5, content);

			int res = ps.executeUpdate();
			
			if(res > 0) {
				System.out.println("저장 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public String amount() {
		con = DBUtil.getConnection();
		String sql = "select (select sum(price) from amount where user_id = ? and (type = '수입' or type = '고정수입') ) - (select sum(price) from amount where user_id = ? and (type = '지출' or type = '고정지출')) as 합 from amount where rownum <= 1";
		String realamount = "";
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, UsersModel.user.getId());
			ps.setInt(2, UsersModel.user.getId());
			
			rs = ps.executeQuery();
			
			int amount = 0;
			if (rs.next()) {
				amount = rs.getInt(1);
			}
			realamount = String.format("%,d원", amount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return realamount;
	}
}
