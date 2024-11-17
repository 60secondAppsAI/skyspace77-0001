package com.skyspace77.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace77.dao.GenericDAO;
import com.skyspace77.service.GenericService;
import com.skyspace77.service.impl.GenericServiceImpl;
import com.skyspace77.dao.MealPreferenceDAO;
import com.skyspace77.domain.MealPreference;
import com.skyspace77.dto.MealPreferenceDTO;
import com.skyspace77.dto.MealPreferenceSearchDTO;
import com.skyspace77.dto.MealPreferencePageDTO;
import com.skyspace77.dto.MealPreferenceConvertCriteriaDTO;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import com.skyspace77.service.MealPreferenceService;
import com.skyspace77.util.ControllerUtils;





@Service
public class MealPreferenceServiceImpl extends GenericServiceImpl<MealPreference, Integer> implements MealPreferenceService {

    private final static Logger logger = LoggerFactory.getLogger(MealPreferenceServiceImpl.class);

	@Autowired
	MealPreferenceDAO mealPreferenceDao;

	


	@Override
	public GenericDAO<MealPreference, Integer> getDAO() {
		return (GenericDAO<MealPreference, Integer>) mealPreferenceDao;
	}
	
	public List<MealPreference> findAll () {
		List<MealPreference> mealPreferences = mealPreferenceDao.findAll();
		
		return mealPreferences;	
		
	}

	public ResultDTO addMealPreference(MealPreferenceDTO mealPreferenceDTO, RequestDTO requestDTO) {

		MealPreference mealPreference = new MealPreference();

		mealPreference.setMealPreferenceId(mealPreferenceDTO.getMealPreferenceId());


		mealPreference.setMealType(mealPreferenceDTO.getMealType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		mealPreference = mealPreferenceDao.save(mealPreference);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MealPreference> getAllMealPreferences(Pageable pageable) {
		return mealPreferenceDao.findAll(pageable);
	}

	public Page<MealPreference> getAllMealPreferences(Specification<MealPreference> spec, Pageable pageable) {
		return mealPreferenceDao.findAll(spec, pageable);
	}

	public ResponseEntity<MealPreferencePageDTO> getMealPreferences(MealPreferenceSearchDTO mealPreferenceSearchDTO) {
	
			Integer mealPreferenceId = mealPreferenceSearchDTO.getMealPreferenceId(); 
 			String mealType = mealPreferenceSearchDTO.getMealType(); 
 			String sortBy = mealPreferenceSearchDTO.getSortBy();
			String sortOrder = mealPreferenceSearchDTO.getSortOrder();
			String searchQuery = mealPreferenceSearchDTO.getSearchQuery();
			Integer page = mealPreferenceSearchDTO.getPage();
			Integer size = mealPreferenceSearchDTO.getSize();

	        Specification<MealPreference> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, mealPreferenceId, "mealPreferenceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, mealType, "mealType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("mealType")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<MealPreference> mealPreferences = this.getAllMealPreferences(spec, pageable);
		
		//System.out.println(String.valueOf(mealPreferences.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(mealPreferences.getTotalPages()));
		
		List<MealPreference> mealPreferencesList = mealPreferences.getContent();
		
		MealPreferenceConvertCriteriaDTO convertCriteria = new MealPreferenceConvertCriteriaDTO();
		List<MealPreferenceDTO> mealPreferenceDTOs = this.convertMealPreferencesToMealPreferenceDTOs(mealPreferencesList,convertCriteria);
		
		MealPreferencePageDTO mealPreferencePageDTO = new MealPreferencePageDTO();
		mealPreferencePageDTO.setMealPreferences(mealPreferenceDTOs);
		mealPreferencePageDTO.setTotalElements(mealPreferences.getTotalElements());
		return ResponseEntity.ok(mealPreferencePageDTO);
	}

	public List<MealPreferenceDTO> convertMealPreferencesToMealPreferenceDTOs(List<MealPreference> mealPreferences, MealPreferenceConvertCriteriaDTO convertCriteria) {
		
		List<MealPreferenceDTO> mealPreferenceDTOs = new ArrayList<MealPreferenceDTO>();
		
		for (MealPreference mealPreference : mealPreferences) {
			mealPreferenceDTOs.add(convertMealPreferenceToMealPreferenceDTO(mealPreference,convertCriteria));
		}
		
		return mealPreferenceDTOs;

	}
	
	public MealPreferenceDTO convertMealPreferenceToMealPreferenceDTO(MealPreference mealPreference, MealPreferenceConvertCriteriaDTO convertCriteria) {
		
		MealPreferenceDTO mealPreferenceDTO = new MealPreferenceDTO();
		
		mealPreferenceDTO.setMealPreferenceId(mealPreference.getMealPreferenceId());

	
		mealPreferenceDTO.setMealType(mealPreference.getMealType());

	

		
		return mealPreferenceDTO;
	}

	public ResultDTO updateMealPreference(MealPreferenceDTO mealPreferenceDTO, RequestDTO requestDTO) {
		
		MealPreference mealPreference = mealPreferenceDao.getById(mealPreferenceDTO.getMealPreferenceId());

		mealPreference.setMealPreferenceId(ControllerUtils.setValue(mealPreference.getMealPreferenceId(), mealPreferenceDTO.getMealPreferenceId()));

		mealPreference.setMealType(ControllerUtils.setValue(mealPreference.getMealType(), mealPreferenceDTO.getMealType()));



        mealPreference = mealPreferenceDao.save(mealPreference);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MealPreferenceDTO getMealPreferenceDTOById(Integer mealPreferenceId) {
	
		MealPreference mealPreference = mealPreferenceDao.getById(mealPreferenceId);
			
		
		MealPreferenceConvertCriteriaDTO convertCriteria = new MealPreferenceConvertCriteriaDTO();
		return(this.convertMealPreferenceToMealPreferenceDTO(mealPreference,convertCriteria));
	}







}
