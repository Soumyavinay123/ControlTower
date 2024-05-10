package com.soumya.user.org.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.soumya.user.org.DTO.UserDTO;
import com.soumya.user.org.domain.HotelDetails;
import com.soumya.user.org.domain.Ratings;
import com.soumya.user.org.domain.UserDetailsDomain;
import com.soumya.user.org.exception.UserNameAlreadyExists;
import com.soumya.user.org.exception.UserNotFoundException;
import com.soumya.user.org.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelServiceFeign hotelServiceFeign;
	
	@Autowired
	private RatingService ratingService;

	public String saveUser(UserDTO user) {

		Optional<UserDetailsDomain> recordByUserName = userRepository.findByUserName(user.getUserName());

		if(recordByUserName.isEmpty()) {
			UserDetailsDomain userBO = UserDTO.getUserBO(user);
			userBO.setUserId(UUID.randomUUID().toString());
			UserDetailsDomain savedUser = userRepository.save(userBO);
			BeanUtils.copyProperties(savedUser, user);
			return "Record Saved";
		}
		throw new UserNameAlreadyExists("User Already Exists With User Name :"+user.getUserName());
	}

	public String updateUser(UserDTO user) {

		UserDetailsDomain retrievdUser = userRepository.findById(user.getUserId()).orElseThrow(() ->new UserNotFoundException("No user found with the given id"));

		BeanUtils.copyProperties(user, retrievdUser);
		userRepository.save(retrievdUser);

		return "Record Updated";

	}

	public String deleteUser(String id) {

		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "Record deleted";
		}
		throw new UserNotFoundException("No user found with the given id");
	}


	public UserDTO getUser(String id) {

		UserDetailsDomain retrievdUser = userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("No user found with the given id"));
		//get request to ratings API
//		Ratings[] ratingsArray = restTemplate.getForObject("http://RATING-API/rating-management/rating/user/"+id, Ratings[].class);
//		List<Ratings> ratingsList = Arrays.stream(ratingsArray).toList();
		List<Ratings> ratingsList = ratingService.getAllRatingsOfUser(id);
		ratingsList.forEach(rating ->{
//			ResponseEntity<HotelDetails> forEntity = restTemplate.getForEntity("http://HOTEL-API/hotel-management/hotel/"+rating.getHotelId(), HotelDetails.class);
//			rating.setHotel(forEntity.getBody());
			rating.setHotel(hotelServiceFeign.gethotel(rating.getHotelId()));
		});
//		612b4c07-12cd-4604-8bd6-baecb01b7d30
//		http://localhost:9092/rating-management/rating/user/612b4c07-12cd-4604-8bd6-baecb01b7d30
		retrievdUser.setRatings(ratingsList);
		return UserDTO.getUserDTO(retrievdUser);

	}

	public List<UserDTO> getAllUsers(){
		
		 List<UserDTO> collectedUsers = userRepository.findAll().stream().map(record ->UserDTO.getUserDTO(record)).collect(Collectors.toList());
		
		return collectedUsers; 
				
}

}