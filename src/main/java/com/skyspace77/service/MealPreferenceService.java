package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.MealPreference;
import com.skyspace77.dto.MealPreferenceDTO;
import com.skyspace77.dto.MealPreferenceSearchDTO;
import com.skyspace77.dto.MealPreferencePageDTO;
import com.skyspace77.dto.MealPreferenceConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealPreferenceService extends GenericService<MealPreference, Integer> {

	List<MealPreference> findAll();

	ResultDTO addMealPreference(MealPreferenceDTO mealPreferenceDTO, RequestDTO requestDTO);

	ResultDTO updateMealPreference(MealPreferenceDTO mealPreferenceDTO, RequestDTO requestDTO);

    Page<MealPreference> getAllMealPreferences(Pageable pageable);

    Page<MealPreference> getAllMealPreferences(Specification<MealPreference> spec, Pageable pageable);

	ResponseEntity<MealPreferencePageDTO> getMealPreferences(MealPreferenceSearchDTO mealPreferenceSearchDTO);
	
	List<MealPreferenceDTO> convertMealPreferencesToMealPreferenceDTOs(List<MealPreference> mealPreferences, MealPreferenceConvertCriteriaDTO convertCriteria);

	MealPreferenceDTO getMealPreferenceDTOById(Integer mealPreferenceId);







}





