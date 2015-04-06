package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.bean.Foodie;
import java.util.List;



public interface IFriendDao {
	/** add one friend **/
	String add(Friend friend);

	/** check user have this friend **/
	Boolean isNotFriend(String id,String foodieid);
	
	/**delete a friend **/
	void deleteFriend(String id);

	
	
	
}
