package Controller;

import Service.DatePickerservice;

public class DatePickerController {

	  DatePickerservice ts= new DatePickerservice();
	

	public boolean search(String[] datelist) {
		System.out.println("컨트롤러 실행");
		return ts.search(datelist);
	}

	

	

}