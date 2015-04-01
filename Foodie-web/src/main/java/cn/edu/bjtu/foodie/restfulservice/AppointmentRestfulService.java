package cn.edu.bjtu.foodie.restfulservice;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cn.edu.bjtu.foodie.bean.Appointment;
import cn.edu.bjtu.foodie.bean.Book;
import cn.edu.bjtu.foodie.common.JsonUtil;
import cn.edu.bjtu.foodie.idao.IAppointmentDao;

import com.google.gson.JsonObject;

@Path("appointment")
public class AppointmentRestfulService {
	private IAppointmentDao appointmentDao;

	public IAppointmentDao getAppointmentDao() {
		return appointmentDao;
	}

	public void setAppointmentDao(IAppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}
	
	@POST
	public String add(@FormParam("foodieId")String foodieId, @FormParam("title")String title, 
			 @FormParam("des")String des,  @FormParam("restId")int restId, 
			 @FormParam("time")long time){
		JsonObject ret = new JsonObject();
		
		final int ERROR_CODE_BAD_PARAM = -1;
		if(foodieId == null || foodieId.equals("") || title == null || title.equals("") || 
				des == null || des.equals("") || restId <= 0
				|| time < 0){
			ret.addProperty("errorCode", ERROR_CODE_BAD_PARAM);
			return ret.toString();
		}
		
		Appointment appointment = new Appointment();
		appointment.setDes(des);
		appointment.setFoodieId(foodieId);
		appointment.setRestId(restId);
		appointment.setTime(new Date(time));
		appointment.setTitle(title);
		
		appointmentDao.add(appointment);
		return ret.toString();
	}
	
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id")String id){
		JsonObject ret = new JsonObject();
		
		appointmentDao.delete(id);
		ret.addProperty("result", 0);
		return ret.toString();
	}
	
	@GET
	@Path("{id}")
	public String get(@PathParam("id")String id){
		final int ERROR_CODE_NO_RESULT = -1;
		Appointment appointment = appointmentDao.getById(id);
		if(appointment != null)
			return JsonUtil.beanToJson(appointment).toString();
		else {
			JsonObject ret = new JsonObject();
			ret.addProperty("errorCode", ERROR_CODE_NO_RESULT);
			return ret.toString();
		}
	}
}
