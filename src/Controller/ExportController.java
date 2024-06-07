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

		exportModel.setRowCount(0);
		String[][] rowData = exportService.select();

		for (String[] row : rowData) {
			exportModel.addRow(row);
		}

		return exportModel;

	}

	public boolean add(ExportModel exportModel) {
		return exportService.add(exportModel);
	}

	public boolean update(ExportModel exportModel) {
		return exportService.update(exportModel);
	}

	// 일별조회
	public DefaultTableModel getExportdayselect(String[] header) {
		DefaultTableModel exportModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		exportModel.setRowCount(0);
		String[][] rowData = exportService.getExportdayselect();

		for (String[] row : rowData) {
			exportModel.addRow(row);
		}

		return exportModel;
	}

	// 월별 조회
	public DefaultTableModel getExportmonthselect(String[] header) {
		DefaultTableModel exportModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		exportModel.setRowCount(0);
		String[][] rowData = exportService.getExportmonthselect();

		for (String[] row : rowData) {
			exportModel.addRow(row);
		}

		return exportModel;
	}

	public boolean delete(ExportModel exportmodel) {
		return exportService.delete(exportmodel);
	}
	
	public void check() {
		exportService.check();
	}

	public int dayupdate(ExportModel exportModel) {
		
		return exportService.dayupdate(exportModel);
	}

	public boolean monthupdate(ExportModel exportModel) {
		
		return  exportService.monthupdate(exportModel);
	}

	public int daydelete(ExportModel exportmodel) {
		
		return exportService.daydelete(exportmodel);
	}

	public boolean monthdelete(ExportModel exportmodel) {
		
		return exportService.monthdelete(exportmodel);
	}
}
