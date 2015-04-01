package cn.edu.bjtu.foodie.idao;

import cn.edu.bjtu.foodie.bean.Appointment;

public interface IAppointmentDao {
	Appointment getById(String id);
	void delete(String id);
	String add(Appointment appointment);
}
