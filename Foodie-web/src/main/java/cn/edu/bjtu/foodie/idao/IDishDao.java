package cn.edu.bjtu.foodie.idao;

import java.util.List;

import cn.edu.bjtu.foodie.bean.Dish;

public interface IDishDao {
	/** check if the dish exists **/
	boolean isDishExistByName(String name);
	
	/**add a dish**/
	String addDish(Dish dish);

	/** get dish by name **/
	List<Dish> getByName(String name);

	/** update the information of dish **/
	void updateDish(Dish dish);

	/** delete dish from database **/
	void deleteById(String id);

	/** get the restId of the dish **/
	Dish getRestaurantById(int id);

	/** get the category of the dish **/
	Dish getCategoryById(int id);
}
