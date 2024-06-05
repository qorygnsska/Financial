package Service;

import DAO.SignupDAO;
import Model.UsersModel;

public class SignupService {
	SignupDAO signupDAO = new SignupDAO();
	
	public boolean signup(UsersModel user) {
		
		return signupDAO.signup(user); // DAO로 전송
		
		
	}

}
