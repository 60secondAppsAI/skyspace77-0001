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

import com.skyspace77.domain.Service;
import com.skyspace77.dto.ServiceDTO;
import com.skyspace77.dto.ServiceSearchDTO;
import com.skyspace77.dto.ServicePageDTO;
import com.skyspace77.service.ServiceService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/service")
@RestController
public class ServiceController {

	private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	ServiceService serviceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Service> getAll() {

		List<Service> services = serviceService.findAll();
		
		return services;	
	}

	@GetMapping(value = "/{serviceId}")
	@ResponseBody
	public ServiceDTO getService(@PathVariable Integer serviceId) {
		
		return (serviceService.getServiceDTOById(serviceId));
	}

 	@RequestMapping(value = "/addService", method = RequestMethod.POST)
	public ResponseEntity<?> addService(@RequestBody ServiceDTO serviceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = serviceService.addService(serviceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/services")
	public ResponseEntity<ServicePageDTO> getServices(ServiceSearchDTO serviceSearchDTO) {
 
		return serviceService.getServices(serviceSearchDTO);
	}	

	@RequestMapping(value = "/updateService", method = RequestMethod.POST)
	public ResponseEntity<?> updateService(@RequestBody ServiceDTO serviceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = serviceService.updateService(serviceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
