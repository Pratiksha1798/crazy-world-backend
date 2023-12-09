package com.crazyworld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crazyworld.in.dao.CountryRepository;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.model.CountryPojo;
import com.crazyworld.in.service.CountryServiceImpl;
import com.crazyworld.in.util.Continent;

@SpringBootTest
public class CountryServiceTestImpl {
	
	@Mock
	private CountryRepository countryRepository;
	
	@InjectMocks
	private CountryServiceImpl countryService;
	
	  @Test
	  public void testGetAllCountries() {
	        // Mocking the data
	        CountryEntity countryEntity = new CountryEntity();
	        countryEntity.setCode("US");
	        countryEntity.setName("United States");
	        countryEntity.setContinentType(Continent.NORTH_AMERICA);
	        countryEntity.setRegion("North America");
	        countryEntity.setSurfaceArea(new BigDecimal("9833517.85"));
	        countryEntity.setIndepYear((short) 1776);
	        countryEntity.setPopulation(331000000);
	        countryEntity.setLifeExpectancy(new BigDecimal("78.80"));
	        countryEntity.setGnp(new BigDecimal("21000000.00"));
	        countryEntity.setLocalName("United States");
	        countryEntity.setGovernmentForm("Federal Republic");
	        countryEntity.setHeadOfState("Joe Biden");
	        countryEntity.setCapital(1);
	        countryEntity.setCode2("US");

	        List<CountryEntity> countryEntities = new ArrayList<>();
	        countryEntities.add(countryEntity);

	        // Stubbing the behavior of the countryRepository
	        when(countryRepository.findAll()).thenReturn(countryEntities);

	        // Calling the method to be tested
	        List<CountryPojo> result = countryService.getAllCountries();

	        // Verifying the result
//	        assertNotNull(result);
//	        assertFalse(result.isEmpty());
	        assertEquals(1, result.size());

	        // Verifying that BeanUtils.copyProperties was called for each countryEntity
	       // verify(BeanUtils, times(1)).copyProperties(any(), any());

	        // You can also further assert the values of the result if needed
//	        CountryPojo countryPojo = result.get(0);
//	        assertEquals("US", countryPojo.getCode());
//	        assertEquals("United States", countryPojo.getName());
	        // Add more assertions as needed
	    }
}
