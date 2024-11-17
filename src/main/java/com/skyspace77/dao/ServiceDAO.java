package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Service;





public interface ServiceDAO extends GenericDAO<Service, Integer> {
  
	List<Service> findAll();
	






}


