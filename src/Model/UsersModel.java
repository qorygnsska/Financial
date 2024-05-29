package Model;

import DAO.UsersDAO;

public class UsersModel {
	
	private int id;
	private String user_id;
	private String user_pass;
	private String name;
	private String jumin;
	
	UsersDAO usersDAO = new UsersDAO();
	
	public static UsersModel user;
	
	public void save(String id, String pass) {
		user = usersDAO.save(id, pass);
	}

	
	public UsersModel() {}
	
	

	public UsersModel(int id, String user_id, String user_pass, String name, String jumin) {
		this.id = id;
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.name = name;
		this.jumin = jumin;
	}



	public UsersModel(String user_id, String user_pass, String name, String jumin) {
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.name = name;
		this.jumin = jumin;
	}



	public int getId() {
		return id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	@Override
	public String toString() {
		return "UsersModel [id=" + id + ", user_id=" + user_id + ", user_pass=" + user_pass + ", name=" + name
				+ ", jumin=" + jumin + "]";
	}
	
	
	
}
