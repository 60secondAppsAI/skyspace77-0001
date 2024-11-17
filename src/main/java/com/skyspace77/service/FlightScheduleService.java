package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.FlightSchedule;
import com.skyspace77.dto.FlightScheduleDTO;
import com.skyspace77.dto.FlightScheduleSearchDTO;
import com.skyspace77.dto.FlightSchedulePageDTO;
import com.skyspace77.dto.FlightScheduleConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightScheduleService extends GenericService<FlightSchedule, Integer> {

	List<FlightSchedule> findAll();

	ResultDTO addFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

	ResultDTO updateFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

    Page<FlightSchedule> getAllFlightSchedules(Pageable pageable);

    Page<FlightSchedule> getAllFlightSchedules(Specification<FlightSchedule> spec, Pageable pageable);

	ResponseEntity<FlightSchedulePageDTO> getFlightSchedules(FlightScheduleSearchDTO flightScheduleSearchDTO);
	
	List<FlightScheduleDTO> convertFlightSchedulesToFlightScheduleDTOs(List<FlightSchedule> flightSchedules, FlightScheduleConvertCriteriaDTO convertCriteria);

	FlightScheduleDTO getFlightScheduleDTOById(Integer flightScheduleId);







}





