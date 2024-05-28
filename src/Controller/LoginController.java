package Controller;

import Service.LoginService;

public class LoginController {
	
	LoginService loginService = new LoginService();

	public boolean login(String id, String pass) {
		
		System.out.println("로그인 컨트롤러 실행");
		
		return loginService.login(id, pass); // 서비스로 전송
		
	}

}
