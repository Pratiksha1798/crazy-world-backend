package com.crazyworld.in.service;

import java.util.List;

import com.crazyworld.in.model.CountryPojo;

public interface ICountryService {
	
	public List<CountryPojo> getAllCountries();
	
	public CountryPojo getByCountryName(String name);
}
