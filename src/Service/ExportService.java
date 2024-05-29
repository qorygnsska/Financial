package Service;

import DAO.ExportDAO;

public class ExportService {

	private ExportDAO dao = new ExportDAO();
	
	// 전제 조회 후 테이블 출력
	public String[][] select() {
				
		return dao.select();		
	}

}
