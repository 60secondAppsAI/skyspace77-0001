package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.EmergencyContact;





public interface EmergencyContactDAO extends GenericDAO<EmergencyContact, Integer> {
  
	List<EmergencyContact> findAll();
	






}


