package Service;

import DAO.LoginDAO;

public class LoginService {
	
	LoginDAO loginDAO = new LoginDAO();
	
	public boolean login(String id, String pass) {
		
		return loginDAO.login(id, pass);
		
		
	}



	public Object idfind(String jumin) {
		System.out.println("아이디 찾기 서비스 실행");
		return loginDAO.idfind(jumin);
	}



	public Object pwfind(String jumin, String uid) {
		System.out.println("비번 찾기 서비스 실행");
		return  loginDAO.pwfind(jumin,uid);
	}

}
