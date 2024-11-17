package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Insurance;





public interface InsuranceDAO extends GenericDAO<Insurance, Integer> {
  
	List<Insurance> findAll();
	






}


