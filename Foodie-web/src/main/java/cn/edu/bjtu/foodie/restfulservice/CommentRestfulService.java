package cn.edu.bjtu.foodie.restfulservice;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cn.edu.bjtu.foodie.bean.Cmt;
import cn.edu.bjtu.foodie.common.JsonUtil;
import cn.edu.bjtu.foodie.common.RestfulServiceUtil;
import cn.edu.bjtu.foodie.idao.ICommentDao;
import cn.edu.bjtu.foodie.idao.IFoodieDao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("comment")
public class CommentRestfulService {
	private JsonArray commentsChildrenLinks = new JsonArray();
	private ICommentDao commentDao;
	private IFoodieDao foodieDao;

	public IFoodieDao getFoodieDao() {
		return foodieDao;
	}

	public void setFoodieDao(IFoodieDao foodieDao) {
		this.foodieDao = foodieDao;
	}

	public ICommentDao getiCommentDao() {
		return commentDao;
	}

	public void setiCommentDao(ICommentDao iCommentDao) {
		this.commentDao = iCommentDao;
	}

	{
		RestfulServiceUtil.addChildrenLinks(commentsChildrenLinks,
				"get the comment according to id ", "/{id}", "GET");
		RestfulServiceUtil.addChildrenLinks(commentsChildrenLinks,
				"delete comment according to id", "/{id}", "DELETE");
	}

	@POST
	public String addComment(@FormParam("foodieId") String foodieId,
			@FormParam("dateId") String dateId,
			@FormParam("content") String content, @FormParam("time") Date time) {
		JsonObject ret = new JsonObject();
		final int ERROR_CODE_BAD_PARAM = -1;
		Cmt my_comments = new Cmt();
		int nameExistById = foodieDao.isNameExistById(foodieId);
		if (content == null || time == null || nameExistById == -1
				|| dateId == null) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", this.commentsChildrenLinks);
			return ret.toString();
		}
		my_comments.setFoodieId(foodieId);

		my_comments.setContent(content);
		my_comments.setDateId(dateId);
		my_comments.setTime(time);
		this.commentDao.add(my_comments);
		ret.addProperty("result", 0);
		ret.add("links", this.commentsChildrenLinks);
		return ret.toString();
	}

	@DELETE
	@Path("{id}")
	public String deleteComment(@PathParam("id") int id) {

		final int ERROR_CODE_COMMENT_DOES_NOT_EXITS = -1;
		final int ERROR_CODE_BAD_PARAM = -2;
		Cmt my_comment = new Cmt();
		JsonObject ret = new JsonObject();
		int cmtExist = this.commentDao.isCmtExist(id + "");
		if (cmtExist == -1) {
			ret.addProperty("errorCode", ERROR_CODE_COMMENT_DOES_NOT_EXITS);
			ret.add("links", this.commentsChildrenLinks);
			return ret.toString();
		}
		if (id < 0) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", this.commentsChildrenLinks);
			return ret.toString();
		}
		this.commentDao.deletebyId(id + "");
		ret.addProperty("result", 0);
		ret.add("links", this.commentsChildrenLinks);
		return ret.toString();
	}

	@GET
	@Path("{id}")
	public String getCommentById(@PathParam("id") int id) {
		JsonObject ret = new JsonObject();
		final int ERROR_CODE_FOOD_DOES_NOT_EXIST = -1;
		final int ERROR_CODE_BAD_PARAM = -2;
		int cmtExist = this.commentDao.isCmtExist(id + "");
		if (cmtExist == -1) {
			ret.addProperty("errorCode", ERROR_CODE_FOOD_DOES_NOT_EXIST);
			ret.add("links", this.commentsChildrenLinks);
			return ret.toString();
		}
		if (id < 0) {
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			ret.add("links", this.commentsChildrenLinks);
			return ret.toString();
		}
		Cmt cmt = this.commentDao.getById(id + "");
		JsonObject commentJson = transformCommentToJson(cmt);
		ret.add("commentJson", commentJson);
		ret.add("links", commentsChildrenLinks);
		return ret.toString();
	}

	/**
	 * transform comment from bean to json
	 * 
	 * @param comment
	 * @return
	 */
	private JsonObject transformCommentToJson(Cmt comment) {
		JsonObject commentJsonObj = JsonUtil.beanToJson(comment);
		return commentJsonObj;
	}
}
