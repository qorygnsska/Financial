package Controller;

import Service.LoginService;

public class LoginController {
	
	LoginService loginService = new LoginService();

	public boolean login(String id, String pass) {
		
		return loginService.login(id, pass); // 서비스로 전송
		
	}



	public Object idfind(String jumin) {
		System.out.println("아이디 찾기 컨트롤러실행");
		return loginService.idfind(jumin);
	}



	public Object pwfind(String jumin, String uid) {
		System.out.println("비번 찾기 컨트롤러실행");
		return loginService.pwfind(jumin,uid);
	}

	

}
