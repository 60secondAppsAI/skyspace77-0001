package com.skyspace77.dao;

import java.util.List;

import com.skyspace77.dao.GenericDAO;
import com.skyspace77.domain.MealPreference;





public interface MealPreferenceDAO extends GenericDAO<MealPreference, Integer> {
  
	List<MealPreference> findAll();
	






}


