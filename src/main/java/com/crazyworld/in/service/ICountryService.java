package com.crazyworld.in.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.model.CountryGnpPojo;
import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.model.CountryPojo;

import com.crazyworld.in.util.IsOfficial;

import com.crazyworld.in.model.CountryWithCityCountDto;


public interface ICountryService {
	
	public List<CountryPojo> getAllCountries();
		
	public List<CityPojo> getCitiesByCountryName(String name);
	
	public long getCityCountByCountryName(String name) ;
		
	public List<CountryGnpPojo> getTop10CountriesByGnp() ;
	 	
	public String getPopulationAndLifeExpectancy(String countrycode);
	
	public CountryPojo getCountryByCode(String code);
		
	public CountryPojo updateGnp(String name, Map<String, Object> updates);
	
	public CountryPojo updatePopulation(String name, Map<String, Object> updates);
		
	public List<CountryWithCityCountDto> getCountriesWithCityCount();

}
