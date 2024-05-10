package com.soumya.user.org.service;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.soumya.user.org.domain.Ratings;

@FeignClient(name = "RATING-API")
//@LoadBalancerClient
public interface RatingService {
	
	@GetMapping("/rating-management/rating/user/{userId}")
	public List<Ratings> getAllRatingsOfUser(@PathVariable String userId);

}
