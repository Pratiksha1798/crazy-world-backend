package com.crazyworld.in.service;

import java.util.List;

import com.crazyworld.in.model.CityPojo;

public interface ICityService {
	
	List<CityPojo> allCities(String name); 
}
