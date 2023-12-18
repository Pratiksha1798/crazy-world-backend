package com.crazyworld.in.service;

import com.crazyworld.in.model.AuthPojo;
import com.crazyworld.in.model.UserPojo;

public interface UserService {
	AuthPojo authenticateUser(UserPojo userPojo);
}
