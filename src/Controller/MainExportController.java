package Controller;

<<<<<<< HEAD
<<<<<<<< HEAD:src/Controller/MainExportController.java
import javax.swing.table.DefaultTableModel;

import Service.MainExportService;

public class MainExportController {

	private MainExportService service = new MainExportService();
=======
import javax.swing.table.DefaultTableModel;

import Service.ExportService;

public class MainExportController {

	private ExportService service = new ExportService();
>>>>>>> bs
	
	public DefaultTableModel getExportModel(String[] header) {

		DefaultTableModel model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		String[][] rowData = service.select();

		for (String[] row : rowData) {
			model.addRow(row);
		}

		return model;
	}
<<<<<<< HEAD
========
public class ExportController {

>>>>>>>> bs:src/Controller/ExportController.java
=======
>>>>>>> bs
}
