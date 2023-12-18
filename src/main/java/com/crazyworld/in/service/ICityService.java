package com.crazyworld.in.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crazyworld.in.model.CityPojo;

@Service
public interface ICityService {
	List<CityPojo> getAllCities();

	List<String> getFirstTenCitiesByStartingCharacter(char startChar);

	public CityPojo getCityWithMaxPopulation();
	
	List<CityPojo> getTopTenPopulatedCities();

	List<CityPojo> getAllCitiesAndDistrictsForCountry(String countryCode);

	List<CityPojo> findAllUniqueDistrictsAsCityPojo();

	List<CityPojo> getAveragePopulationByDistrict(String districtName);

	List<CityPojo> updatePopulationForCity(String cityName, int newPopulation);

	List<CityPojo> updateDistrictForCity(String cityName, String newDistrict);

}
