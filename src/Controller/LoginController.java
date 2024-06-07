package Controller;

import Service.LoginService;

public class LoginController {
	
	LoginService loginService = new LoginService();

	public boolean login(String id, String pass) {
		
		return loginService.login(id, pass); // 서비스로 전송
		
	}



	public Object idfind(String jumin) {
		return loginService.idfind(jumin);
	}



	public Object pwfind(String jumin, String uid) {
		return loginService.pwfind(jumin,uid);
	}

	

}
