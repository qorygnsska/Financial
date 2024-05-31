package Controller;

import javax.swing.table.DefaultTableModel;

import Service.MainImportService;

public class MainImportController {

	private MainImportService service = new MainImportService();

	public DefaultTableModel getImportModel(String[] header) {

		DefaultTableModel model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		System.out.println("임포트 컨트롤러 2차원 배열 값 넣기 전");
		
		String[][] rowData = service.select();

		for (String[] row : rowData) {
			model.addRow(row);
		}

		return model;
	}
}