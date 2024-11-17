package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.Insurance;
import com.skyspace77.dto.InsuranceDTO;
import com.skyspace77.dto.InsuranceSearchDTO;
import com.skyspace77.dto.InsurancePageDTO;
import com.skyspace77.dto.InsuranceConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InsuranceService extends GenericService<Insurance, Integer> {

	List<Insurance> findAll();

	ResultDTO addInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

	ResultDTO updateInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

    Page<Insurance> getAllInsurances(Pageable pageable);

    Page<Insurance> getAllInsurances(Specification<Insurance> spec, Pageable pageable);

	ResponseEntity<InsurancePageDTO> getInsurances(InsuranceSearchDTO insuranceSearchDTO);
	
	List<InsuranceDTO> convertInsurancesToInsuranceDTOs(List<Insurance> insurances, InsuranceConvertCriteriaDTO convertCriteria);

	InsuranceDTO getInsuranceDTOById(Integer insuranceId);







}





