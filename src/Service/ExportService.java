package Service;

import DAO.ExportDAO;

public class ExportService {
	private ExportDAO dao = new ExportDAO();

	public String[][] select() {

		return dao.select();
	}
}
