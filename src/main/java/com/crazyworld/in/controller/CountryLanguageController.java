package com.crazyworld.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.service.CountryLanguageServiceImpl;

@RestController
@RequestMapping("/api/countries")
public class CountryLanguageController {
	
	@Autowired
	private CountryLanguageServiceImpl countryLanguageService;

	@GetMapping("/{region}/alllanguages")
    public ResponseEntity<List<CountryLanguagePojo>> getAllLanguagesByRegion(@PathVariable String region) {
		List<CountryLanguagePojo> listOfLanguages=countryLanguageService.getAllLanguagesByRegion(region);
        return new ResponseEntity<> (listOfLanguages,HttpStatus.OK);
    }
}
