package Service;

import DAO.SaveDAO;

public class SaveService {

	private SaveDAO dao = new SaveDAO();
	
	public String[][] saveMoney() {
		return dao.select();
	}
}
