package Service;

import DAO.ImportDAO;
import Model.ImportModel;

public class ImportService {
	private ImportDAO dao = new ImportDAO();

	public String[][] select() {

		return dao.select();
	}

	public boolean update(ImportModel importModel) {
		System.out.println("ImportService 실행중");
		return dao.update(importModel);
	}
}