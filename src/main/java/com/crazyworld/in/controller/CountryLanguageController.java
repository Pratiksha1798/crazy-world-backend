package com.crazyworld.in.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.service.CountryLanguageServiceImpl;
import com.crazyworld.in.util.IsOfficial;
import com.crazyworld.in.util.MaxSpokenInEachCountryResoponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/countrylang")
public class CountryLanguageController {

	@Autowired
	private CountryLanguageServiceImpl countryLanguageService;

	@GetMapping("/{region}/alllanguages")
	public ResponseEntity<List<CountryLanguagePojo>> getAllLanguagesByRegion(@PathVariable String region) {
		List<CountryLanguagePojo> listOfLanguages = countryLanguageService.getAllLanguagesByRegion(region);
		return new ResponseEntity<>(listOfLanguages, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CountryLanguagePojo>> getAllUniqueLanguages() {
		List<CountryLanguagePojo> listOfLanguages = countryLanguageService.getAllCountryLanguages();
		return new ResponseEntity<>(listOfLanguages, HttpStatus.OK);
	}

	@GetMapping("/{countrycode}")
	public ResponseEntity<List<CountryLanguagePojo>> getCountryLanguagesByCode(
			@PathVariable("countrycode") String countrycode) {

		List<CountryLanguagePojo> listOfLanguages = countryLanguageService.getCountryLanguagesByCode(countrycode);

		return new ResponseEntity<>(listOfLanguages, HttpStatus.OK);
	}

	@GetMapping("/allofficial")
	public List<CountryLanguagePojo> getAllOfficialCountryLanguages() {
		List<CountryLanguagePojo> listofLanguage = countryLanguageService.getAllOfficialCountryLanguages();
		return countryLanguageService.getAllOfficialCountryLanguages();
	}

	@GetMapping("unofficial/{countrycode}")
	public ResponseEntity<List<CountryLanguagePojo>> getUnofficialCountryLanguagesByCode(
			@PathVariable("countrycode") String countrycode) {

		List<CountryLanguagePojo> listOfLanguage = countryLanguageService
				.getUnofficialCountryLanguagesByCode(countrycode);
		return new ResponseEntity<>(listOfLanguage, HttpStatus.OK);
	}

	@GetMapping("maxspokenlang")
	public ResponseEntity<List<CountryLanguagePojo>> getMaxspokenLanguages() {
		List<CountryLanguagePojo> listOfLanguages = countryLanguageService.getMaxSpokenLanguage();
		return new ResponseEntity<>(listOfLanguages, HttpStatus.OK);
	}

	@GetMapping("maxspokenlang/{countrycode}")
	public ResponseEntity<CountryLanguagePojo> getMaxspokenLanguage(@PathVariable("countrycode") String countrycode) {
		CountryLanguagePojo countryLanguagePojo = countryLanguageService.getMaxSpokenLanguageByCode(countrycode);
		return new ResponseEntity<>(countryLanguagePojo, HttpStatus.OK);
	}

	@PatchMapping("updatepercentage/{countrycode}/{language}/{percentage}")
	public ResponseEntity<String> getUpdatePercentage(@Valid @PathVariable("countrycode") String countrycode,
			@PathVariable("language") String language, @PathVariable("percentage") BigDecimal percentage) {
		countryLanguageService.updatePercentage(countrycode, language, percentage);
		return new ResponseEntity<>("Updated percentage", HttpStatus.OK);
	}

	@PatchMapping("/setofficial/{ctycode}/{lang}")
	public ResponseEntity<String> setOfficialStatus(@Valid @PathVariable("ctycode") String ctycode,
			@PathVariable("lang") String lang) {
		countryLanguageService.setOfficialStatus(ctycode, lang);
		return new ResponseEntity<>("Updated isOffical", HttpStatus.OK);
	}

}
