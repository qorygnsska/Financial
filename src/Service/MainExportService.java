package Service;

import DAO.MainExportDAO;

public class MainExportService {

	private MainExportDAO dao = new MainExportDAO();
	
	// 전제 조회 후 테이블 출력
	public String[][] select() {
				
		return dao.select();		
	}

}