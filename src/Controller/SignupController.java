package Controller;

import Model.UsersModel;
import Service.SignupService;

public class SignupController {

	SignupService signupService = new SignupService();

	public boolean signup(UsersModel user) {
		return signupService.signup(user); // 서비스로 전송

	}

}
