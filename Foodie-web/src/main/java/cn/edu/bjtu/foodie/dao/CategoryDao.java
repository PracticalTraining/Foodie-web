package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Category;
import cn.edu.bjtu.foodie.idao.ICategoryDao;

public class CategoryDao extends HibernateDaoSupport implements ICategoryDao {

	public Category getById(String id) {
		Category rc = getHibernateTemplate().get(Category.class, id);
		return rc;
	}

	public boolean isCategoryExist(String id) {
		List<Category> list = getHibernateTemplate().find(
				"from Category where id=?", id);
		return !list.isEmpty();
	}

	public String add(Category category) {
		System.out.println(category.getName());
		getHibernateTemplate().save(category);
		return (category.getId());
	}

	public void delete(Category deleteCategory) {
		getHibernateTemplate().delete(deleteCategory);
	}

	public Category searchCategoryDetailById(String id) {
		List<Category> Categories = getHibernateTemplate().find(
				"from Category where id=?", id);
		return Categories.get(0);
	}

	public void update(Category updateCategory) {
		getHibernateTemplate().update(updateCategory);

	}

}
