package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.LoyaltyProgram;





public interface LoyaltyProgramDAO extends GenericDAO<LoyaltyProgram, Integer> {
  
	List<LoyaltyProgram> findAll();
	






}


