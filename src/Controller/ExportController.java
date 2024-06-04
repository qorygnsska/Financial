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
		System.out.println("(ExportController)지출 내역 추가 중");
		return exportService.add(exportModel);
	}

	public boolean update(ExportModel exportModel) {
		System.out.println("(ExportController)실행중");
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
		System.out.println("(ExportController)실행중");
		return exportService.delete(exportmodel);
	}
}
