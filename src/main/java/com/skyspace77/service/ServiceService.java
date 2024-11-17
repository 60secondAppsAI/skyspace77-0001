package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.Service;
import com.skyspace77.dto.ServiceDTO;
import com.skyspace77.dto.ServiceSearchDTO;
import com.skyspace77.dto.ServicePageDTO;
import com.skyspace77.dto.ServiceConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ServiceService extends GenericService<Service, Integer> {

	List<Service> findAll();

	ResultDTO addService(ServiceDTO serviceDTO, RequestDTO requestDTO);

	ResultDTO updateService(ServiceDTO serviceDTO, RequestDTO requestDTO);

    Page<Service> getAllServices(Pageable pageable);

    Page<Service> getAllServices(Specification<Service> spec, Pageable pageable);

	ResponseEntity<ServicePageDTO> getServices(ServiceSearchDTO serviceSearchDTO);
	
	List<ServiceDTO> convertServicesToServiceDTOs(List<Service> services, ServiceConvertCriteriaDTO convertCriteria);

	ServiceDTO getServiceDTOById(Integer serviceId);







}





