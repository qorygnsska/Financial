package Service;

import DAO.ImportDAO;

public class ImportService {
	private ImportDAO dao = new ImportDAO();

	public String[][] select() {

		return dao.select();
	}
}