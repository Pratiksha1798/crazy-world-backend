package com.crazyworld.in.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.crazyworld.in.dao.CountryRepository;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.exception.CountryNotFoundException;
import com.crazyworld.in.model.CountryLanguagePojo;
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
	    CountryRepository countryRepository;

	    @InjectMocks
	    CountryServiceImpl countryService;

	    @Test
	    void testGetByCountryName() {
	      
	        String countryName = "India";
	        CountryEntity mockCountryEntity = new CountryEntity();
	        mockCountryEntity.setName("India");

	        
	        when(countryRepository.findByName(countryName)).thenReturn(mockCountryEntity);

	        
	        CountryPojo result = countryService.getByCountryName(countryName);

	        
	        assertNotNull(result);
	        assertEquals("India", result.getName());
	        verify(countryRepository, times(1)).findByName(countryName);
	    }

	    @Test
	    void testGetByCountryNameNotFound() {
	        
	        String countryName = "NonExistentCountry";

	        
	        when(countryRepository.findByName(countryName)).thenReturn(null);

	       
	        CountryNotFoundException exception = assertThrows(CountryNotFoundException.class, () -> {
	            countryService.getByCountryName(countryName);
	        });

	        assertEquals("Country Details Not Found for Country : NonExistentCountry", exception.getMessage());
	        verify(countryRepository, times(1)).findByName(countryName);
	    }
	
	
	
	



	@Test
	void testGetCountryWithHighestLifeExpectancy() {
		
        CountryEntity country1 = new CountryEntity();
        country1.setName("Country1");
        country1.setLifeExpectancy(new BigDecimal("75.5"));

        CountryEntity country2 = new CountryEntity();
        country2.setName("Country2");
        country2.setLifeExpectancy(new BigDecimal("80.2"));

        List<CountryEntity> countries = new ArrayList<>();
        countries.add(country1);
        countries.add(country2);

      
        when(countryRepository.findAll()).thenReturn(countries);

        
        CountryPojo result = countryService.getCountryWithHighestLifeExpectancy();

        
        assertNotNull(result);
        assertEquals("Country2", result.getName());
        assertEquals(new BigDecimal("80.2"), result.getLifeExpectancy());
        verify(countryRepository, times(1)).findAll();
	}

	@Test
	void testGetLanguagesByRegion() {
		
        String region = "Asia";
        List<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Hindi");

        
        when(countryRepository.findLanguagesByRegion(region)).thenReturn(languages);

        
        List<CountryLanguagePojo> result = countryService.getLanguagesByRegion(region);

        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getLanguage());
        assertEquals("Hindi", result.get(1).getLanguage());
        verify(countryRepository, times(1)).findLanguagesByRegion(region);
	}

	@Test
	void testGetDistinctGovernmentForms() {
		 
        List<String> distinctGovernmentForms = Arrays.asList("Republic", "Monarchy", "Democracy");

        
        when(countryRepository.findDistinctGovernmentForms()).thenReturn(distinctGovernmentForms);

       
        List<CountryPojo> result = countryService.getDistinctGovernmentForms();

        
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Republic", result.get(0).getGovernmentForm());
        assertEquals("Monarchy", result.get(1).getGovernmentForm());
        assertEquals("Democracy", result.get(2).getGovernmentForm());
        verify(countryRepository, times(1)).findDistinctGovernmentForms();
	}

	@Test
	void testGetTop10PopulatedCountries() {
		
        List<CountryEntity> top10CountriesData = Arrays.asList(
            new CountryEntity("USA", "United States", Continent.NORTH_AMERICA, "North America",
                    new BigDecimal("98335100"), (short) 1776, 331449281, new BigDecimal("78.8"),
                    new BigDecimal("19500000"), new BigDecimal("18624400"), "United States", "Federal Republic",
                    "Joe Biden", 1, "US", null, null),
            new CountryEntity("PHL", "Philippines", Continent.ASIA, "Southeast Asia", new BigDecimal(300000.00),
					(short) 1946, 109581078, new BigDecimal(71.20), new BigDecimal(149743.00),
					new BigDecimal(103219.00), "Pilipinas", "Republic", "Rodrigo Duterte", 766, "PH", null, null),
            new CountryEntity("ARG", "Argentina", Continent.SOUTH_AMERICA, "South America", new BigDecimal(2780400.00),
					(short) 1816, 45195777, new BigDecimal(76.70), new BigDecimal(3402385.00),
					new BigDecimal(3233105.00), "Argentina", "Federal Republic", "Alberto Fernández", 69, "AR", null,
					null),
            new CountryEntity("DEU", "Germany", Continent.EUROPE, "Central Europe", new BigDecimal(357022.00),
            	    (short) 1871, 83132799, new BigDecimal(81.40), new BigDecimal(4128282.00),
            	    new BigDecimal(3802359.00), "Federal Republic of Germany", "Federal Republic", "Olaf Scholz", 49, "DE", null, null),


            new	CountryEntity("CHN", "China", Continent.ASIA, "East Asia", new BigDecimal(9596961.00),
            	    (short) 1949, 1444216107, new BigDecimal(76.90), new BigDecimal(14166692.00),
            	    new BigDecimal(14693430.00), "People's Republic of China", "Socialist Republic", "Xi Jinping", 86, "CN", null, null),

            new	CountryEntity("IND", "India", Continent.ASIA, "South Asia", new BigDecimal(3287263.00),
            	    (short) 1947, 1393409038, new BigDecimal(69.70), new BigDecimal(2043415.00),
            	    new BigDecimal(2039078.00), "Republic of India", "Federal Republic", "Narendra Modi", 91, "IN", null, null),

            new	CountryEntity("BRA", "Brazil", Continent.SOUTH_AMERICA, "South America", new BigDecimal(8515767.00),
            	    (short) 1822, 213993437, new BigDecimal(75.70), new BigDecimal(3180210.00),
            	    new BigDecimal(3146414.00), "Federative Republic of Brazil", "Federal Republic", "Jair Bolsonaro", 55, "BR", null, null),

            new	CountryEntity("RUS", "Russia", Continent.EUROPE, "Eastern Europe", new BigDecimal(17098242.00),
            	    (short) 1991, 145912025, new BigDecimal(72.60), new BigDecimal(4166098.00),
            	    new BigDecimal(4601600.00), "Russian Federation", "Federal Republic", "Vladimir Putin", 7, "RU", null, null),

            new	CountryEntity("NGA", "Nigeria", Continent.AFRICA, "West Africa", new BigDecimal(923768.00),
            	    (short) 1960, 211400708, new BigDecimal(54.30), new BigDecimal(514005.00),
            	    new BigDecimal(468052.00), "Federal Republic of Nigeria", "Federal Republic", "Muhammadu Buhari", 234, "NG", null, null),

            new	CountryEntity("FRA", "France", Continent.EUROPE, "Western Europe", new BigDecimal(551695.00),
            	    (short) 843, 65273511, new BigDecimal(82.70), new BigDecimal(2962582.00),
            	    new BigDecimal(2789450.00), "French Republic", "Semi-Presidential Republic", "Emmanuel Macron", 33, "FR", null, null)

            
        );

       
        when(countryRepository.findTop10PopulatedCountries()).thenReturn(top10CountriesData);

        
        List<CountryPojo> result = countryService.getTop10PopulatedCountries();

       
        assertNotNull(result);
        assertEquals(10, result.size());
        assertEquals("United States", result.get(0).getName());
        assertEquals(331449281, result.get(0).getPopulation());
       

        verify(countryRepository, times(1)).findTop10PopulatedCountries();
	}

	@Test
	void testUpdateHeadOfState() {
		
        String countryName = "TestCountry";
        CountryEntity existingCountry = new CountryEntity();
        existingCountry.setName(countryName);

        
        String newHeadOfState = "NewHeadOfState";
        Map<String, Object> updates = new HashMap<>();
        updates.put("headOfState", newHeadOfState);

       
        when(countryRepository.findByName(countryName)).thenReturn(existingCountry);
        when(countryRepository.save(existingCountry)).thenReturn(existingCountry);

       
        CountryPojo updatedCountry = countryService.updateHeadOfState(countryName, updates);

        
        assertEquals(newHeadOfState, updatedCountry.getHeadOfState());
	}

	@Test
	void testGetCountriesWithLanguageCount() {
		  
        List<Object[]> mockData = new ArrayList<>();
        mockData.add(new Object[]{"Country1", 5});
        mockData.add(new Object[]{"Country2", 3});

        
        when(countryRepository.findCountriesWithLanguageCount()).thenReturn(mockData);

        
        List<Object[]> result = countryService.getCountriesWithLanguageCount();

        
        verify(countryRepository, times(1)).findCountriesWithLanguageCount();
        assertEquals(2, result.size()); 
        assertEquals("Country1", result.get(0)[0]); 
        assertEquals(5, result.get(0)[1]);
        assertEquals("Country2", result.get(1)[0]);
        assertEquals(3, result.get(1)[1]);
    }
	}


	
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
