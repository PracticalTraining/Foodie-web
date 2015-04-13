package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.edu.bjtu.foodie.bean.Category;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.dao.CategoryDao;
import cn.edu.bjtu.foodie.common.JsonUtil;

@Path("category")
public class CategoryRestfulService {
	CategoryDao categoryDao;

	// direct children links
	private JsonArray CategoryChildrenLinks;

	// get set method for spring IOC
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	{
		// initialize direct children links
		CategoryChildrenLinks = new JsonArray();
		RestfulServiceUtil.addChildrenLinks(CategoryChildrenLinks,
				"get category detail information", "/{id}", "GET");
		RestfulServiceUtil.addChildrenLinks(CategoryChildrenLinks,
				"update classification of dish", "/{id}", "PUT");
		RestfulServiceUtil.addChildrenLinks(CategoryChildrenLinks,
				"delete classification of dish", "/{id}", "DELETE");
	}

	/** add Category **/
	@POST
	public String addCategory(@FormParam("name") String name,
			@FormParam("restId") int restId) {
		JsonObject ret = new JsonObject();
		// define errorCode
		final int ERROR_CODE_BAD_PARAM = -1;

		// System.out.println(name);
		if (name.equals("") || name == null) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}

		// add one row to database
		Category category = new Category();
		category.setName(name);
		category.setRestId(restId);
		ret.addProperty("id", categoryDao.add(category));
		ret.add("links", CategoryChildrenLinks);
		return ret.toString();
	}

	/** get the detail info of Category by id **/
	@GET
	@Path("{id}")
	public String getDetailInfoById(@PathParam("id") String id) {
		JsonObject ret = new JsonObject();
		// define errorCode
		final int ERROR_CODE_NO_RESULT = -1;
		if (!categoryDao.isCategoryExist(id)) {
			ret.addProperty("erroecode", ERROR_CODE_NO_RESULT);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}

		// search the database
		Category categoryDetailInfo = categoryDao.searchCategoryDetailById(id);
		JsonObject jCategory = transformCategoryToJson(categoryDetailInfo);
		ret.add("category", jCategory);
		ret.add("links", CategoryChildrenLinks);
		return ret.toString();
	}

	/** update the Category **/
	@PUT
	@Path("{id}")
	public String updateCategory(@PathParam("id") String id,
			@PathParam("name") String name, @PathParam("restId") int restId) {
		JsonObject ret = new JsonObject();
		// define errorCode
		final int ERROR_CODE_BAD_PARAM = -1;
		final int ERROR_CODE_NO_RESULT = -2;

		// check bad request parameter
		if (name == null || name.equals("") || restId < 0) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}

		// search the database
		Category updateCategory = categoryDao.getById(id);
		if (updateCategory == null) {
			ret.addProperty("errorcode", ERROR_CODE_NO_RESULT);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}

		updateCategory.setName(name);
		updateCategory.setRestId(restId);
		categoryDao.update(updateCategory);
		ret.addProperty("id", id);
		ret.add("links", CategoryChildrenLinks);
		return ret.toString();
	}

	/** delete the category by id **/
	@DELETE
	@Path("{id}")
	public String deleteCategoryById(@PathParam("id") String id) {
		JsonObject ret = new JsonObject();
		// define errorCode
		final int ERROR_CODE_BAD_PARAM = -1;
		final int ERROR_CODE_DISHCATEGORY_NOT_EXIST = -2;
		if (id == null || id.equals("")) {
			ret.addProperty("errorcode", ERROR_CODE_BAD_PARAM);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}
		if (categoryDao.isCategoryExist(id) == false) {
			ret.addProperty("errorcode", ERROR_CODE_DISHCATEGORY_NOT_EXIST);
			ret.add("links", CategoryChildrenLinks);
			return ret.toString();
		}

		Category deleteCategory = categoryDao.searchCategoryDetailById(id);
		categoryDao.delete(deleteCategory);
		ret.addProperty("resule", 0);
		return ret.toString();
	}

	/**
	 * transform foodCategory from bean to json
	 * 
	 * @param category
	 * @return
	 */
	private JsonObject transformCategoryToJson(Category category) {
		JsonObject jCategory = JsonUtil.beanToJson(category);
		return jCategory;
	}

}
