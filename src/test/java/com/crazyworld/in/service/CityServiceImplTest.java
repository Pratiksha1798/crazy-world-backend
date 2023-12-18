package com.crazyworld.in.service;

import com.crazyworld.in.exception.ValidateFieldException;
import com.crazyworld.in.service.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crazyworld.in.dao.CityRepository;
import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.exception.CityNotFoundException;
import com.crazyworld.in.exception.CountryNotFoundException;
import com.crazyworld.in.model.CityPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

	@Mock
	private CityRepository cityRepository;

	@InjectMocks
	private CityServiceImpl cityService;

	@Test
	void getAllCities() {

		List<CityEntity> cityEntities = Arrays.asList(new CityEntity(1, "City1", "District1", 100000, null),
				new CityEntity(2, "City2", "District2", 200000, null));
		when(cityRepository.findAll()).thenReturn(cityEntities);

		List<CityPojo> result = cityService.getAllCities();

		assertEquals(2, result.size());

		assertEquals(1, result.get(0).getId());
		assertEquals("City1", result.get(0).getName());
		assertEquals("District1", result.get(0).getDistrict());
		assertEquals(100000, result.get(0).getPopulation());

		assertEquals(2, result.get(1).getId());
		assertEquals("City2", result.get(1).getName());
		assertEquals("District2", result.get(1).getDistrict());
		assertEquals(200000, result.get(1).getPopulation());
	}

	@Test
	void getFirstTenCitiesByStartingCharacter() {

		char startChar = 'A';
		String startCharString = String.valueOf(startChar);
		List<String> cityNames = Arrays.asList("CityA1", "CityA2", "CityB1", "CityC1");
		when(cityRepository.findFirstTenCitiesByStartingCharacter(startCharString)).thenReturn(cityNames);

		List<String> result = cityService.getFirstTenCitiesByStartingCharacter(startChar);

		assertEquals(4, result.size());
		assertTrue(result.contains("CityA1"));
		assertTrue(result.contains("CityA2"));
		assertTrue(result.contains("CityB1"));
		assertTrue(result.contains("CityC1"));
	}

	@Test
	void getCityWithMaxPopulation_WhenCityExists() {

		CityEntity cityEntity = new CityEntity(1, "City1", "District1", 1500000, null);

		when(cityRepository.findCityWithMaxPopulation()).thenReturn(cityEntity);

		CityPojo result = cityService.getCityWithMaxPopulation();

		assertNotNull(result);
		assertEquals("City1", result.getName());
		assertEquals("District1", result.getDistrict());
		assertEquals(1500000, result.getPopulation());
	}

	@Test
	void getCityWithMaxPopulation_WhenNoCityExists() {

		when(cityRepository.findCityWithMaxPopulation()).thenReturn(null);

		CityNotFoundException exception = assertThrows(CityNotFoundException.class,
				() -> cityService.getCityWithMaxPopulation());

		assertEquals("city not found", exception.getMessage());
	}

	@Test
	void getAllCitiesAndDistrictsForCountry_WhenNoCitiesExist() {

		String nonExistentCountryCode = "NonExistentCode";

		when(cityRepository.getCitiesAndDistrictsfromCountryCode(nonExistentCountryCode)).thenReturn(new ArrayList<>());

		CityNotFoundException exception = assertThrows(CityNotFoundException.class,
				() -> cityService.getAllCitiesAndDistrictsForCountry(nonExistentCountryCode));

		assertEquals("city not found with country code " + nonExistentCountryCode, exception.getMessage());
	}

	@Test
	void findAllUniqueDistrictsAsCityPojo() {

		List<String> uniqueDistricts = Arrays.asList("District1", "District2", "District3");
		when(cityRepository.findAllUniqueDistricts()).thenReturn(uniqueDistricts);

		List<CityPojo> result = cityService.findAllUniqueDistrictsAsCityPojo();

		assertEquals(3, result.size());
		assertEquals("District1", result.get(0).getDistrict());
		assertEquals("District2", result.get(1).getDistrict());
		assertEquals("District3", result.get(2).getDistrict());
	}

	@Test
	void getAveragePopulationByDistrict_WhenDistrictExists() {

		String districtName = "District1";
		Integer avgPopulation = 500000;
		when(cityRepository.findAveragePopulationByDistrict(districtName)).thenReturn(avgPopulation);

		List<CityPojo> result = cityService.getAveragePopulationByDistrict(districtName);

		assertEquals(1, result.size());
		assertEquals("District1", result.get(0).getDistrict());
		assertEquals(500000, result.get(0).getPopulation());
	}

	@Test
	void getAveragePopulationByDistrict_WhenDistrictDoesNotExist() {

		String nonExistentDistrict = "NonExistentDistrict";
		when(cityRepository.findAveragePopulationByDistrict(nonExistentDistrict)).thenReturn(null);

		List<CityPojo> result = cityService.getAveragePopulationByDistrict(nonExistentDistrict);

		assertTrue(result.isEmpty());
	}

	@Test
	void updatePopulationForCity_WhenCityExists() {

		String cityName = "City1";
		int newPopulation = 150000;

		List<CityEntity> cityEntities = new ArrayList<>();
		CityEntity cityEntity = new CityEntity(1, cityName, "District1", 100000, null);
		cityEntities.add(cityEntity);

		when(cityRepository.findAllByName(cityName)).thenReturn(cityEntities);

		when(cityRepository.save(any(CityEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

		List<CityPojo> result = cityService.updatePopulationForCity(cityName, newPopulation);

		assertEquals(1, result.size());
		assertEquals("City1", result.get(0).getName());
		assertEquals("District1", result.get(0).getDistrict());
		assertEquals(150000, result.get(0).getPopulation());

	}

	@Test
	void updatePopulationForCity_WhenCityDoesNotExist() {
		String nonExistentCityName = "NonExistentCity";
		int newPopulation = 150000;

		when(cityRepository.findAllByName(nonExistentCityName)).thenReturn(new ArrayList<>());

		CityNotFoundException exception = assertThrows(CityNotFoundException.class,
				() -> cityService.updatePopulationForCity(nonExistentCityName, newPopulation));

		assertEquals("City not found with name " + nonExistentCityName, exception.getMessage());

	}

	@Test
	void updateDistrictForCity_WhenCityDoesNotExist() {

		String nonExistentCityName = "NonExistentCity";
		String newDistrict = "NewDistrict";

		when(cityRepository.findByName(nonExistentCityName)).thenReturn(null);

		CityNotFoundException exception = assertThrows(CityNotFoundException.class,
				() -> cityService.updateDistrictForCity(nonExistentCityName, newDistrict));

		assertEquals("City not found with name " + nonExistentCityName + " to update the population",
				exception.getMessage());

	}
}
