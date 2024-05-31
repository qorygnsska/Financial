package Service;

import DAO.ExportDAO;
import Model.ExportModel;

public class ExportService {
	private ExportDAO dao = new ExportDAO();

	public String[][] select() {

		return dao.select();
	}

	public boolean add(ExportModel exportModel) {
		System.out.println("(ExportService)지출 내역 추가 중");
		return dao.add(exportModel);
	}
<<<<<<< HEAD
=======
	
	public boolean update(ExportModel exportModel) {
		System.out.println("ExportService 실행중");
		return dao.update(exportModel);
	}
>>>>>>> bs

}