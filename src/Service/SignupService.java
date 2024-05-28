package Service;

import DAO.SignupDAO;
import Model.UsersModel;

public class SignupService {
	SignupDAO signupDAO = new SignupDAO();
	
	public boolean signup(UsersModel user) {
		
		System.out.println("signup 서비스 실행");
		return signupDAO.signup(user); // DAO로 전송
		
		
	}

}
