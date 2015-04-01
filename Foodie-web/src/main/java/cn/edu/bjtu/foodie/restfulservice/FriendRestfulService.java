package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.IFriendDao;
import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.idao.IFoodieDao;

@Path("friend")
@Produces("application/json;charset=UTF-8")
public class FriendRestfulService {

	// dao
	private IFriendDao friendDao;
	private IFoodieDao foodieDao;

	// direct children links
	private JsonArray friendChildrenLinks;
	private JsonArray idChildrenLinks;
	private JsonArray searchChildrenLinks;

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
		// RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
		// "add friend", "/{id}", "POST");
		RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
				"delete friend", "/{id}", "DELETE");
		idChildrenLinks = new JsonArray();
		RestfulServiceUtil.addChildrenLinks(friendChildrenLinks,
				"search friend", "/search", "GET");
		searchChildrenLinks = new JsonArray();

	}

	/** add a friend **/
	@POST
	public String addFriend(@FormParam("name") String name,
			@FormParam("foodieId") String foodieid) {
		JsonObject ret = new JsonObject();
		// define error code
		final int ERROR_CODE_USER_NOT_EXIST = -1;
		final int ERROR_CODE_BAD_PARAM = -2;
		// check request parameters
		if (name == null || name.equals("")) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}
		// check if user name is already exist
		if (foodieDao.isNameExistByName(name) == "-1") {
			ret.addProperty("errorCode", ERROR_CODE_USER_NOT_EXIST);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}
		// add one row to database
		Friend friend = new Friend();
		friend.setFriendId(foodieDao.isNameExistByName(name));
		friend.setStatus(-1);
		friend.setFoodieId(foodieid);
		ret.addProperty("id", friendDao.add(friend));
		ret.add("links", idChildrenLinks);
		return ret.toString();
	}

	/** delete a friend **/
	@DELETE
	@Path("{id}")
	public String deleteFriend(@PathParam("id") String id) {
		JsonObject ret = new JsonObject();

		// delete one row
		friendDao.deleteFriend(id);
		ret.addProperty("result", 0);
		ret.add("links", idChildrenLinks);
		return ret.toString();
	}

	/** search a friend **/
	@GET
	@Path("search")
	public String searchFriend(@QueryParam("name") String name,
			@QueryParam("foodieId") String foodieid) {
		JsonObject ret = new JsonObject();
		// define error code
		final int ERROR_CODE_USER_NOT_EXIST = -1;
		final int ERROR_CODE_BAD_PARAM = -2;

		// check if user name is already exist
		if ((foodieDao.isNameExistByName(name) == "-1")&&!(name == null || name.equals(""))) {
			ret.addProperty("errorCode", ERROR_CODE_USER_NOT_EXIST);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}

		// check request parameters
		if (name == null || name.equals("")) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}

		
			Foodie resultfoodie = foodieDao.getById(foodieDao.isNameExistByName(name));
			//resultfoodie.setId(foodieDao.isNameExistByName(name));
			JsonObject jFoodie = new JsonObject();
			jFoodie.addProperty("id", resultfoodie.getId());
			jFoodie.addProperty("name", name);
			jFoodie.addProperty("picture", resultfoodie.getPicture());
			jFoodie.addProperty("phone", resultfoodie.getPhone());
			ret.add("friend", jFoodie);
			ret.add("links", searchChildrenLinks);
			return ret.toString();
	}

}
