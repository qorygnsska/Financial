package Controller;

import javax.swing.table.DefaultTableModel;

import Model.ImportModel;
import Service.ImportService;

public class ImportController {
	private ImportService importService = new ImportService();

	public DefaultTableModel getImport(String[] header) {
		DefaultTableModel importModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		importModel.setRowCount(0);
		String[][] rowData = importService.select();

		for (String[] row : rowData) {
			importModel.addRow(row);
		}

		return importModel;

	}

	public boolean add(ImportModel importModel) {
		return importService.add(importModel);
	}

	public boolean update(ImportModel importmodel) {
		return importService.update(importmodel);
	}

	public DefaultTableModel getImportdayselect(String[] header) {
		DefaultTableModel importModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		importModel.setRowCount(0);
		String[][] rowData = importService.getImportdayselect();

		for (String[] row : rowData) {
			importModel.addRow(row);
		}

		return importModel;

	}

	public DefaultTableModel getImportmonthselect(String[] header) {
		DefaultTableModel importModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		importModel.setRowCount(0);
		String[][] rowData = importService.getImportmonthselect();

		for (String[] row : rowData) {
			importModel.addRow(row);
		}

		return importModel;
	}

	public boolean delete(ImportModel importmodel) {
		return importService.delete(importmodel);
	}
	
	public void check() {
		importService.check();
	}

	public int dayupdate(ImportModel importmodel) {
		// TODO Auto-generated method stub
		return importService.dayupdate(importmodel);
	}

	public boolean monthupdate(ImportModel importmodel) {
		// TODO Auto-generated method stub
		return importService.monthupdate(importmodel);
	}

	public int daydelete(ImportModel importmodel) {
		
	return importService.daydelete(importmodel);
	}

	public boolean monthdelete(ImportModel importmodel) {
		
		return importService.monthdelete(importmodel);
	}
}
