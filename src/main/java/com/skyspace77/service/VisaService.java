package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.Visa;
import com.skyspace77.dto.VisaDTO;
import com.skyspace77.dto.VisaSearchDTO;
import com.skyspace77.dto.VisaPageDTO;
import com.skyspace77.dto.VisaConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface VisaService extends GenericService<Visa, Integer> {

	List<Visa> findAll();

	ResultDTO addVisa(VisaDTO visaDTO, RequestDTO requestDTO);

	ResultDTO updateVisa(VisaDTO visaDTO, RequestDTO requestDTO);

    Page<Visa> getAllVisas(Pageable pageable);

    Page<Visa> getAllVisas(Specification<Visa> spec, Pageable pageable);

	ResponseEntity<VisaPageDTO> getVisas(VisaSearchDTO visaSearchDTO);
	
	List<VisaDTO> convertVisasToVisaDTOs(List<Visa> visas, VisaConvertCriteriaDTO convertCriteria);

	VisaDTO getVisaDTOById(Integer visaId);







}





