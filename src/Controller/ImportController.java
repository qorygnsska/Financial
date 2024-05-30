package Controller;

import javax.swing.table.DefaultTableModel;

import Service.ImportService;

public class ImportController {
	private ImportService importService = new ImportService();

	public DefaultTableModel getImport(String[] header) {
		DefaultTableModel importModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		
		String[][] rowData = importService.select();
		
		for(String[] row: rowData) {
			importModel.addRow(row);
		}
		
		return importModel;
		
	}
}