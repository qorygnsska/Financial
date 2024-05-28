package Service;

import DAO.LoginDAO;

public class LoginService {
	
	LoginDAO loginDAO = new LoginDAO();
	
	public boolean login(String id, String pass) {
		System.out.println("로그인 서비스 실행");
		
		return loginDAO.login(id, pass);
		
		
	}

}
