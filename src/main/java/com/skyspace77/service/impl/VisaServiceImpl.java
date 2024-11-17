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
import com.skyspace77.dao.VisaDAO;
import com.skyspace77.domain.Visa;
import com.skyspace77.dto.VisaDTO;
import com.skyspace77.dto.VisaSearchDTO;
import com.skyspace77.dto.VisaPageDTO;
import com.skyspace77.dto.VisaConvertCriteriaDTO;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import com.skyspace77.service.VisaService;
import com.skyspace77.util.ControllerUtils;





@Service
public class VisaServiceImpl extends GenericServiceImpl<Visa, Integer> implements VisaService {

    private final static Logger logger = LoggerFactory.getLogger(VisaServiceImpl.class);

	@Autowired
	VisaDAO visaDao;

	


	@Override
	public GenericDAO<Visa, Integer> getDAO() {
		return (GenericDAO<Visa, Integer>) visaDao;
	}
	
	public List<Visa> findAll () {
		List<Visa> visas = visaDao.findAll();
		
		return visas;	
		
	}

	public ResultDTO addVisa(VisaDTO visaDTO, RequestDTO requestDTO) {

		Visa visa = new Visa();

		visa.setVisaId(visaDTO.getVisaId());


		visa.setVisaType(visaDTO.getVisaType());


		visa.setIssueCountry(visaDTO.getIssueCountry());


		visa.setExpiryDate(visaDTO.getExpiryDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		visa = visaDao.save(visa);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Visa> getAllVisas(Pageable pageable) {
		return visaDao.findAll(pageable);
	}

	public Page<Visa> getAllVisas(Specification<Visa> spec, Pageable pageable) {
		return visaDao.findAll(spec, pageable);
	}

	public ResponseEntity<VisaPageDTO> getVisas(VisaSearchDTO visaSearchDTO) {
	
			Integer visaId = visaSearchDTO.getVisaId(); 
 			String visaType = visaSearchDTO.getVisaType(); 
 			String issueCountry = visaSearchDTO.getIssueCountry(); 
   			String sortBy = visaSearchDTO.getSortBy();
			String sortOrder = visaSearchDTO.getSortOrder();
			String searchQuery = visaSearchDTO.getSearchQuery();
			Integer page = visaSearchDTO.getPage();
			Integer size = visaSearchDTO.getSize();

	        Specification<Visa> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, visaId, "visaId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, visaType, "visaType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, issueCountry, "issueCountry"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("visaType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("issueCountry")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Visa> visas = this.getAllVisas(spec, pageable);
		
		//System.out.println(String.valueOf(visas.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(visas.getTotalPages()));
		
		List<Visa> visasList = visas.getContent();
		
		VisaConvertCriteriaDTO convertCriteria = new VisaConvertCriteriaDTO();
		List<VisaDTO> visaDTOs = this.convertVisasToVisaDTOs(visasList,convertCriteria);
		
		VisaPageDTO visaPageDTO = new VisaPageDTO();
		visaPageDTO.setVisas(visaDTOs);
		visaPageDTO.setTotalElements(visas.getTotalElements());
		return ResponseEntity.ok(visaPageDTO);
	}

	public List<VisaDTO> convertVisasToVisaDTOs(List<Visa> visas, VisaConvertCriteriaDTO convertCriteria) {
		
		List<VisaDTO> visaDTOs = new ArrayList<VisaDTO>();
		
		for (Visa visa : visas) {
			visaDTOs.add(convertVisaToVisaDTO(visa,convertCriteria));
		}
		
		return visaDTOs;

	}
	
	public VisaDTO convertVisaToVisaDTO(Visa visa, VisaConvertCriteriaDTO convertCriteria) {
		
		VisaDTO visaDTO = new VisaDTO();
		
		visaDTO.setVisaId(visa.getVisaId());

	
		visaDTO.setVisaType(visa.getVisaType());

	
		visaDTO.setIssueCountry(visa.getIssueCountry());

	
		visaDTO.setExpiryDate(visa.getExpiryDate());

	

		
		return visaDTO;
	}

	public ResultDTO updateVisa(VisaDTO visaDTO, RequestDTO requestDTO) {
		
		Visa visa = visaDao.getById(visaDTO.getVisaId());

		visa.setVisaId(ControllerUtils.setValue(visa.getVisaId(), visaDTO.getVisaId()));

		visa.setVisaType(ControllerUtils.setValue(visa.getVisaType(), visaDTO.getVisaType()));

		visa.setIssueCountry(ControllerUtils.setValue(visa.getIssueCountry(), visaDTO.getIssueCountry()));

		visa.setExpiryDate(ControllerUtils.setValue(visa.getExpiryDate(), visaDTO.getExpiryDate()));



        visa = visaDao.save(visa);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public VisaDTO getVisaDTOById(Integer visaId) {
	
		Visa visa = visaDao.getById(visaId);
			
		
		VisaConvertCriteriaDTO convertCriteria = new VisaConvertCriteriaDTO();
		return(this.convertVisaToVisaDTO(visa,convertCriteria));
	}







}
