package com.crazyworld.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.service.CountryServiceImpl;

@RestController
@RequestMapping("/api/countries")
public class CityController {
	
	@Autowired
	CountryServiceImpl countryServiceImpl;
	
	  	@GetMapping("/{name}/cities")
	    public ResponseEntity<List<CityPojo>> getCitiesByCountryName(@PathVariable String name) {
	        List<CityPojo> cities = countryServiceImpl.getCitiesByCountryName(name);
	        return new ResponseEntity<>(cities, HttpStatus.OK);
	    }
	  
	  	@GetMapping("/{name}/citycount")
	    public ResponseEntity<String> getCityCountByCountryName(@PathVariable String name) {
	        long cityCount = countryServiceImpl.getCityCountByCountryName(name);
	        String response = "Total count of cities in " + name + ": " + cityCount;
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  	
}
