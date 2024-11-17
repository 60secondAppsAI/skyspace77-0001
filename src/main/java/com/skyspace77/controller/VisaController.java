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

import com.skyspace77.domain.Visa;
import com.skyspace77.dto.VisaDTO;
import com.skyspace77.dto.VisaSearchDTO;
import com.skyspace77.dto.VisaPageDTO;
import com.skyspace77.service.VisaService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/visa")
@RestController
public class VisaController {

	private final static Logger logger = LoggerFactory.getLogger(VisaController.class);

	@Autowired
	VisaService visaService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Visa> getAll() {

		List<Visa> visas = visaService.findAll();
		
		return visas;	
	}

	@GetMapping(value = "/{visaId}")
	@ResponseBody
	public VisaDTO getVisa(@PathVariable Integer visaId) {
		
		return (visaService.getVisaDTOById(visaId));
	}

 	@RequestMapping(value = "/addVisa", method = RequestMethod.POST)
	public ResponseEntity<?> addVisa(@RequestBody VisaDTO visaDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = visaService.addVisa(visaDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/visas")
	public ResponseEntity<VisaPageDTO> getVisas(VisaSearchDTO visaSearchDTO) {
 
		return visaService.getVisas(visaSearchDTO);
	}	

	@RequestMapping(value = "/updateVisa", method = RequestMethod.POST)
	public ResponseEntity<?> updateVisa(@RequestBody VisaDTO visaDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = visaService.updateVisa(visaDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
