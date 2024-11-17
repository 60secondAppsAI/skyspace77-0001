package com.skyspace77.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class VisaDTO {

	private Integer visaId;

	private String visaType;

	private String issueCountry;

	private Date expiryDate;






}
