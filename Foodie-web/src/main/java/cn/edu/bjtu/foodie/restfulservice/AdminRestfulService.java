package cn.edu.bjtu.foodie.restfulservice;

import javax.ws.rs.Path;

import cn.edu.bjtu.foodie.idao.IAdminDao;

@Path("admin")
public class AdminRestfulService {
	private IAdminDao adminDao;
	
	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
