package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.CrewMember;
import com.skyspace77.dto.CrewMemberDTO;
import com.skyspace77.dto.CrewMemberSearchDTO;
import com.skyspace77.dto.CrewMemberPageDTO;
import com.skyspace77.dto.CrewMemberConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CrewMemberService extends GenericService<CrewMember, Integer> {

	List<CrewMember> findAll();

	ResultDTO addCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO);

	ResultDTO updateCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO);

    Page<CrewMember> getAllCrewMembers(Pageable pageable);

    Page<CrewMember> getAllCrewMembers(Specification<CrewMember> spec, Pageable pageable);

	ResponseEntity<CrewMemberPageDTO> getCrewMembers(CrewMemberSearchDTO crewMemberSearchDTO);
	
	List<CrewMemberDTO> convertCrewMembersToCrewMemberDTOs(List<CrewMember> crewMembers, CrewMemberConvertCriteriaDTO convertCriteria);

	CrewMemberDTO getCrewMemberDTOById(Integer crewMemberId);







}





