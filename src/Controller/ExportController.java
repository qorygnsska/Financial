package Controller;

import javax.swing.table.DefaultTableModel;

import Model.ExportModel;
import Service.ExportService;

public class ExportController {
	private ExportService exportService = new ExportService();

	public DefaultTableModel getExport(String[] header) {
		DefaultTableModel exportModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		
		String[][] rowData = exportService.select();
		
		for(String[] row: rowData) {
			exportModel.addRow(row);
		}
		
		return exportModel;
		
	}

	public boolean add(ExportModel exportModel) {
		System.out.println("(ExportController)지출 내역 추가 중");
		return exportService.add(exportModel);
	}
}