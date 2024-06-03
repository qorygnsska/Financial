package Controller;

import javax.swing.table.DefaultTableModel;

import Model.SaveModel;
import Service.SaveService;

public class SaveController {
	private SaveService service = new SaveService();
	
	public DefaultTableModel getSaveModel(String[] header) {

		DefaultTableModel model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};
		
		String[][] rowData = service.saveMoney();

		for (String[] row : rowData) {
			model.addRow(row);
		}

		return model;
	}
	
	public void insert(SaveModel model) {
			service.insert(model);
	}
}
