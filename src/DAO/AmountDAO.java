package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AmountDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	
	public Object[][] selecet() {
		Object[][] result = null;
		System.out.println("Amount DAO의 select 실행!!");
		
		con = DBUtil.getConnection();
		String sql = "select day, price, type, content, memo from amount where user_id = 5 order by day desc"; // 아이디가 5인 데이터 sql 
		String sql2 = "select count(*) from amount"; // 데이터 개수 sql
		
		// 아이디가 5인 총액 sql
		String sql3 = "select (select sum(price) from amount where type = '수입' or type = '고정수입') - (select sum(price) from amount where type = '지출' or type = '고정지출') as 합 from amount where user_id = 5 and rownum <= 1";
		
		try {
			ps = con.prepareStatement(sql2);
			
			rs = ps.executeQuery();
			
			int row = 0;
			if(rs.next()) {
				row = rs.getInt(1);
			}	
			
			ps = con.prepareStatement(sql3);
			
			rs = ps.executeQuery();
			int sum = 0;
			if(rs.next()) {
				sum = rs.getInt(1);
			}
			
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			result = new String[row][5];
			int index = 0;
			while(rs.next()) {
				if(rs.getString("type").equals("수입") || rs.getString("type").equals("고정수입")) {
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
					
				}else if(rs.getString("type").equals("지출") || rs.getString("type").equals("고정지출")) {
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
}
