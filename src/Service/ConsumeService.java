package Service;

import DAO.ConsumeDAO;

public class ConsumeService {

	private ConsumeDAO dao = new ConsumeDAO();
	
	public String[][] consumeTag(){
		return dao.select();
	}
}
