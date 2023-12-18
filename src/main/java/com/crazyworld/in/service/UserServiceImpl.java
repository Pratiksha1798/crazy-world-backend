package com.crazyworld.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crazyworld.in.dao.UserRepository;
import com.crazyworld.in.dao.entity.RolesEntity;
import com.crazyworld.in.dao.entity.UserEntity;
import com.crazyworld.in.model.AuthPojo;
import com.crazyworld.in.model.RolesPojo;
import com.crazyworld.in.model.UserPojo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	JwtService jwtService;
	
	@Override
	public AuthPojo authenticateUser(UserPojo userPojo) {
	
		AuthPojo authModel = new AuthPojo();
		UserEntity fetchedUser = userRepo.findByUserName(userPojo.getUserName());
		if(fetchedUser!=null && fetchedUser.getUserPassword().equals(userPojo.getUserPassword())) {
			List<RolesPojo> allRolesModel = new ArrayList<>();
			for(RolesEntity eachRoles: fetchedUser.getAllRoles()) {	
				RolesPojo roleModel = new RolesPojo();
				BeanUtils.copyProperties(eachRoles, roleModel);
				allRolesModel.add(roleModel);
			}
			userPojo.setAllRoles(allRolesModel);
			authModel.setUser(userPojo);
			System.out.println(userPojo);
			authModel.setToken(jwtService.generateToken(userPojo));
		}else {
			throw new RuntimeException("Invalid Username/password!");
		}
		return authModel;
	}
		
	
}