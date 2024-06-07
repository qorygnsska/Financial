package Service;

import DAO.DatePickerDAO;

public class DatePickerservice {
	DatePickerDAO tdao = new DatePickerDAO();

	public boolean importsearch(String[] datelist) {
		return tdao.importseracht(datelist);
	}

	public boolean exportsearch(String[] datelist) {
		return tdao.exportseracht(datelist);
	}

	public boolean importmonthsearch(String[] datelist) {
		return tdao.importmonthseracht(datelist);
	}

	public boolean exportmonthsearch(String[] datelist) {
		return tdao.exportmonthseracht(datelist);
	}

}
