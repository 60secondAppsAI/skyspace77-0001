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
import com.skyspace77.dao.ServiceDAO;
import com.skyspace77.domain.Service;
import com.skyspace77.dto.ServiceDTO;
import com.skyspace77.dto.ServiceSearchDTO;
import com.skyspace77.dto.ServicePageDTO;
import com.skyspace77.dto.ServiceConvertCriteriaDTO;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import com.skyspace77.service.ServiceService;
import com.skyspace77.util.ControllerUtils;





@Service
public class ServiceServiceImpl extends GenericServiceImpl<Service, Integer> implements ServiceService {

    private final static Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

	@Autowired
	ServiceDAO serviceDao;

	


	@Override
	public GenericDAO<Service, Integer> getDAO() {
		return (GenericDAO<Service, Integer>) serviceDao;
	}
	
	public List<Service> findAll () {
		List<Service> services = serviceDao.findAll();
		
		return services;	
		
	}

	public ResultDTO addService(ServiceDTO serviceDTO, RequestDTO requestDTO) {

		Service service = new Service();

		service.setServiceId(serviceDTO.getServiceId());


		service.setServiceName(serviceDTO.getServiceName());


		service.setServiceDescription(serviceDTO.getServiceDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		service = serviceDao.save(service);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Service> getAllServices(Pageable pageable) {
		return serviceDao.findAll(pageable);
	}

	public Page<Service> getAllServices(Specification<Service> spec, Pageable pageable) {
		return serviceDao.findAll(spec, pageable);
	}

	public ResponseEntity<ServicePageDTO> getServices(ServiceSearchDTO serviceSearchDTO) {
	
			Integer serviceId = serviceSearchDTO.getServiceId(); 
 			String serviceName = serviceSearchDTO.getServiceName(); 
 			String serviceDescription = serviceSearchDTO.getServiceDescription(); 
 			String sortBy = serviceSearchDTO.getSortBy();
			String sortOrder = serviceSearchDTO.getSortOrder();
			String searchQuery = serviceSearchDTO.getSearchQuery();
			Integer page = serviceSearchDTO.getPage();
			Integer size = serviceSearchDTO.getSize();

	        Specification<Service> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, serviceId, "serviceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serviceName, "serviceName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serviceDescription, "serviceDescription"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("serviceName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("serviceDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Service> services = this.getAllServices(spec, pageable);
		
		//System.out.println(String.valueOf(services.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(services.getTotalPages()));
		
		List<Service> servicesList = services.getContent();
		
		ServiceConvertCriteriaDTO convertCriteria = new ServiceConvertCriteriaDTO();
		List<ServiceDTO> serviceDTOs = this.convertServicesToServiceDTOs(servicesList,convertCriteria);
		
		ServicePageDTO servicePageDTO = new ServicePageDTO();
		servicePageDTO.setServices(serviceDTOs);
		servicePageDTO.setTotalElements(services.getTotalElements());
		return ResponseEntity.ok(servicePageDTO);
	}

	public List<ServiceDTO> convertServicesToServiceDTOs(List<Service> services, ServiceConvertCriteriaDTO convertCriteria) {
		
		List<ServiceDTO> serviceDTOs = new ArrayList<ServiceDTO>();
		
		for (Service service : services) {
			serviceDTOs.add(convertServiceToServiceDTO(service,convertCriteria));
		}
		
		return serviceDTOs;

	}
	
	public ServiceDTO convertServiceToServiceDTO(Service service, ServiceConvertCriteriaDTO convertCriteria) {
		
		ServiceDTO serviceDTO = new ServiceDTO();
		
		serviceDTO.setServiceId(service.getServiceId());

	
		serviceDTO.setServiceName(service.getServiceName());

	
		serviceDTO.setServiceDescription(service.getServiceDescription());

	

		
		return serviceDTO;
	}

	public ResultDTO updateService(ServiceDTO serviceDTO, RequestDTO requestDTO) {
		
		Service service = serviceDao.getById(serviceDTO.getServiceId());

		service.setServiceId(ControllerUtils.setValue(service.getServiceId(), serviceDTO.getServiceId()));

		service.setServiceName(ControllerUtils.setValue(service.getServiceName(), serviceDTO.getServiceName()));

		service.setServiceDescription(ControllerUtils.setValue(service.getServiceDescription(), serviceDTO.getServiceDescription()));



        service = serviceDao.save(service);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ServiceDTO getServiceDTOById(Integer serviceId) {
	
		Service service = serviceDao.getById(serviceId);
			
		
		ServiceConvertCriteriaDTO convertCriteria = new ServiceConvertCriteriaDTO();
		return(this.convertServiceToServiceDTO(service,convertCriteria));
	}







}
