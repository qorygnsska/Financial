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
		System.out.println("(ExportService)지출 내역 추가 중");
		return dao.add(exportModel);
	}

	
	public boolean update(ExportModel exportModel) {
		System.out.println("ExportService 실행중");
		return dao.update(exportModel);
	}

	public String[][] getExportdayselect() {
		
		return dpdao.getExportdayselect();
	}

	public String[][] getExportmonthselect() {
		
		return dpdao.getExportmonthselect();
	}


}