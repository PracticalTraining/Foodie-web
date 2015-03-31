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
	public Boolean isNotFriend(Foodie foodie,String id){
		List<Friend> list = getHibernateTemplate().find("from Friend where foodieId = ? and friendId = ? and status = 0",foodie.getId(),id);
		return list.isEmpty();
	}
	public void deleteFriend(Foodie foodie,String id){
		Friend friend = new Friend();
		friend.setFoodieId(foodie.getId());
		friend.setFriendId(id);
		getHibernateTemplate().delete(friend);
		
	}
	


	
}
