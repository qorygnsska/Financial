package Service;

import DAO.ImportDAO;

public class ImportService {

	private ImportDAO dao = new ImportDAO();
	
	// 전제 조회 후 테이블 출력
	public String[][] select() {
			
		return dao.select();		
	}
	
}
