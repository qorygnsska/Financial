package Controller;

import javax.swing.table.DefaultTableModel;

import Service.MainExportService;

public class MainExportController {

	private MainExportService service = new MainExportService();
	
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
}