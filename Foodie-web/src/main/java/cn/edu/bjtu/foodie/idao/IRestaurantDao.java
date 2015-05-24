package cn.edu.bjtu.foodie.idao;


import java.util.List;

import cn.edu.bjtu.foodie.bean.Restaurant;


public interface IRestaurantDao {
	Restaurant getById(String id);
	List<Restaurant> getAll();
}
