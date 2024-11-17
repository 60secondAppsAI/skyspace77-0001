package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


