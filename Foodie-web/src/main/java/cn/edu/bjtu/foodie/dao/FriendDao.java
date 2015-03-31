package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Foodie;
import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.idao.IFriendDao;
import cn.edu.bjtu.foodie.idao.IFoodieDao;

public class FriendDao extends HibernateDaoSupport implements IFriendDao {
	public String add(Friend friend){
		getHibernateTemplate().update(friend);
		return friend.getId();
	}
	


	
}
