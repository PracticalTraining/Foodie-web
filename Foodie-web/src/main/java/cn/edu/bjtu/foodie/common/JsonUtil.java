package cn.edu.bjtu.foodie.common;

import java.lang.reflect.Field;

import com.google.gson.JsonObject;

public class JsonUtil {
	/*
	 * transform java bean to json
	 */
	public static JsonObject beanToJson(Object bean){
		JsonObject json = new JsonObject();
		Field[] fields = bean.getClass().getDeclaredFields();
		Field.setAccessible(fields,true);
		try {
			for (Field field : fields) {
				json.addProperty(field.getName(), field.get(bean) == null ? "" : field.get(bean).toString());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return json;
	}
}
