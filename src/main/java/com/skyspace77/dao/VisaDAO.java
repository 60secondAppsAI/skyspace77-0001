package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.Visa;





public interface VisaDAO extends GenericDAO<Visa, Integer> {
  
	List<Visa> findAll();
	






}


