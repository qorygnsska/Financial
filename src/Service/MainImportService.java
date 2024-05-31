package Service;

import java.text.DecimalFormat;

import DAO.MainImportDAO;

public class MainImportService {

	private MainImportDAO dao = new MainImportDAO();
	private DecimalFormat df = new DecimalFormat("#,##0,000");
	// 전제 조회 후 테이블 출력
	public String[][] select() {
		String[][] arr = dao.select();
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			arr[i][1] = df.format(Integer.parseInt(arr[i][1]))  + "원";
		}
		
		return arr;		
	}
	
}