package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.LoyaltyProgram;
import com.skyspace77.dto.LoyaltyProgramDTO;
import com.skyspace77.dto.LoyaltyProgramSearchDTO;
import com.skyspace77.dto.LoyaltyProgramPageDTO;
import com.skyspace77.dto.LoyaltyProgramConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoyaltyProgramService extends GenericService<LoyaltyProgram, Integer> {

	List<LoyaltyProgram> findAll();

	ResultDTO addLoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO, RequestDTO requestDTO);

	ResultDTO updateLoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO, RequestDTO requestDTO);

    Page<LoyaltyProgram> getAllLoyaltyPrograms(Pageable pageable);

    Page<LoyaltyProgram> getAllLoyaltyPrograms(Specification<LoyaltyProgram> spec, Pageable pageable);

	ResponseEntity<LoyaltyProgramPageDTO> getLoyaltyPrograms(LoyaltyProgramSearchDTO loyaltyProgramSearchDTO);
	
	List<LoyaltyProgramDTO> convertLoyaltyProgramsToLoyaltyProgramDTOs(List<LoyaltyProgram> loyaltyPrograms, LoyaltyProgramConvertCriteriaDTO convertCriteria);

	LoyaltyProgramDTO getLoyaltyProgramDTOById(Integer loyaltyProgramId);







}





