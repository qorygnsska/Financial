package Controller;

import Model.UsersModel;
import Service.SignupService;

public class SignupController {

	SignupService signupService = new SignupService();

	public boolean signup(UsersModel user) {
		System.out.println("signup 컨트롤러 실행");
		return signupService.signup(user); // 서비스로 전송

	}

}
