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
import com.skyspace77.dao.EmergencyContactDAO;
import com.skyspace77.domain.EmergencyContact;
import com.skyspace77.dto.EmergencyContactDTO;
import com.skyspace77.dto.EmergencyContactSearchDTO;
import com.skyspace77.dto.EmergencyContactPageDTO;
import com.skyspace77.dto.EmergencyContactConvertCriteriaDTO;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import com.skyspace77.service.EmergencyContactService;
import com.skyspace77.util.ControllerUtils;





@Service
public class EmergencyContactServiceImpl extends GenericServiceImpl<EmergencyContact, Integer> implements EmergencyContactService {

    private final static Logger logger = LoggerFactory.getLogger(EmergencyContactServiceImpl.class);

	@Autowired
	EmergencyContactDAO emergencyContactDao;

	


	@Override
	public GenericDAO<EmergencyContact, Integer> getDAO() {
		return (GenericDAO<EmergencyContact, Integer>) emergencyContactDao;
	}
	
	public List<EmergencyContact> findAll () {
		List<EmergencyContact> emergencyContacts = emergencyContactDao.findAll();
		
		return emergencyContacts;	
		
	}

	public ResultDTO addEmergencyContact(EmergencyContactDTO emergencyContactDTO, RequestDTO requestDTO) {

		EmergencyContact emergencyContact = new EmergencyContact();

		emergencyContact.setEmergencyContactId(emergencyContactDTO.getEmergencyContactId());


		emergencyContact.setContactName(emergencyContactDTO.getContactName());


		emergencyContact.setContactRelation(emergencyContactDTO.getContactRelation());


		emergencyContact.setContactNumber(emergencyContactDTO.getContactNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		emergencyContact = emergencyContactDao.save(emergencyContact);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<EmergencyContact> getAllEmergencyContacts(Pageable pageable) {
		return emergencyContactDao.findAll(pageable);
	}

	public Page<EmergencyContact> getAllEmergencyContacts(Specification<EmergencyContact> spec, Pageable pageable) {
		return emergencyContactDao.findAll(spec, pageable);
	}

	public ResponseEntity<EmergencyContactPageDTO> getEmergencyContacts(EmergencyContactSearchDTO emergencyContactSearchDTO) {
	
			Integer emergencyContactId = emergencyContactSearchDTO.getEmergencyContactId(); 
 			String contactName = emergencyContactSearchDTO.getContactName(); 
 			String contactRelation = emergencyContactSearchDTO.getContactRelation(); 
 			String contactNumber = emergencyContactSearchDTO.getContactNumber(); 
 			String sortBy = emergencyContactSearchDTO.getSortBy();
			String sortOrder = emergencyContactSearchDTO.getSortOrder();
			String searchQuery = emergencyContactSearchDTO.getSearchQuery();
			Integer page = emergencyContactSearchDTO.getPage();
			Integer size = emergencyContactSearchDTO.getSize();

	        Specification<EmergencyContact> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, emergencyContactId, "emergencyContactId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactName, "contactName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactRelation, "contactRelation"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactNumber, "contactNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("contactName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactRelation")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<EmergencyContact> emergencyContacts = this.getAllEmergencyContacts(spec, pageable);
		
		//System.out.println(String.valueOf(emergencyContacts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(emergencyContacts.getTotalPages()));
		
		List<EmergencyContact> emergencyContactsList = emergencyContacts.getContent();
		
		EmergencyContactConvertCriteriaDTO convertCriteria = new EmergencyContactConvertCriteriaDTO();
		List<EmergencyContactDTO> emergencyContactDTOs = this.convertEmergencyContactsToEmergencyContactDTOs(emergencyContactsList,convertCriteria);
		
		EmergencyContactPageDTO emergencyContactPageDTO = new EmergencyContactPageDTO();
		emergencyContactPageDTO.setEmergencyContacts(emergencyContactDTOs);
		emergencyContactPageDTO.setTotalElements(emergencyContacts.getTotalElements());
		return ResponseEntity.ok(emergencyContactPageDTO);
	}

	public List<EmergencyContactDTO> convertEmergencyContactsToEmergencyContactDTOs(List<EmergencyContact> emergencyContacts, EmergencyContactConvertCriteriaDTO convertCriteria) {
		
		List<EmergencyContactDTO> emergencyContactDTOs = new ArrayList<EmergencyContactDTO>();
		
		for (EmergencyContact emergencyContact : emergencyContacts) {
			emergencyContactDTOs.add(convertEmergencyContactToEmergencyContactDTO(emergencyContact,convertCriteria));
		}
		
		return emergencyContactDTOs;

	}
	
	public EmergencyContactDTO convertEmergencyContactToEmergencyContactDTO(EmergencyContact emergencyContact, EmergencyContactConvertCriteriaDTO convertCriteria) {
		
		EmergencyContactDTO emergencyContactDTO = new EmergencyContactDTO();
		
		emergencyContactDTO.setEmergencyContactId(emergencyContact.getEmergencyContactId());

	
		emergencyContactDTO.setContactName(emergencyContact.getContactName());

	
		emergencyContactDTO.setContactRelation(emergencyContact.getContactRelation());

	
		emergencyContactDTO.setContactNumber(emergencyContact.getContactNumber());

	

		
		return emergencyContactDTO;
	}

	public ResultDTO updateEmergencyContact(EmergencyContactDTO emergencyContactDTO, RequestDTO requestDTO) {
		
		EmergencyContact emergencyContact = emergencyContactDao.getById(emergencyContactDTO.getEmergencyContactId());

		emergencyContact.setEmergencyContactId(ControllerUtils.setValue(emergencyContact.getEmergencyContactId(), emergencyContactDTO.getEmergencyContactId()));

		emergencyContact.setContactName(ControllerUtils.setValue(emergencyContact.getContactName(), emergencyContactDTO.getContactName()));

		emergencyContact.setContactRelation(ControllerUtils.setValue(emergencyContact.getContactRelation(), emergencyContactDTO.getContactRelation()));

		emergencyContact.setContactNumber(ControllerUtils.setValue(emergencyContact.getContactNumber(), emergencyContactDTO.getContactNumber()));



        emergencyContact = emergencyContactDao.save(emergencyContact);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EmergencyContactDTO getEmergencyContactDTOById(Integer emergencyContactId) {
	
		EmergencyContact emergencyContact = emergencyContactDao.getById(emergencyContactId);
			
		
		EmergencyContactConvertCriteriaDTO convertCriteria = new EmergencyContactConvertCriteriaDTO();
		return(this.convertEmergencyContactToEmergencyContactDTO(emergencyContact,convertCriteria));
	}







}
