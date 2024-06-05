package Service;

import DAO.DatePickerDAO;
import DAO.ImportDAO;
import Model.ImportModel;

public class ImportService {
	private ImportDAO dao = new ImportDAO();
	private DatePickerDAO dpdao=new DatePickerDAO();
	public String[][] select() {
		return dao.select();
	}
	
	public boolean add(ImportModel importModel) {
		return dao.add(importModel);
	}

	public boolean update(ImportModel importmodel) {
		return dao.update(importmodel);
	}

	public String[][] getImportdayselect() {
		return  dpdao.getImportdayselect();
	}

	public String[][] getImportmonthselect() {
		return dpdao.getImportmonthselect();
	}

	public boolean delete(ImportModel importmodel) {
		return dao.delete(importmodel);
	}
	
	public void check() {
		dao.check();
	}
}