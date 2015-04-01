package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.IFoodieDao;
import cn.edu.bjtu.foodie.idao.IFriendDao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FoodieRestfulService {

	//dao
		private IFriendDao friendDao;
		private IFoodieDao foodieDao;
		
		// direct children links
		private JsonArray friendChildrenLinks;
		private JsonArray idChildrenLinks;
		private JsonArray searchChildrenLinks;
		
		private JsonArray foodieChildrenLinks;
		private JsonArray foodieidChildrenLinks;
		private JsonArray searchfoodieChildrenLinks;
		
		// get set method for spring IOC
		
		public IFriendDao getFriendDao() {
			return friendDao;
		}

		public void setFriendDao(IFriendDao friendDao) {
			this.friendDao = friendDao;
		}
		
		public IFoodieDao getFoodieDao() {
			return foodieDao;
		}

		public void setFoodieDao(IFoodieDao foodieDao) {
			this.foodieDao = foodieDao;
		}
		
		
		{
			// initialize direct children links
			friendChildrenLinks = new JsonArray();
			RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
					"add friend", "/{id}", "POST");
			RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
					"delete friend", "/{id}", "DELETE");
			idChildrenLinks = new JsonArray();
			RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
					"search friend", "/search", "GET");
			searchChildrenLinks = new JsonArray();
			
			
		}
		
		/** login by name and password **/
		@GET
		@Path("login")
		public String login(@QueryParam("name")String name,@QueryParam("password") String password){
			JsonObject ret = new JsonObject();
			//define error code
			final int ERROR_CODE_USER_NOT_EXIST = -1;
			final int ERROR_CODE_PASSWORD_NOT_VALIDATED = -2;
			final int ERROR_CODE_BAD_PARAM = -3;
			//check request parameters
			if(name == null || name.equals("") || password == null || password.equals("")){
				ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
				ret.add("links", foodieChildrenLinks);
				return ret.toString();
			}
			//check if user name is exsit
			if(foodieDao.isNameExistByName(name)==-1){
				ret.addProperty("errorCode", ERROR_CODE_USER_NOT_EXIST);
				ret.add("links", friendChildrenLinks);
				return ret.toString();
			}
			//check password
			int loginResult = foodieDao.login(name, password);
			if(loginResult == -1){
				ret.addProperty("errorCode", ERROR_CODE_PASSWORD_NOT_VALIDATED);
				ret.add("links", friendChildrenLinks);
				return ret.toString();
			}
			ret.addProperty("id", loginResult);
			ret.add("links", friendChildrenLinks);
			return ret.toString();
		}
		
		/** search information by id **/
		@GET
		@Path("{id}")
		public String getById(@PathParam("id") int id){
			JsonObject ret = new JsonObject();
			//define error code
			final int ERROR_CODE_FOODIE_NOT_EXIST = -1;
			Foodie foodie = foodieDao.getById(id);
			if( foodie== null)
			{
				ret.addProperty("errorCode", ERROR_CODE_FOODIE_NOT_EXIST);
				ret.add("links", idChildrenLinks);
				return ret.toString();
			}
			ret.addProperty("id", id);
			ret.addProperty("name", foodie.getName());
			ret.addProperty("phone", foodie.getPhone());
			ret.addProperty("picture", foodie.getPicture());
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}
		
		/** update foodie information **/
		@PUT
		@Path("{id}")
		public String updateFoodie(@PathParam("id") int id,
				@FormParam("name") String  name,@FormParam("picture") String picture,
				@FormParam("phone") int phone){
			JsonObject ret = new JsonObject();
			//define error code
			final int ERROR_CODE_FOODIE_NOT_EXIST = -1;
			final int ERROR_CODE_BAD_PARAM = -2;
			//check request parameters
			Integer integer=new Integer(phone);
			if(id <= 0 || name == null || name.equals("")
			        ||integer.toString().length()!=11){
			           ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
		               ret.add("links", idChildrenLinks);
			           return ret.toString();
			    }
			//check if foodie exsit
			Foodie foodie = foodieDao.getById(id);
			if(foodie == null)
			{
				ret.addProperty("errorCode", ERROR_CODE_FOODIE_NOT_EXIST);
				ret.add("links", idChildrenLinks);
				return ret.toString();
			}
			//update the database
			foodie.setName(name);
			foodie.setPhone(phone);
			foodie.setPicture(picture);
			ret.addProperty("result", 0);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}
		
}
