package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Category;

public interface ICategoryDao {
	/** get detail information of DishCategory by id **/
	Category getById(String id);

	/** check if Category exist **/
	boolean isCategoryExist(String id);
	
	/** add one row **/
	String add(Category category);
	
	/** delete the Category **/
	void delete(Category deleteCategory);
	
	/** search the detail info of the Category **/
	Category searchCategoryDetailById(String id);
	
	/** update the Category **/
	void update(Category updateCategory);
}
