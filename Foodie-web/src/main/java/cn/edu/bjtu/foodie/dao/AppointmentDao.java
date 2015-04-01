package cn.edu.bjtu.foodie.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.bjtu.foodie.bean.Appointment;
import cn.edu.bjtu.foodie.idao.IAppointmentDao;

public class AppointmentDao extends HibernateDaoSupport implements IAppointmentDao {

	public Appointment getById(String id) {
		return getHibernateTemplate().get(Appointment.class, id);
	}

	public void delete(String id) {
		Appointment appointment = new Appointment();
		appointment.setId(id);
		getHibernateTemplate().delete(appointment);
	}

	public String add(Appointment appointment) {
		return (String) getHibernateTemplate().save(appointment);
	}

}
