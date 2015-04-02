package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Dish;
import cn.edu.bjtu.foodie.idao.IDishDao;

public class DishDao extends HibernateDaoSupport implements IDishDao {

	public boolean isDishExistByName(String name) {
		List<Dish> list = getHibernateTemplate().find("from Dish where name=?",
				name);
		return !list.isEmpty();
	}

	public String addDish(Dish dish) {
		getHibernateTemplate().save(dish);
		return dish.getId();
	}

	public List<Dish> getByName(String name) {
		List<Dish> list = getHibernateTemplate().find(
				"from Dish where name like ?", "%" + name + "%");
		return null;
	}

	public void updateDish(Dish dish) {
		getHibernateTemplate().saveOrUpdate(dish);

	}

	public void deleteById(String id) {
		getHibernateTemplate().delete("Dish where id=?", id);

	}

	public Dish getRestaurantById(int id) {
		return getHibernateTemplate().get(Dish.class, id);
	}

	public Dish getCategoryById(int id) {
		return getHibernateTemplate().get(Dish.class, id);
	}

}
