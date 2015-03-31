package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.bean.Foodie;
import java.util.List;



public interface IFriendDao {
	/** add one friend **/
	String add(Friend friend);

	/** check user have this friend **/
	Boolean isNotFriend(Foodie foodie,String id);
	
	/**delete a friend **/
	void deleteFriend(Foodie foodie,String id);

	
	
	
}
