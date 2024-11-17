package com.skyspace77.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspace77.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspace77.domain.MealPreference;
import com.skyspace77.dto.MealPreferenceDTO;
import com.skyspace77.dto.MealPreferenceSearchDTO;
import com.skyspace77.dto.MealPreferencePageDTO;
import com.skyspace77.service.MealPreferenceService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/mealPreference")
@RestController
public class MealPreferenceController {

	private final static Logger logger = LoggerFactory.getLogger(MealPreferenceController.class);

	@Autowired
	MealPreferenceService mealPreferenceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MealPreference> getAll() {

		List<MealPreference> mealPreferences = mealPreferenceService.findAll();
		
		return mealPreferences;	
	}

	@GetMapping(value = "/{mealPreferenceId}")
	@ResponseBody
	public MealPreferenceDTO getMealPreference(@PathVariable Integer mealPreferenceId) {
		
		return (mealPreferenceService.getMealPreferenceDTOById(mealPreferenceId));
	}

 	@RequestMapping(value = "/addMealPreference", method = RequestMethod.POST)
	public ResponseEntity<?> addMealPreference(@RequestBody MealPreferenceDTO mealPreferenceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mealPreferenceService.addMealPreference(mealPreferenceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/mealPreferences")
	public ResponseEntity<MealPreferencePageDTO> getMealPreferences(MealPreferenceSearchDTO mealPreferenceSearchDTO) {
 
		return mealPreferenceService.getMealPreferences(mealPreferenceSearchDTO);
	}	

	@RequestMapping(value = "/updateMealPreference", method = RequestMethod.POST)
	public ResponseEntity<?> updateMealPreference(@RequestBody MealPreferenceDTO mealPreferenceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mealPreferenceService.updateMealPreference(mealPreferenceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
