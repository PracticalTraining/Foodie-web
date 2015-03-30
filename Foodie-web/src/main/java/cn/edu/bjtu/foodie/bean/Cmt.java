package cn.edu.bjtu.foodie.bean;

import java.util.Date;

public class Cmt {
	/** primary key generated by guid **/
	private String id;
	/** primary key of the foodie who write the comment **/
	private String foodieId;
	/** primary key of the commented date **/
	private String dateId;
	/** the content of the comment **/
	private String content;
	/** when the comment is written **/
	private Date time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFoodieId() {
		return foodieId;
	}
	public void setFoodieId(String foodieId) {
		this.foodieId = foodieId;
	}
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Cmt [id=" + id + ", foodieId=" + foodieId + ", dateId="
				+ dateId + ", content=" + content + ", time=" + time + "]";
	}

}
