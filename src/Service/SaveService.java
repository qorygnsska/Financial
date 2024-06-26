package Service;

import DAO.SaveDAO;
import Model.SaveModel;

public class SaveService {

	private SaveDAO dao = new SaveDAO();
	
	public String[][] saveMoney() {
		
		
		String[][] arr = dao.select();
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			arr[i][1] = String.format("%,d원", Integer.parseInt(arr[i][1]));
		}
			return arr;
	}

	public void insert(SaveModel model) {
		dao.insert(model);
	}
	
	public void update(SaveModel model) {
		dao.update(model);
	}
}
