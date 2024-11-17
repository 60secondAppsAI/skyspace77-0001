package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


