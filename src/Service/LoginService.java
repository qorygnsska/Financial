package Service;

import DAO.LoginDAO;

public class LoginService {
	
	LoginDAO loginDAO = new LoginDAO();
	
	public boolean login(String id, String pass) {
		
		return loginDAO.login(id, pass);
		
		
	}



	public Object idfind(String jumin) {
		return loginDAO.idfind(jumin);
	}



	public Object pwfind(String jumin, String uid) {
		return  loginDAO.pwfind(jumin,uid);
	}

}
