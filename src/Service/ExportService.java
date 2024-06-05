package Service;

import DAO.DatePickerDAO;
import DAO.ExportDAO;
import Model.ExportModel;

public class ExportService {
	private ExportDAO dao = new ExportDAO();
	private DatePickerDAO dpdao=new DatePickerDAO();
	public String[][] select() {
		return dao.select();
	}

	public boolean add(ExportModel exportModel) {
		return dao.add(exportModel);
	}

	
	public boolean update(ExportModel exportModel) {
		return dao.update(exportModel);
	}

	public String[][] getExportdayselect() {
		return dpdao.getExportdayselect();
	}

	public String[][] getExportmonthselect() {
		return dpdao.getExportmonthselect();
	}

	public boolean delete(ExportModel exportmodel) {
		return dao.delete(exportmodel);
	}
	
	public void check() {
		dao.check();
	}
}