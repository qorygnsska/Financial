package Service;

import DAO.AmountDAO;
import Model.AmountModel;
import Model.ExportModel;
import Model.ImportModel;

public class AmountService {
	AmountDAO amountDAO = new AmountDAO();

	public Object[][] select() {
		return amountDAO.selecet();
	}
	
	
	public void insert(AmountModel amountModel) {
		amountDAO.insert(amountModel);
	}

	public String amount() {
		return amountDAO.amount();
	}

	public void update(AmountModel amountModel) {
		amountDAO.update(amountModel);
	}

	public void delete(int rownum, String amounttype) {
		amountDAO.delete(rownum, amounttype);
	}

	public void fexinsert(ExportModel exportModel) {
		amountDAO.fexinsert(exportModel);
	}

	public void fiminsert(ImportModel importModel) {
		amountDAO.fiminsert(importModel);
	}

}
