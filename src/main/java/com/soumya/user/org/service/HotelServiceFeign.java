package com.soumya.user.org.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.soumya.user.org.domain.HotelDetails;

@FeignClient(name = "HOTEL-API")
public interface HotelServiceFeign {
	
	@GetMapping("/hotel-management/hotel/{hotelId}")
	public HotelDetails gethotel(@PathVariable String hotelId);

}
