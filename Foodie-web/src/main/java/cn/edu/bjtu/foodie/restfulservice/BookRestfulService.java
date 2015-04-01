package cn.edu.bjtu.foodie.restfulservice;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cn.edu.bjtu.foodie.bean.Book;
import cn.edu.bjtu.foodie.common.JsonUtil;
import cn.edu.bjtu.foodie.idao.IBookDao;

import com.google.gson.JsonObject;

@Path("book")
public class BookRestfulService {
	private IBookDao bookDao;

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	@POST
	public String applyBook(@FormParam("foodieId")String foodieId,
			@FormParam("restId")int restId, @FormParam("time")long time){
		JsonObject ret = new JsonObject();

		final int ERROR_CODE_BAD_PARAM = -1;
		if(foodieId == null || foodieId.equals("") || restId <= 0 || time < 0){
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			return ret.toString();
		}
		Book book = new Book();
		book.setFoodieId(foodieId);
		book.setRestId(restId);
		book.setTime(new Date(time));
		book.setStatus(0);
		int max = bookDao.getMaxBookNumber(restId);
		book.setBookNumber(max + 1);
		bookDao.add(book);
		ret.addProperty("booknumber", max + 1);
		return ret.toString();
	}
	
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id")String id){
		JsonObject ret = new JsonObject();
		
		bookDao.delete(id);
		ret.addProperty("result", 0);
		return ret.toString();
	}
	
	@GET
	@Path("{id}")
	public String get(@PathParam("id")String id){
		final int ERROR_CODE_NO_RESULT = -1;
		Book book = bookDao.getById(id);
		if(book != null)
			return JsonUtil.beanToJson(book).toString();
		else {
			JsonObject ret = new JsonObject();
			ret.addProperty("errorCode", ERROR_CODE_NO_RESULT);
			return ret.toString();
		}
	}
}
