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
		System.out.println("(ImportService)지출 내역 추가 중");
		return dao.add(importModel);
	}

	public boolean update(ImportModel importmodel) {
		System.out.println("ImportService 실행중");
		return dao.update(importmodel);
	}

	public String[][] getImportdayselect() {
		return  dpdao.getImportdayselect();
	}

	public String[][] getImportmonthselect() {
		return dpdao.getImportmonthselect();
	}

	public boolean delete(ImportModel importmodel) {
		System.out.println("ImportService 실행중");
		return dao.delete(importmodel);
	}
}