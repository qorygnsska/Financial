package Service;

import DAO.AmountDAO;

public class AmountService {
	AmountDAO amountDAO = new AmountDAO();
	
	public Object[][] select() {
		System.out.println("amount 서비스 실행!!!!!!!!!!!!!");
		return amountDAO.selecet();
	}

}
