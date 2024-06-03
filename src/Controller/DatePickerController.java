package Controller;

import Service.DatePickerservice;

public class DatePickerController {

	  DatePickerservice ts= new DatePickerservice();
	



	public boolean importsearch(String[] datelist) {
		System.out.println("컨트롤러 실행");
		return ts.importsearch(datelist);
		
	}

	public boolean exportsearch(String[] datelist) {
		System.out.println("컨트롤러 실행");
		return ts.exportsearch(datelist);
		
	}

	public boolean importmonthsearch(String[] datelist) {
		System.out.println("컨트롤러 실행");
		return ts.importmonthsearch(datelist);
		
	}

	

}
