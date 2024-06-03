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
		
		for(String[] row: rowData) {
			importModel.addRow(row);
		}
		
		return importModel;
		
	}
	
	public boolean add(ImportModel importModel) {
		System.out.println("(ImportController)지출 내역 추가 중");
		return importService.add(importModel);
	}

	public boolean update(ImportModel importmodel) {
		System.out.println("(IMportController)실행중");
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
		
		for(String[] row: rowData) {
			importModel.addRow(row);
		}
		
		return importModel;
		
	}
	}
