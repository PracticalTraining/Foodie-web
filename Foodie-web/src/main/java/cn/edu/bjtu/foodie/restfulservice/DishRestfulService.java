package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.edu.bjtu.foodie.bean.Dish;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.IDishDao;

@Path("/dish")
@Produces("application/json;charset=UTF-8")
public class DishRestfulService {
	// dao
	private IDishDao dishDao;

	// direct children links
	private JsonArray dishChildrenLinks;
	private JsonArray searchChildrenLinks;

	// get set method for spring IOC
	public IDishDao getDishDao() {
		return dishDao;
	}

	public void setDishDao(IDishDao dishDao) {
		this.dishDao = dishDao;
	}

	{
		// initialize direct children links
		dishChildrenLinks = new JsonArray();
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks, "search dish",
				"/search", "GET");
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks, "add dish",
				"/add", "POST");
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks,
				"update dish infor", "/update", "PUT");
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks, "delete dish",
				"/delete", "delete");
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks,
				"get restaurant id", "/restId", "GET");
		RestfulServiceUtil.addChildrenLinks(dishChildrenLinks,
				"get category id", "categoryId", "GET");
		searchChildrenLinks = new JsonArray();
	}

	/** add a dish **/
	@POST
	public String addDish(@FormParam("name") String name,
			@FormParam("price") int price, @FormParam("restId") int restId,
			@FormParam("categoryId") int categoryId) {

		JsonObject ret = new JsonObject();

		// define error code
		final int ERROR_CODE_DISH_EXIST = -1;
		final int ERROR_CODE_BAD_PARAM = -2;

		// check request parameters
		if (name == null || name.equals("") || price < 0 || restId < 0
				|| categoryId < 0) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", dishChildrenLinks);
			return ret.toString();
		}

		// check is dish already exists
		if (dishDao.isDishExistByName(name)) {
			ret.addProperty("errorCode", ERROR_CODE_DISH_EXIST);
			ret.add("links", dishChildrenLinks);
			return ret.toString();
		}

		// add one row to database
		Dish dish = new Dish();
		dish.setName(name);
		dish.setPrice(price);
		dish.setRestId(restId);
		dish.setCategoryId(categoryId);
		ret.addProperty("id", dishDao.addDish(dish));
		ret.add("links", dishChildrenLinks);
		return ret.toString();
	}
}
