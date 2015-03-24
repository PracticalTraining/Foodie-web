package cn.edu.bjtu.foodie.bean;

public class Admin {
	/** primary key generated by guid **/
	private String id;
	/** username **/
	private String name;
	/** password encrypted by md5 **/
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}
	
}
