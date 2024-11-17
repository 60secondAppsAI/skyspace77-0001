package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Itinerary;





public interface ItineraryDAO extends GenericDAO<Itinerary, Integer> {
  
	List<Itinerary> findAll();
	






}


