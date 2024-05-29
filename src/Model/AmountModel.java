package Model;

public class AmountModel {
	
	private int id;
	private String day;
	private int price;
	private String type;
	private String content;
	private String memo;
	
	public AmountModel() {}
	
	public AmountModel(String day, int price, String type, String content, String memo) {
		this.day = day;
		this.price = price;
		this.type = type;
		this.content = content;
		this.memo = memo;
	}

	public int getId() {
		return id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "AmountModel [id=" + id + ", day=" + day + ", price=" + price + ", type=" + type + ", content=" + content
				+ ", memo=" + memo + "]";
	}
	
	
	
	
	
	
}
