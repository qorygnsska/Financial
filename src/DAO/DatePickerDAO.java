package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatePickerDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public boolean seracht(String[] datelist) {
		boolean result = false;
		System.out.println("DatePicker DAO 실행!");

		con = DBUtil.getConnection();
		String sql = "select day,price,ex.type ,memo from export e\r\n" + "join extype ex on e.type_id=ex.id\r\n"
				+ "where day=?";
		String date1 = datelist[0];

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, date1);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("day") + " ");
				System.out.print(rs.getInt("price") + " ");
				System.out.print(rs.getString("type") + " ");
				System.out.print(rs.getString("memo"));
				System.out.println();

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean importseracht(String[] datelist) {
		boolean result = false;
		System.out.println("DatePicker DAO 실행!");

		con = DBUtil.getConnection();
		String sql = "select day,price,ex.type ,memo from import e\r\n" + "join extype ex on e.type_id=ex.id\r\n"
				+ "where day=?";
		String date1 = datelist[0];

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, date1);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("day") + " ");
				System.out.print(rs.getInt("price") + " ");
				System.out.print(rs.getString("type") + " ");
				System.out.print(rs.getString("memo"));
				System.out.println();

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
