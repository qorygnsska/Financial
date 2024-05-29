package Controller;

import javax.swing.table.DefaultTableModel;

import Service.ImportService;

public class ImportController {

	private ImportService service = new ImportService();

	public DefaultTableModel getImportModel(String[] header) {

		DefaultTableModel model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		Object[][] rowData = service.select();

		for (Object[] row : rowData) {
			model.addRow(row);
		}

		return model;
	}
}
