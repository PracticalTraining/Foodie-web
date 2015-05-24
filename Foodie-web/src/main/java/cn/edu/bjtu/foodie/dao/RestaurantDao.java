package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.bean.Restaurant;
import cn.edu.bjtu.foodie.idao.IRestaurantDao;

public class RestaurantDao extends HibernateDaoSupport implements IRestaurantDao {
	public Restaurant getById(String id){
		return getHibernateTemplate().get(Restaurant.class, id);
	}
	
	public List<Restaurant> getAll(){
		List<Restaurant> list;
		list = getHibernateTemplate().find("from bean.Restaurant");
		return list;
	}
}
