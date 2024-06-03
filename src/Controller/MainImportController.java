package Controller;

import javax.swing.table.DefaultTableModel;

import Service.MainImportService;

public class MainImportController {

	private MainImportService service = new MainImportService();

	public DefaultTableModel getImportModel(String[] header) {

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
	
	public String thisMonth() {
		return service.thisMonth();
	}
	
	public String beforeMonth() {
		return service.beforeMonth();
	}
}