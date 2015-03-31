package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Friend;
import cn.edu.bjtu.foodie.bean.Foodie;

import java.util.List;

public interface IFoodieDao {
	
	/** check if name is already exist **/
	int isNameExistByName(String name);
	/** check if name is exist by id **/
	int isNameExistById(String id);
	/** get foodie by id **/
	Foodie getById(String id);
	
	/** add one row **/
	int add(Foodie foodie);
	/** search the foodie according to name **/
	List<Foodie> searchByName(String name);
	/** check if there exsit a row with given name and password
	 *  @return -1 if not exsit
	 *  @return customer id if exsit
	 */
	int login(String name,String password);
	/** select a row by id **/
	Foodie getById(int id);
	/** update customer **/
	void update(Foodie foodie);
}
