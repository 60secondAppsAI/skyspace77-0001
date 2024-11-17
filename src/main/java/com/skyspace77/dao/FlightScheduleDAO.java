package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.FlightSchedule;





public interface FlightScheduleDAO extends GenericDAO<FlightSchedule, Integer> {
  
	List<FlightSchedule> findAll();
	






}


