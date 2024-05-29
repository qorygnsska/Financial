package Controller;

import javax.swing.table.DefaultTableModel;

import Service.ExportService;

public class ExportController {

	private ExportService service = new ExportService();
	
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
