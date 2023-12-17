
package com.crazyworld.in.service;

import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.model.CountryPojo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.crazyworld.in.dao.CountryLanguageRepository;

import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.dao.entity.CountryLanguageEntity;
import com.crazyworld.in.exception.DataNotFoundException;
import com.crazyworld.in.exception.LanguageNotFoundException;
import com.crazyworld.in.util.Continent;
import com.crazyworld.in.util.IsOfficial;

@SpringBootTest
class CountryLanguageServiceImplTest {

	@Mock
	CountryLanguageRepository countrylanguageRepository;

	@InjectMocks
	CountryLanguageServiceImpl countryLanguageImpl;

	CountryEntity[] countryEntitiesArr = {
			 new CountryEntity("PHL", "Philippines", Continent.ASIA, "Southeast Asia", new BigDecimal(300000.00),
			            (short) 1946, 109581078, new BigDecimal(71.20), 149743.00, new BigDecimal(103219.00),
			            "Pilipinas", "Republic", "Rodrigo Duterte", 766, "PH", "",
			            "Rich cultural history", "Archipelagic country with diverse landscapes", "Cultural festivals and celebrations",
			            null, null),

			    new CountryEntity("ARG", "Argentina", Continent.SOUTH_AMERICA, "South America", new BigDecimal(2780400.00),
			            (short) 1816, 45195777, new BigDecimal(76.70), 3402385.00, new BigDecimal(3233105.00),
			            "Argentina", "Federal Republic", "Alberto Fernández", 69, "AR", "",
			            "Rich history of indigenous cultures", "Diverse geography with mountains and plains",
			            "Cultural traditions influenced by European and indigenous roots", null, null),
			};

	

	CountryLanguageEntity[] entities = new CountryLanguageEntity[] {
			new CountryLanguageEntity("ARG-Spanish", IsOfficial.F, new BigDecimal(80.0), countryEntitiesArr[0]),
			new CountryLanguageEntity("ARG-English", IsOfficial.T, new BigDecimal(55.0), countryEntitiesArr[0]),
			new CountryLanguageEntity("PHL-Filipino", IsOfficial.T, new BigDecimal(55.0), countryEntitiesArr[1]),
			new CountryLanguageEntity("PHL-Filipino", IsOfficial.F, new BigDecimal(55.0), countryEntitiesArr[1]),

	};

	List<CountryEntity> countryEntities = Arrays.asList(countryEntitiesArr);

	List<CountryLanguageEntity> countryLanguageEntities = Arrays.asList(entities);

	List<CountryLanguagePojo> countryLanguagePojos = convertEntitiesToPojos(countryLanguageEntities);

	List<CountryLanguagePojo> convertEntitiesToPojos(List<CountryLanguageEntity> entities) {
		return entities.stream().map(enitity -> {
			CountryLanguagePojo countryLanguagePojo = new CountryLanguagePojo();
			BeanUtils.copyProperties(enitity, countryLanguagePojo);
			CountryEntity countryEntity = enitity.getCountryEntity();
			CountryPojo countryPojo = new CountryPojo();
			BeanUtils.copyProperties(countryEntity, countryPojo);
			countryLanguagePojo.setCountryPojo(countryPojo);
			return countryLanguagePojo;
		}).toList();
	}

	@Test
	void testGetAllCountryLanguages() {

		when(countrylanguageRepository.findAll()).thenReturn(countryLanguageEntities);
		List<CountryLanguagePojo> expectedLanguages = countryLanguagePojos;
		List<CountryLanguagePojo> actualAllLanguages = countryLanguageImpl.getAllCountryLanguages();
		assertEquals(expectedLanguages.size(), actualAllLanguages.size());
		verify(countrylanguageRepository).findAll();
	}

	@Test
	void testGetAllCountryLanguagesException() {
		when(countrylanguageRepository.findAll()).thenReturn(new ArrayList<CountryLanguageEntity>());
		String expectedMessage = "Country Language Database is empty";
		DataNotFoundException actualException = assertThrows(DataNotFoundException.class,
				() -> countryLanguageImpl.getAllCountryLanguages());
		String actualMessage = actualException.getMessage();
		assertEquals(expectedMessage, actualMessage);
		verify(countrylanguageRepository).findAll();
	}

	@Test
	void testGetCountryLanguagesByCode() {
		String code = "ARG";

		List<CountryLanguageEntity> stuffedCountryLanguageEntities = countryLanguageEntities.stream()
				.filter(entity -> entity.getCountryEntity().equals(countryEntitiesArr[1])).toList();
		List<CountryEntity> stuffedCountryEntities = countryEntities.stream()
				.filter(entity -> entity.getCode().equals(code)).toList();

		when(countrylanguageRepository.FindCountryByCode(code)).thenReturn(stuffedCountryEntities);
		when(countrylanguageRepository.findByCountryEntity(countryEntitiesArr[1]))
				.thenReturn(stuffedCountryLanguageEntities);

		List<CountryLanguagePojo> expectedLanguages = convertEntitiesToPojos(stuffedCountryLanguageEntities);
		List<CountryLanguagePojo> actualLanguages = countryLanguageImpl.getCountryLanguagesByCode("ARG");

		assertEquals(expectedLanguages.size(), actualLanguages.size());
		verify(countrylanguageRepository).findByCountryEntity(countryEntitiesArr[1]);
	}

	@Test
	void testGetAllOfficialCountryLanguages() {
		when(countrylanguageRepository.findAll()).thenReturn(countryLanguageEntities);

		List<CountryLanguagePojo> expected = new ArrayList<>();
		for (CountryLanguageEntity entity : countryLanguageEntities) {
			if (entity.getIsOfficialType() == IsOfficial.T) {
				CountryLanguagePojo pojo = new CountryLanguagePojo();
				BeanUtils.copyProperties(entity, pojo);
				pojo.setIsOfficial(entity.getIsOfficialType());
				expected.add(pojo);
			}
		}

		List<CountryLanguagePojo> actual = countryLanguageImpl.getAllOfficialCountryLanguages();

		assertEquals(expected.size(), actual.size());
		verify(countrylanguageRepository).findAll();

	}

	@Test
	void testGetAllOfficialCountryLanguagesException() {
		when(countrylanguageRepository.findAll()).thenReturn(new ArrayList<>());

		LanguageNotFoundException exception = assertThrows(LanguageNotFoundException.class,
				() -> countryLanguageImpl.getAllOfficialCountryLanguages());

		assertEquals("Country Language Database is empty", exception.getMessage());

		verify(countrylanguageRepository).findAll();

	}

	@Test
	void testGetMaxSpokenLanguage() {
		when(countrylanguageRepository.findMaxSpokenLanguageEntitiesInEachCountry())
				.thenReturn(countryLanguageEntities);

		List<CountryLanguagePojo> expected = convertEntitiesToPojos(countryLanguageEntities);
		List<CountryLanguagePojo> actual = countryLanguageImpl.getMaxSpokenLanguage();

		assertEquals(expected.size(), actual.size());
		verify(countrylanguageRepository).findMaxSpokenLanguageEntitiesInEachCountry();

	}

	@Test
	void testGetMaxSpokenLanguageException() {
		when(countrylanguageRepository.findMaxSpokenLanguageEntitiesInEachCountry()).thenReturn(new ArrayList<>());

		LanguageNotFoundException exception = assertThrows(LanguageNotFoundException.class,
				() -> countryLanguageImpl.getMaxSpokenLanguage());

		assertEquals("Languages are not available", exception.getMessage());

		verify(countrylanguageRepository).findMaxSpokenLanguageEntitiesInEachCountry();

	}

	@Test
	void testUpdatePercentage() {
		String code = "ARG";
		String language = "Spanish";
		BigDecimal percentage = new BigDecimal(90.0);

		when(countrylanguageRepository.FindCountryByCode(code)).thenReturn(countryEntities);

		Optional<CountryLanguageEntity> optionalEntity = Optional.of(countryLanguageEntities.get(0));

		when(countrylanguageRepository.findById(language)).thenReturn(optionalEntity);
		when(countrylanguageRepository.updateLanguageEntity(language, percentage)).thenReturn(1);

		assertDoesNotThrow(() -> countryLanguageImpl.updatePercentage(code, language, percentage));

		verify(countrylanguageRepository).FindCountryByCode(code);
		verify(countrylanguageRepository).findById(language);
		verify(countrylanguageRepository).updateLanguageEntity(language, percentage);

	}

	@Test
	void testUpdatePercentageException() {
		String code = "ARG";
		String language = "Spanish";
		BigDecimal percentage = new BigDecimal(90.0);

		when(countrylanguageRepository.FindCountryByCode(code)).thenReturn(countryEntities);

		Optional<CountryLanguageEntity> optionalEntity = Optional.empty();
		when(countrylanguageRepository.findById(language)).thenReturn(optionalEntity);

		LanguageNotFoundException exception = assertThrows(LanguageNotFoundException.class,
				() -> countryLanguageImpl.updatePercentage(code, language, percentage));

		assertEquals(language + " not found.", exception.getMessage());

		verify(countrylanguageRepository).FindCountryByCode(code);
		verify(countrylanguageRepository).findById(language);
		verify(countrylanguageRepository, never()).updateLanguageEntity(any(), any());

	}

	@Test
	void testSetOfficialStatus() {
		String code = "ARG";
		String language = "Spanish";

		when(countrylanguageRepository.FindCountryByCode(code)).thenReturn(countryEntities);

		when(countrylanguageRepository.existsByLanguage(language)).thenReturn(true);

		assertDoesNotThrow(() -> countryLanguageImpl.setOfficialStatus(code, language));

		verify(countrylanguageRepository).FindCountryByCode(code);
		verify(countrylanguageRepository).existsByLanguage(language);
		verify(countrylanguageRepository).updateIsOfficialEntity(code, language);

	}

	@Test
	void testSetOfficialStatusException() {
		String code = "ARG";
		String language = "Spanish";

		when(countrylanguageRepository.FindCountryByCode(code)).thenReturn(countryEntities);

		when(countrylanguageRepository.existsByLanguage(language)).thenReturn(false);

		LanguageNotFoundException exception = assertThrows(LanguageNotFoundException.class,
				() -> countryLanguageImpl.setOfficialStatus(code, language));

		assertEquals(language + " not found.", exception.getMessage());

		verify(countrylanguageRepository).FindCountryByCode(code);
		verify(countrylanguageRepository).existsByLanguage(language);
		verify(countrylanguageRepository, never()).updateIsOfficialEntity(any(), any());

	}
}
