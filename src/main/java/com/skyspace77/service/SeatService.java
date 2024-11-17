package com.skyspace77.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace77.domain.Seat;
import com.skyspace77.dto.SeatDTO;
import com.skyspace77.dto.SeatSearchDTO;
import com.skyspace77.dto.SeatPageDTO;
import com.skyspace77.dto.SeatConvertCriteriaDTO;
import com.skyspace77.service.GenericService;
import com.skyspace77.dto.common.RequestDTO;
import com.skyspace77.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeatService extends GenericService<Seat, Integer> {

	List<Seat> findAll();

	ResultDTO addSeat(SeatDTO seatDTO, RequestDTO requestDTO);

	ResultDTO updateSeat(SeatDTO seatDTO, RequestDTO requestDTO);

    Page<Seat> getAllSeats(Pageable pageable);

    Page<Seat> getAllSeats(Specification<Seat> spec, Pageable pageable);

	ResponseEntity<SeatPageDTO> getSeats(SeatSearchDTO seatSearchDTO);
	
	List<SeatDTO> convertSeatsToSeatDTOs(List<Seat> seats, SeatConvertCriteriaDTO convertCriteria);

	SeatDTO getSeatDTOById(Integer seatId);







}





