package cn.edu.bjtu.foodie.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Cmt;
import cn.edu.bjtu.foodie.idao.ICommentDao;

public class CommontDao extends HibernateDaoSupport implements ICommentDao {
	public int isCmtExist(String id) {
		List<Cmt> list = getHibernateTemplate()
				.find("from Cmt  where id=?", id);
		return list.isEmpty() ? -1 : Integer.valueOf(list.get(0).getId());
	}

	public int add(Cmt cmt) {
		getHibernateTemplate().save(cmt);
		return Integer.valueOf(cmt.getId());
	}

	public Cmt getById(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Cmt.class, id);
	}

	public void deletebyId(String id) {
		// TODO Auto-generated method stub
		Cmt my_comment = new Cmt();
		my_comment.setId(id);
		getHibernateTemplate().delete(my_comment);
	}

}
