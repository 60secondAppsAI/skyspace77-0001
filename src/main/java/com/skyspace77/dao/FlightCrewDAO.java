package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.FlightCrew;





public interface FlightCrewDAO extends GenericDAO<FlightCrew, Integer> {
  
	List<FlightCrew> findAll();
	






}


