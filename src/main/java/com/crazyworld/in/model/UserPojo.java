package com.crazyworld.in.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPojo {
	int userId;
	String userName;
	String userPassword;
	List<RolesPojo> allRoles;

}
