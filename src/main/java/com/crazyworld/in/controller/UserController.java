package com.crazyworld.in.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.AdminPojo;
import com.crazyworld.in.model.AuthPojo;
import com.crazyworld.in.model.UserPojo;

import com.crazyworld.in.service.UserServiceImpl;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login")
	public AuthPojo authenticateUser(@RequestBody UserPojo userPojo) {
		return userService.authenticateUser(userPojo);
	}

}
