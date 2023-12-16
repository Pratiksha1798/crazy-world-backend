package com.crazyworld.in.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crazyworld.in.dao.CityRepository;
import com.crazyworld.in.dao.CountryRepository;
import com.crazyworld.in.dao.CountryRepository.PopulationAndLifeExpectancy;
import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.exception.CountryNotFoundException;
import com.crazyworld.in.exception.ValidateFieldException;
import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.model.CountryGnpPojo;
import com.crazyworld.in.model.CountryPojo;
import com.crazyworld.in.util.Continent;

@SpringBootTest
class CountryServiceImplTest {
	
	
	 @Mock
	 private CountryRepository countryRepository;

	 @InjectMocks
	 private CountryServiceImpl countryService;
	 
	 @Mock
	 private CityRepository cityRepository;
	  

	 @InjectMocks
	 private CityServiceImpl cityService;

	@Test
	void testGetAllCountries() {
	     when(countryRepository.findAll()).thenReturn(getMockCountryEntities());

	        List<CountryPojo> result = countryService.getAllCountries();

	        assertNotNull(result);
	        assertFalse(result.isEmpty());
	        assertEquals(1, result.size());

	        CountryPojo countryPojo = result.get(0);
	        assertEquals("US", countryPojo.getCode());
	        assertEquals("United States", countryPojo.getName());
	    }

	
	@Test
	public void testGetAllCountriesEmptyList() {
	    when(countryRepository.findAll()).thenReturn(new ArrayList<>());
	
	    CountryNotFoundException exception = assertThrows(CountryNotFoundException.class,
	            () -> countryService.getAllCountries());
	
	    assertEquals("Country details not found", exception.getMessage());
	
	    verify(countryRepository, times(1)).findAll();
	
	    verifyNoMoreInteractions(countryRepository);
	}

	    private List<CountryEntity> getMockCountryEntities() {
	        CountryEntity countryEntity = new CountryEntity();
	        countryEntity.setCode("US");
	        countryEntity.setName("United States");
	        countryEntity.setContinentType(Continent.NORTH_AMERICA);
	        countryEntity.setRegion("North America");
	        countryEntity.setSurfaceArea(new BigDecimal("9833517.85"));
	        countryEntity.setIndepYear((short) 1776);
	        countryEntity.setPopulation(331000000);
	        countryEntity.setLifeExpectancy(new BigDecimal("78.80"));
	        countryEntity.setGnp(new Double("21000000.00"));
	        countryEntity.setLocalName("United States");
	        countryEntity.setGovernmentForm("Federal Republic");
	        countryEntity.setHeadOfState("Joe Biden");
	        countryEntity.setCapital(1);
	        countryEntity.setCode2("US");

	        List<CountryEntity> countryEntities = new ArrayList<>();
	        countryEntities.add(countryEntity);

	        return countryEntities;
	    }
	


	    @Test
	    public void testGetCitiesByCountryName() {
	        String countryName = "USA";
	        List<CityEntity> mockCities = getMockCities();

	        when(cityRepository.findByCountryEntityName(countryName)).thenReturn(mockCities);

	        List<CityPojo> result = countryService.getCitiesByCountryName(countryName);

	        verify(cityRepository, times(1)).findByCountryEntityName(countryName);

	        assertNotNull(result);
	        assertFalse(result.isEmpty());
	        assertEquals(mockCities.size(), result.size());
	        assertEquals("New York", result.get(0).getName());
	    }

	    @Test
	    public void testGetCitiesByCountryNameCountryNotFound() {
	        String countryName = "NonExistentCountry";
	        when(cityRepository.findByCountryEntityName(countryName)).thenReturn(new ArrayList<>());
	        assertThrows(CountryNotFoundException.class,
	                () -> countryService.getCitiesByCountryName(countryName));
	        verify(cityRepository, times(1)).findByCountryEntityName(countryName);
	    }

	    private List<CityEntity> getMockCities() {
	        List<CityEntity> mockCities = new ArrayList<>();

	        CityEntity city1 = new CityEntity();
	        city1.setId(1);
	        city1.setName("New York");
	        city1.setDistrict("New York");
	        city1.setPopulation(8175133);

	        CityEntity city2 = new CityEntity();
	        city2.setId(2);
	        city2.setName("Los Angeles");
	        city2.setDistrict("California");
	        city2.setPopulation(3792621);

	        mockCities.add(city1);
	        mockCities.add(city2);

	        return mockCities;
	    }

	
	
	

	@Test
	void testGetCityCountByCountryName() {
		String countryName = "Country1";
        long cityCount = 5L;
        when(countryRepository.countCitiesByName(countryName)).thenReturn(cityCount);
        long result = countryService.getCityCountByCountryName(countryName);
        verify(countryRepository, times(1)).countCitiesByName(countryName);
        assertEquals(cityCount, result);
	}


	    @Test
	    void testGetPopulationAndLifeExpectancy() {
	     
	    	  String countryCode = "US";
	          Integer population = 331000000;
	          BigDecimal lifeExpectancy = new BigDecimal("78.80");

	          when(countryRepository.findPopulationAndLifeExpectancyByCountryCode(countryCode))
	                  .thenReturn(new PopulationAndLifeExpectancy() {
	                      @Override
	                      public String getCode() {
	                          return countryCode;
	                      }

	                      @Override
	                      public Integer getPopulation() {
	                          return population;
	                      }

	                      @Override
	                      public BigDecimal getLifeExpectancy() {
	                          return lifeExpectancy;
	                      }
	                  });

	          String result = countryService.getPopulationAndLifeExpectancy(countryCode);

	          verify(countryRepository, times(1)).findPopulationAndLifeExpectancyByCountryCode(countryCode);

	          assertNotNull(result);
	          assertTrue(result.contains("Country Code: " + countryCode));
	          assertTrue(result.contains("Population: " + population));
	          assertTrue(result.contains("Life Expectancy: " + lifeExpectancy));
	      }

	      @Test
	      public void testGetPopulationAndLifeExpectancyCountryNotFound() {
	          String countryCode = "NonExistentCountry";

	          when(countryRepository.findPopulationAndLifeExpectancyByCountryCode(countryCode)).thenReturn(null);

	          assertThrows(CountryNotFoundException.class,
	                  () -> countryService.getPopulationAndLifeExpectancy(countryCode));

	          verify(countryRepository, times(1)).findPopulationAndLifeExpectancyByCountryCode(countryCode);
	      }

	   
	        
	    
	

		@Test
		void testUpdateGnp() {
		  CountryEntity country1 = new CountryEntity();
	        country1.setName("Country1");
	        country1.setGnp(new Double("1000000.00"));

	        CountryEntity country2 = new CountryEntity();
	        country2.setName("Country2");
	        country2.setGnp(new Double("900000.00"));

	        when(countryRepository.findTop10ByGnp()).thenReturn(Arrays.asList(country1, country2));

	        List<CountryGnpPojo> result = countryService.getTop10CountriesByGnp();

	        verify(countryRepository, times(1)).findTop10ByGnp();

	        assertNotNull(result);
	        assertEquals(2, result.size());

	        assertEquals("Country1", result.get(0).getName());
	        assertEquals(new Double("1000000.00"), result.get(0).getGnp());
	        assertEquals("Country2", result.get(1).getName());
	        assertEquals(new Double("900000.00"), result.get(1).getGnp());
	    }
	

		@Test
		void testUpdatePopulation() {
		    String countryName = "India";
		    int newPopulation = 1000;

		    CountryEntity mockCountryEntity = new CountryEntity();
		    when(countryRepository.findByName(countryName)).thenReturn(mockCountryEntity);
		    when(countryRepository.save(any())).thenReturn(mockCountryEntity);

		    Map<String, Object> updates = new HashMap<>();
		    updates.put("population", newPopulation);

		    CountryPojo result = countryService.updatePopulation(countryName, updates);

		    verify(countryRepository, times(1)).findByName(countryName);
		    verify(countryRepository, times(1)).save(any());

		    assertNotNull(result);
		    assertEquals(newPopulation, result.getPopulation());
		}
		
		@Test
		void testUpdatePopulationWithException() {
		    String countryName = "India";
		    int newPopulation = -100;  

		    CountryEntity mockCountryEntity = new CountryEntity();
		    when(countryRepository.findByName(countryName)).thenReturn(mockCountryEntity);

		    Map<String, Object> updates = new HashMap<>();
		    updates.put("population", newPopulation);

		    assertThrows(ValidateFieldException.class,
		            () -> countryService.updatePopulation(countryName, updates));

		    verify(countryRepository, times(1)).findByName(countryName);
		    verify(countryRepository, never()).save(any());
		}



}
