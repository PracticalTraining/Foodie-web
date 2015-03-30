package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.JsonArray;

import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.IFriendDao;

@Path("friend")
@Produces("application/json")
public class FriendRestfulService {
	
	//dao
	private IFriendDao friendDao;
	
	// direct children links
	private JsonArray friendChildrenLinks;
	private JsonArray idChildrenLinks;
	private JsonArray searchChildrenLinks;
	
	
	// get set method for spring IOC
	
	public IFriendDao getAdminDao() {
		return friendDao;
	}

	public void setFriendDao(IFriendDao friendDao) {
		this.friendDao = friendDao;
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

}
