package Controller;

import Service.DatePickerservice;

public class DatePickerController {

	DatePickerservice ts = new DatePickerservice();

	public boolean importsearch(String[] datelist) {
		return ts.importsearch(datelist);

	}

	public boolean exportsearch(String[] datelist) {
		return ts.exportsearch(datelist);

	}

	public boolean importmonthsearch(String[] datelist) {
		return ts.importmonthsearch(datelist);

	}

	public boolean exportmonthsearch(String[] datelist) {
		return ts.exportmonthsearch(datelist);
	}

}
