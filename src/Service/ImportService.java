package Service;

import DAO.ImportDAO;

public class ImportService {

	private ImportDAO dao = new ImportDAO();
	
	// 전제 조회 후 테이블 출력
	public Object[][] select() {
			System.out.println("서비스 셀렉트");
		return dao.select();		
	}
	
}
