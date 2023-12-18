package com.crazyworld.in.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminPojo {
	
    private int userId;
	
	
	private String userName;
	
	
	private String userPassword;

}
