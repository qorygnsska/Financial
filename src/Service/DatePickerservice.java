package Service;

import DAO.DatePickerDAO;

public class DatePickerservice {
DatePickerDAO tdao = new DatePickerDAO();

	public boolean search(String[] datelist) {
		System.out.println("서비스실행");
		return tdao.seracht(datelist);
	}

}