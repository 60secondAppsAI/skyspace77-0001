package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.EmergencyContact;
import com.skyspace77.dto.EmergencyContactDTO;
import com.skyspace77.dto.EmergencyContactSearchDTO;
import com.skyspace77.dto.EmergencyContactPageDTO;
import com.skyspace77.dto.EmergencyContactConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EmergencyContactService extends GenericService<EmergencyContact, Integer> {

	List<EmergencyContact> findAll();

	ResultDTO addEmergencyContact(EmergencyContactDTO emergencyContactDTO, RequestDTO requestDTO);

	ResultDTO updateEmergencyContact(EmergencyContactDTO emergencyContactDTO, RequestDTO requestDTO);

    Page<EmergencyContact> getAllEmergencyContacts(Pageable pageable);

    Page<EmergencyContact> getAllEmergencyContacts(Specification<EmergencyContact> spec, Pageable pageable);

	ResponseEntity<EmergencyContactPageDTO> getEmergencyContacts(EmergencyContactSearchDTO emergencyContactSearchDTO);
	
	List<EmergencyContactDTO> convertEmergencyContactsToEmergencyContactDTOs(List<EmergencyContact> emergencyContacts, EmergencyContactConvertCriteriaDTO convertCriteria);

	EmergencyContactDTO getEmergencyContactDTOById(Integer emergencyContactId);







}





