package Controller;

import Model.AmountModel;
import Model.ExportModel;
import Model.ImportModel;
import Service.AmountService;

public class AmountController {

	AmountService amountService = new AmountService();
	
	public Object[][] selecet() {
		return amountService.select();
	}
	
	public void insert(AmountModel amountModel) {
		amountService.insert(amountModel);
	}

	public String amount() {
		return amountService.amount();
	}

	public void update(AmountModel amountModel) {
		amountService.update(amountModel);
	}

	public void delete(int rownum, String amounttype) {
		amountService.delete(rownum, amounttype);
	}

	public void fexinsert(ExportModel exportModel) {
		amountService.fexinsert(exportModel);
	}

	public void fiminsert(ImportModel importModel) {
		amountService.fiminsert(importModel);
	}

}
