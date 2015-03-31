package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Cmt;

public interface ICommentDao {
	public int isCmtExist(String id);

	/** add ont cmt into the database **/
	public int add(Cmt cmt);

	/** get the cmt based on id **/
	public Cmt getById(String id);

	/** delete the cmt based on id **/
	public void deletebyId(String id);

}
