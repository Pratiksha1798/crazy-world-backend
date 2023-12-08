package com.crazyworld.in.service;

import java.util.List;

import com.crazyworld.in.model.CountryLanguagePojo;

public interface ICountryLanguageService {
	
	  List<CountryLanguagePojo> getAllLanguagesByRegion(String region);

}
