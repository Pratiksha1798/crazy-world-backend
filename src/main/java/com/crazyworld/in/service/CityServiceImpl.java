package com.crazyworld.in.service;

import com.crazyworld.in.dao.CityRepository;
import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.model.CountryPojo;

import org.apache.el.stream.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	CityRepository cityRepository;

	@Override
	public List<CityPojo> getAllCities() {
		List<CityEntity> allCity = cityRepository.findAll();
		List<CityPojo> cityPojos = new ArrayList<>();

		for (CityEntity cityEntity : allCity) {
			CityPojo cityPojo = new CityPojo();
			BeanUtils.copyProperties(cityEntity, cityPojo);
			cityPojos.add(cityPojo);
		}
		return cityPojos;
	}

	@Override
	public List<String> getFirstTenCitiesByStartingCharacter(char startChar) {
		String startCharString = String.valueOf(startChar);
		return cityRepository.findFirstTenCitiesByStartingCharacter(startCharString);
	}

	@Override
	public CityPojo getCityWithMaxPopulation() {
		CityEntity cityEntity = cityRepository.findCityWithMaxPopulation();
		CityPojo cities = new CityPojo();

		if (cityEntity != null) {
			BeanUtils.copyProperties(cityEntity, cities);
		}

		return cities;
	}

	private CityPojo convertToCityPojo(CityEntity cityEntity) {
		CityPojo cityPojo = new CityPojo();

		return cityPojo;
	}

	@Override
	public List<CityPojo> getTopTenPopulatedCities() {
		List<CityEntity> cityEntities = cityRepository.findTop10PopulatedCities();
		List<CityPojo> topTenCities = new ArrayList<>();

		for (CityEntity cityEntity : cityEntities) {
			CityPojo cityPojo = new CityPojo();
			BeanUtils.copyProperties(cityEntity, cityPojo);
			topTenCities.add(cityPojo);
		}

		return topTenCities;
	}

	@Override
	public List<CityPojo> getAllCitiesAndDistrictsForCountry(String countryCode) {
		List<CityEntity> cityEntities = cityRepository.getCitiesAndDistrictsfromCountryCode(countryCode);
		List<CityPojo> citiesAndDistricts = new ArrayList<>();

		for (CityEntity cityEntity : cityEntities) {          
			citiesAndDistricts.add(convertToCityPojo(cityEntity));
			CityPojo cityPojo = new CityPojo();
			cityPojo.setName(cityEntity.getName());
			cityPojo.setDistrict(cityEntity.getDistrict());
			BeanUtils.copyProperties(cityEntity, cityPojo);
			citiesAndDistricts.add(cityPojo);
		}

		return citiesAndDistricts;
	}

	@Override
	public List<CityPojo> findAllUniqueDistrictsAsCityPojo() {
		List<String> uniqueDistricts = cityRepository.findAllUniqueDistricts();
		List<CityPojo> districtPojoList = new ArrayList<>();

		for (String district : uniqueDistricts) {
			CityPojo cityPojo = new CityPojo();
			cityPojo.setDistrict(district);
			districtPojoList.add(cityPojo);
		}

		return districtPojoList;
	}

	public List<CityPojo> getAveragePopulationByDistrict(String districtName) {
		Integer avgPopulation = cityRepository.findAveragePopulationByDistrict(districtName);
		if (avgPopulation != null) {
			CityPojo cityPojo = new CityPojo();
			cityPojo.setDistrict(districtName);
			cityPojo.setPopulation(avgPopulation);
			return Collections.singletonList(cityPojo);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<CityPojo> updatePopulationForCity(String cityName, int newPopulation) {
		List<CityPojo> updatedCities = new ArrayList<>();
		List<CityEntity> cityEntities = cityRepository.findAllByName(cityName);
		for (CityEntity cityEntity : cityEntities) {
			cityEntity.setPopulation(newPopulation);
			cityRepository.save(cityEntity);

			CityPojo updatedCity = new CityPojo();
			BeanUtils.copyProperties(cityEntity, updatedCity);
			updatedCities.add(updatedCity);
		}

		return updatedCities;
	}

	@Override
	public List<CityPojo> updateDistrictForCity(String cityName, String newDistrict) {
		CityEntity cityEntity = cityRepository.findByName(cityName);
		List<CityPojo> updatedCities = new ArrayList<>();

		if (cityEntity != null) {
			cityEntity.setDistrict(newDistrict);
			cityRepository.save(cityEntity);

			List<CityEntity> updatedEntities = cityRepository.findAllByName(cityName);
			for (CityEntity updatedEntity : updatedEntities) {
				CityPojo updatedCity = new CityPojo();
				BeanUtils.copyProperties(updatedEntity, updatedCity);
				updatedCities.add(updatedCity);
			}
		}
		return updatedCities;
	}

}
