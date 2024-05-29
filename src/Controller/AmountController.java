package Controller;

import Service.AmountService;

public class AmountController {

	AmountService amountService = new AmountService();
	
	public Object[][] selecet() {
		System.out.println("어마운트 컨트롤러 실행!!!!!!!!!!!");
		return amountService.select();
	}

}
