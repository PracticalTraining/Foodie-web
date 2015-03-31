package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.idao.IFriendDao;
import cn.edu.bjtu.foodie.idao.IFoodieDao;


public class FoodieDao extends HibernateDaoSupport implements IFoodieDao {

	
	public int isNameExistByName(String name){
		List<Foodie> list = getHibernateTemplate().find("from Foodie where name = ?", name);
		return list.isEmpty() ? -1 : Integer.parseInt(list.get(0).getId());
		
	}
	
	public int isNameExistById(String id){
		List<Foodie> list = getHibernateTemplate().find("from Foodie where id = ?", id);
		return list.isEmpty() ? -1 : Integer.parseInt(list.get(0).getId());
	}
	public Foodie getById(String id){
		return getHibernateTemplate().get(Foodie.class, id);
	}

	public int add(Foodie foodie) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Foodie> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public int login(String name, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Foodie getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Foodie foodie) {
		// TODO Auto-generated method stub
		
	}
}
