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
	
	// 선택한 날짜의 값이 있는지 확인
	public boolean selectCheck(String date) {
		System.out.println("(ExportController)선택한 날짜 조회 중");
		return exportService.selectCheck(date);
	}
	
	// 선택한 날짜의 값을 저장하는 메서드
	public DefaultTableModel getDayExport(String[] header, String date) {
		DefaultTableModel exportModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		
		String[][] rowData = exportService.selectDay(date);
		
		for(String[] row: rowData) {
			exportModel.addRow(row);
		}
		
		
		return exportModel;
	}
}