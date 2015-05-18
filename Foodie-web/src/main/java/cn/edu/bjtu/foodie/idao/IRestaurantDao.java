package cn.edu.bjtu.foodie.idao;


import cn.edu.bjtu.foodie.bean.Restaurant;

public interface IRestaurantDao {
	Restaurant getById(String id);
	
}
