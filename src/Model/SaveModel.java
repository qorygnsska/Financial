package Model;

public class SaveModel {

	private int user_id;
	private int price;
	private String day;
	private int type_id;
	private String memo;
	private int rowNum;
	public SaveModel(int user_id, int price, String day, int type_id, String memo, int rowNum) {
		
		this.user_id = user_id;
		this.price = price;
		this.day = day;
		this.type_id = type_id;
		this.memo = memo;
		this.rowNum = rowNum;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
		
}
