package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.JsonObject;

@Path("/")
public class TestRestfulService {
	@GET
    public String sayHello(@PathParam("name")String name) {
		JsonObject jObj = new JsonObject();
		jObj.addProperty("test_string", "this is a test string.....");
        return jObj.toString();
    }
}
