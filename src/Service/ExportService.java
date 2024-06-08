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

	public int dayupdate(ExportModel exportModel) {
		
		return dao.dayupdate(exportModel);
	}

	public boolean monthupdate(ExportModel exportModel) {
		// TODO Auto-generated method stub
		return dao.monthupdate(exportModel);
	}

	public int daydelete(ExportModel exportmodel) {
	
		return dao.daydelete(exportmodel);
	}
	
	public boolean monthdelete(ExportModel exportmodel) {
		
		return dao.monthdelete(exportmodel);
	}
}