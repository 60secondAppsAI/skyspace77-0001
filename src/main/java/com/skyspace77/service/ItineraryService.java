package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.Itinerary;
import com.skyspace77.dto.ItineraryDTO;
import com.skyspace77.dto.ItinerarySearchDTO;
import com.skyspace77.dto.ItineraryPageDTO;
import com.skyspace77.dto.ItineraryConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItineraryService extends GenericService<Itinerary, Integer> {

	List<Itinerary> findAll();

	ResultDTO addItinerary(ItineraryDTO itineraryDTO, RequestDTO requestDTO);

	ResultDTO updateItinerary(ItineraryDTO itineraryDTO, RequestDTO requestDTO);

    Page<Itinerary> getAllItinerarys(Pageable pageable);

    Page<Itinerary> getAllItinerarys(Specification<Itinerary> spec, Pageable pageable);

	ResponseEntity<ItineraryPageDTO> getItinerarys(ItinerarySearchDTO itinerarySearchDTO);
	
	List<ItineraryDTO> convertItinerarysToItineraryDTOs(List<Itinerary> itinerarys, ItineraryConvertCriteriaDTO convertCriteria);

	ItineraryDTO getItineraryDTOById(Integer itineraryId);







}





