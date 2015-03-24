package cn.edu.bjtu.foodie.bean;

public class Foodie {
	/** primary key generated by guid **/
	private String id;
	/** username **/
	private String name;
	/** password encrypted by md5 **/
	private String password;
	/** foodie's photo **/
	private String picture;
	/** foodie's phone number **/
	private String phone;
	
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Foodie [id=" + id + ", name=" + name + ", password=" + password
				+ ", picture=" + picture + ", phone=" + phone + "]";
	}
	
}
