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

	// 선택한 날짜의 값이 있는지 확인
	public boolean selectCheck(String date) {
		System.out.println("(ExportService)선택한 날짜 조회 중");
		return dao.selectCheck(date);
	}

	// 선택한 날짜의 값을 불러오는 메서드
	public String[][] selectDay(String date) {
		return dao.selectDay(date);
	}

}