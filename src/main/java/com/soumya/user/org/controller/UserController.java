package com.soumya.user.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.user.org.DTO.UserDTO;
import com.soumya.user.org.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<String> saveUser(@Valid @RequestBody UserDTO userDetails){
		
		
		return new ResponseEntity<>(userService.saveUser(userDetails),HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDetails){
		
		
		return new ResponseEntity<>(userService.updateUser(userDetails),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id){
		
		
		return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String id){
		
		
		return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		
		
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
}
