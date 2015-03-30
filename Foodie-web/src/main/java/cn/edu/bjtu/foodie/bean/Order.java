package cn.edu.bjtu.foodie.bean;

public class Order {
	/** primary key generated by guid **/
	private String id;
	/** primary keys of dish **/
	private String dishIds;
	/** primary key of foodie **/
	private String foodieId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDishIds() {
		return dishIds;
	}
	public void setDishIds(String dishIds) {
		this.dishIds = dishIds;
	}
	public String getFoodieId() {
		return foodieId;
	}
	public void setFoodieId(String foodieId) {
		this.foodieId = foodieId;
	}
}