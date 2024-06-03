package Service;

import java.text.DecimalFormat;

import DAO.MainExportDAO;

public class MainExportService {

	private MainExportDAO dao = new MainExportDAO();
	// 전제 조회 후 테이블 출력
	public String[][] select() {
				
		String[][] arr = dao.select();
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			arr[i][1] = String.format("%,d원", Integer.parseInt(arr[i][1]));
		}
		
		return arr;		
	}

	
	public String thisMonth() {
		int money = dao.thisMonth();
		String sum = String.format("%,d원", money);
		return sum;
	}
	
	public String beforeMonth() {
		int money = dao.beforeMonth();
		String sum = String.format("%,d원", money);
		return sum;
	}
}