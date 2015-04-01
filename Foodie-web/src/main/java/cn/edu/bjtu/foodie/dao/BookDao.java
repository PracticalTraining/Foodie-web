package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Book;
import cn.edu.bjtu.foodie.idao.IBookDao;

public class BookDao extends HibernateDaoSupport implements IBookDao {

	public Book getById(String id) {
		return getHibernateTemplate().get(Book.class, id);
	}
	
	public void delete(String id) {
		Book book = new Book();
		book.setId(id);
		getHibernateTemplate().delete(book);
	}

	public String add(Book book) {
		return (String) getHibernateTemplate().save(book);
	}

	public int getMaxBookNumber(int restId) {
		List<Book> books =  getHibernateTemplate().find("from Book where restId = ? order by bookNumber desc", restId);
		if(books != null && !books.isEmpty()){
			return books.get(0).getBookNumber();
		}
		return 0;
	}
	
}
