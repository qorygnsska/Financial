package Service;

import DAO.ImportDAO;
import Model.ImportModel;

public class ImportService {
	private ImportDAO dao = new ImportDAO();

	public String[][] select() {

		return dao.select();
	}
	
	public boolean add(ImportModel importModel) {
		System.out.println("(ImportService)지출 내역 추가 중");
		return dao.add(importModel);
	}

	public boolean update(ImportModel importmodel) {
		System.out.println("ImportService 실행중");
		return dao.update(importmodel);
	}
}