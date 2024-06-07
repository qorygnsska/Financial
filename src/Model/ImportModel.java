package Model;

public class ImportModel {
	private int id;
	private String day;
	private int price;
	private int type_id;
	private String memo;
	private int idnum;
	public ImportModel() {
	}
	
	public ImportModel(int id, String day, int price, int type_id, String memo) {
		this.id = id;
		this.day = day;
		this.price = price;
		this.type_id = type_id;
		this.memo = memo;
	}
	
	
	
	public ImportModel(int id, String day, int price, int type_id, String memo, int idnum) {
		super();
		this.id = id;
		this.day = day;
		this.price = price;
		this.type_id = type_id;
		this.memo = memo;
		this.idnum = idnum;
	}
	

	public int getIdnum() {
		return idnum;
	}

	public void setIdnum(int idnum) {
		this.idnum = idnum;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ImportModel [id=" + id + ", day=" + day + ", price=" + price + ", type_id=" + type_id + ", memo=" + memo
				+ ", idnum=" + idnum + "]";
	}
	
}
