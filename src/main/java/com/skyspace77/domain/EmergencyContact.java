package com.skyspace77.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="emergency_contacts")
@Getter @Setter @NoArgsConstructor
public class EmergencyContact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="emergency_contact_id")
	private Integer emergencyContactId;
    
  	@Column(name="contact_name")
	private String contactName;
    
  	@Column(name="contact_relation")
	private String contactRelation;
    
  	@Column(name="contact_number")
	private String contactNumber;
    
	




}
