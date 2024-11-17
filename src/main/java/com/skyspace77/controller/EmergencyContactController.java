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

import com.skyspace77.domain.EmergencyContact;
import com.skyspace77.dto.EmergencyContactDTO;
import com.skyspace77.dto.EmergencyContactSearchDTO;
import com.skyspace77.dto.EmergencyContactPageDTO;
import com.skyspace77.service.EmergencyContactService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/emergencyContact")
@RestController
public class EmergencyContactController {

	private final static Logger logger = LoggerFactory.getLogger(EmergencyContactController.class);

	@Autowired
	EmergencyContactService emergencyContactService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<EmergencyContact> getAll() {

		List<EmergencyContact> emergencyContacts = emergencyContactService.findAll();
		
		return emergencyContacts;	
	}

	@GetMapping(value = "/{emergencyContactId}")
	@ResponseBody
	public EmergencyContactDTO getEmergencyContact(@PathVariable Integer emergencyContactId) {
		
		return (emergencyContactService.getEmergencyContactDTOById(emergencyContactId));
	}

 	@RequestMapping(value = "/addEmergencyContact", method = RequestMethod.POST)
	public ResponseEntity<?> addEmergencyContact(@RequestBody EmergencyContactDTO emergencyContactDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = emergencyContactService.addEmergencyContact(emergencyContactDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/emergencyContacts")
	public ResponseEntity<EmergencyContactPageDTO> getEmergencyContacts(EmergencyContactSearchDTO emergencyContactSearchDTO) {
 
		return emergencyContactService.getEmergencyContacts(emergencyContactSearchDTO);
	}	

	@RequestMapping(value = "/updateEmergencyContact", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmergencyContact(@RequestBody EmergencyContactDTO emergencyContactDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = emergencyContactService.updateEmergencyContact(emergencyContactDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
