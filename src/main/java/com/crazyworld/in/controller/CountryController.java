package com.crazyworld.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.CountryPojo;
import com.crazyworld.in.service.CountryServiceImpl;

@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	@Autowired
	CountryServiceImpl countryServiceImpl;
	
	@GetMapping("/countries")
	public List<CountryPojo> getAllCountries(){
		List<CountryPojo> allCountries=countryServiceImpl.getAllCountries();
		return allCountries;
	}
	
	@GetMapping("/countries/{name}")
	public CountryPojo getByName( @PathVariable String name) {
		CountryPojo countryName=countryServiceImpl.getByCountryName(name);
		return countryName;
		
	}
	
	

}
