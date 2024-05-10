package com.soumya.user.org.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class HotelDetails {

	private String hotelId;
	private String hotelName;
	private String hotelDescription;
	private Long hotelNumber;
	
}
