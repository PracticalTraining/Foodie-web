package cn.edu.bjtu.foodie.restfulservice;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.bean.Restaurant;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.IRestaurantDao;



@Path("restaurant")
@Produces("application/json;charset=UTF-8")
public class RestaurantRestfulService {

	// dao
	private IRestaurantDao restaurantDao;
	

	// direct children links
	private JsonArray friendChildrenLinks;
	private JsonArray idChildrenLinks;
	private JsonArray searchChildrenLinks;

	
	private JsonArray restaurantChildrenLinks;
	private JsonArray restaurantidChildrenLinks;
	private JsonArray searchrestaurantChildrenLinks;
	

	// get set method for spring IOC

	public IRestaurantDao getRestaurantDao() {
		return restaurantDao;
	}

	public void setRestaurantDao(IRestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}



	{
		
		RestfulServiceUtil.addChildrenLinks(searchrestaurantChildrenLinks,
				"search friend", "/search", "GET");
		searchrestaurantChildrenLinks = new JsonArray();

	}

	/** get restaurant **/
	@GET
	@Path("search")
	public String searchRestaurant(@QueryParam("id") String id) {
		JsonObject ret = new JsonObject();
		Restaurant resultrestaurant = restaurantDao.getById(id);
		JsonObject jRestaurant = new JsonObject();
		jRestaurant.addProperty("id", resultrestaurant.getId());
		jRestaurant.addProperty("name", resultrestaurant.getName());
		jRestaurant.addProperty("picture", resultrestaurant.getPictureUrl());
		jRestaurant.addProperty("description", resultrestaurant.getDescription());
		jRestaurant.addProperty("longitude", resultrestaurant.getLongitude());
		jRestaurant.addProperty("latitude", resultrestaurant.getLatitude());

		//////////////////////////
			/*Foodie resultfoodie = foodieDao.getById(foodieDao.isNameExistByName(name));
			//resultfoodie.setId(foodieDao.isNameExistByName(name));
			JsonObject jFoodie = new JsonObject();
			jFoodie.addProperty("id", resultfoodie.getId());
			jFoodie.addProperty("name", name);
			jFoodie.addProperty("picture", resultfoodie.getPicture());
			jFoodie.addProperty("phone", resultfoodie.getPhone());*/
			ret.add("restaurant", jRestaurant);
			ret.add("links", searchrestaurantChildrenLinks);
			return ret.toString();
	}

/** get all restaurant **/
@GET
@Path("searchall")
public String searchAllRestaurant() {
	JsonObject ret = new JsonObject();
	List<Restaurant> listResult = restaurantDao.getAll();
	JsonArray restaurants = new JsonArray();
    for(Restaurant restaurant:listResult){
    	JsonObject jRest = new JsonObject();
    	jRest.addProperty("id",restaurant.getId());
    	jRest.addProperty("name",restaurant.getName());
    	jRest.addProperty("longitude",restaurant.getLongitude());
    	jRest.addProperty("latitude",restaurant.getLatitude());
    	jRest.addProperty("description",restaurant.getDescription());
    	jRest.addProperty("pictureUrl",restaurant.getPictureUrl());
    	
    	restaurants.add(jRest);
    }
    ret.add("restaurants",restaurants);
	ret.add("links", searchChildrenLinks);
    
		return ret.toString();

}
}

