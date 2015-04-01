package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Book;

public interface IBookDao {
	Book getById(String id);
	void delete(String id);
	String add(Book book);
	int getMaxBookNumber(int restId);
}
