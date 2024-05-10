package com.soumya.user.org.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Ratings {
	private String ratingId;
	private String commnets;
	private LocalDateTime createdOn;
	private String hotelId;
	private String userId;
	private HotelDetails hotel;

}
