package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.bean.Foodie;

import java.util.List;

public interface IFoodieDao {
	
	/** check if name is already exist **/
	int isNameExistByName(String name);
	int isNameExistById(String id);
	
}
