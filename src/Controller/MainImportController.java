package Controller;

<<<<<<< HEAD
<<<<<<<< HEAD:src/Controller/MainImportController.java
import javax.swing.table.DefaultTableModel;

import Service.MainImportService;

public class MainImportController {

	private MainImportService service = new MainImportService();
=======
import javax.swing.table.DefaultTableModel;

import Service.ImportService;

public class MainImportController {

	private ImportService service = new ImportService();
>>>>>>> bs

	public DefaultTableModel getImportModel(String[] header) {

		DefaultTableModel model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		System.out.println("임포트 컨트롤러 2차원 배열 값 넣기 전");
		
		Object[][] rowData = service.select();

		for (Object[] row : rowData) {
			model.addRow(row);
		}

		return model;
	}
<<<<<<< HEAD
========
public class ImportController {

>>>>>>>> bs:src/Controller/ImportController.java
=======
>>>>>>> bs
}
