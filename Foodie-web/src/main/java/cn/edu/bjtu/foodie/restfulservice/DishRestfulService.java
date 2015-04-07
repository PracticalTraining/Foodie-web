package cn.edu.bjtu.foodie.restfulservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.edu.bjtu.foodie.bean.Dish;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.ICategoryDao;
import cn.edu.bjtu.foodie.idao.IDishDao;

@Path("/dish")
@Produces("application/json;charset=UTF-8")
public class DishRestfulService {
	// dao
	private IDishDao dishDao;
	private ICategoryDao categoryDao;

	// direct children links
	private JsonArray dishChildrenLinks;
	private JsonArray searchChildrenLinks;
	private JsonArray idChildrenLinks;

	// get set method for spring IOC
	public IDishDao getDishDao() {
		return dishDao;
	}

	public void setDishDao(IDishDao dishDao) {
		this.dishDao = dishDao;
	}

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
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

	/** get a dish **/
	@GET
	@Path("search")
	public String getDish(@QueryParam("name") String name) {
		JsonObject ret = new JsonObject();

		final int ERROR_CODE_BAD_PARAM = -1;

		// check request parameters
		if (name == null || name.equals("")) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", searchChildrenLinks);
			return ret.toString();
		}

		List<Dish> list = dishDao.getByName(name);
		JsonArray dishs = new JsonArray();
		for (Dish dish : list) {
			JsonObject jDish = new JsonObject();
			jDish.addProperty("id", dish.getId());
			jDish.addProperty("name", dish.getName());
			jDish.addProperty("price", dish.getPrice());
			jDish.addProperty("restId", dish.getRestId());
			jDish.addProperty("categoryId", dish.getCategoryId());

			dishs.add(jDish);
		}
		ret.add("dish", dishs);
		ret.add("links", searchChildrenLinks);
		return ret.toString();
	}

	/** get dishes **/
	@GET
	@Path("searchdish")
	public String getDishes() {
		JsonObject ret = new JsonObject();

		List<Dish> list = dishDao.getDishes();
		JsonArray dishs = new JsonArray();
		for (Dish dish : list) {
			JsonObject jDish = new JsonObject();
			jDish.addProperty("id", dish.getId());
			jDish.addProperty("name", dish.getName());
			jDish.addProperty("price", dish.getPrice());
			jDish.addProperty("restId", dish.getRestId());
			jDish.addProperty("categoryId", dish.getCategoryId());

			dishs.add(jDish);
		}
		ret.add("dish", dishs);
		ret.add("links", searchChildrenLinks);
		return ret.toString();
	}

	/** update dish by id **/
	@PUT
	@Path("{id}")
	public String updateById(@PathParam("id") String id,
			@PathParam("name") String name, @PathParam("price") int price,
			@PathParam("restId") int restId,
			@PathParam("categoryId") int categoryId) {
		JsonObject ret = new JsonObject();

		// define error code
		final int ERROR_CODE_DISH_NOT_EXIST = -1;
		final int ERROR_CODE_BAD_PARAM = -2;
		final int ERROR_CODE_CATEGORY_NOT_EXIST = -3;

		// check request parameters
		if (name == null || name.equals("") || price < 0 || restId < 0
				|| categoryId < 0) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}

		// check if dish is not exist
		Dish dish = dishDao.getById(id);
		if (dish == null) {
			ret.addProperty("errorCode", ERROR_CODE_DISH_NOT_EXIST);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}

		// check if category exists
		if (!categoryDao.isCategoryExist(id)) {
			ret.addProperty("errorcode", ERROR_CODE_CATEGORY_NOT_EXIST);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}

		dish.setName(name);
		dish.setPrice(price);
		dish.setRestId(restId);
		dish.setCategoryId(categoryId);
		dishDao.updateDish(dish);
		ret.addProperty("result", 0);
		ret.add("links", idChildrenLinks);
		return ret.toString();
	}

	/** delete food by id **/
	@DELETE
	@Path("{id}")
	public String deleteDish(@PathParam("id") String id) {
		JsonObject ret = new JsonObject();
		// define error code
		final int ERROR_CODE_DISH_NOT_EXIST = -1;
		Dish dish = dishDao.getById(id);

		// check if food is exist
		if (dish == null) {
			ret.addProperty("errorcode", ERROR_CODE_DISH_NOT_EXIST);
			ret.add("links", idChildrenLinks);
			return ret.toString();
		}
		dishDao.deleteById(id);
		ret.addProperty("result", 0);
		ret.add("links", idChildrenLinks);
		return ret.toString();
	}
}
