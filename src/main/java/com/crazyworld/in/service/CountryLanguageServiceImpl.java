package com.crazyworld.in.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crazyworld.in.dao.CountryLanguageRepository;

import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.dao.entity.CountryLanguageEntity;

import com.crazyworld.in.exception.DataNotFoundException;
import com.crazyworld.in.exception.LanguageNotFoundException;
import com.crazyworld.in.model.CountryLanguagePojo;

import com.crazyworld.in.util.IsOfficial;

@Service
public class CountryLanguageServiceImpl implements ICountryLanguageService {

	@Autowired
	CountryLanguageRepository countryLanguageRepository;

	@Override
	public List<CountryLanguagePojo> getAllCountryLanguages() {
		List<CountryLanguageEntity> listOfLanguages = countryLanguageRepository.findAll();
		List<CountryLanguagePojo> listOfPojo = new ArrayList<>();
		if (listOfLanguages.isEmpty()) {
			throw new DataNotFoundException("Country Language Database is empty");
		}
		for (CountryLanguageEntity languagesCopy : listOfLanguages) {
			CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
			BeanUtils.copyProperties(languagesCopy, pojoLanguage);
			languagesCopy.setCountryEntity(languagesCopy.getCountryEntity());
			pojoLanguage.setIsOfficial(languagesCopy.getIsOfficialType());
			listOfPojo.add(pojoLanguage);
		}
		return listOfPojo;
	}

	@Override
	public List<CountryLanguagePojo> getCountryLanguagesByCode(String code) {

		CountryEntity countryEntity = countryLanguageRepository.FindCountryByCode(code).get(0);

		if (countryEntity == null)
			throw new DataNotFoundException("Country not found for code " + code);

		List<CountryLanguagePojo> listOfPojo = new ArrayList<>();
		List<CountryLanguageEntity> listOfLanguages = countryLanguageRepository.findByCountryEntity(countryEntity);
		if (listOfLanguages.isEmpty()) {
			throw new LanguageNotFoundException("Languages are Not Found for the given code " + code);
		}
		for (CountryLanguageEntity languagesCopy : listOfLanguages) {
			CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
			BeanUtils.copyProperties(languagesCopy, pojoLanguage);
			languagesCopy.setCountryEntity(languagesCopy.getCountryEntity());
			pojoLanguage.setIsOfficial(languagesCopy.getIsOfficialType()); // to set isOfficial
			listOfPojo.add(pojoLanguage);
		}
		return listOfPojo;
	}

	@Override
	public List<CountryLanguagePojo> getAllOfficialCountryLanguages() {
		List<CountryLanguageEntity> listOfLanguages = countryLanguageRepository.findAll();
		List<CountryLanguagePojo> listOfPojo = new ArrayList<>();
		if (listOfLanguages.isEmpty()) {
			throw new LanguageNotFoundException("Country Language Database is empty");
		}
		for (CountryLanguageEntity languagesCopy : listOfLanguages) {
			if (languagesCopy.getIsOfficialType() == IsOfficial.T) {
				CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
				BeanUtils.copyProperties(languagesCopy, pojoLanguage);
				languagesCopy.setCountryEntity(languagesCopy.getCountryEntity());
				pojoLanguage.setIsOfficial(languagesCopy.getIsOfficialType());
				listOfPojo.add(pojoLanguage);
			}
		}
		return listOfPojo;
	}

	@Override
	public List<CountryLanguagePojo> getUnofficialCountryLanguagesByCode(String countrycode) {

		CountryEntity countryEntity = countryLanguageRepository.FindCountryByCode(countrycode).get(0);

		if (countryEntity == null)
			throw new DataNotFoundException("Country not found for code " + countrycode);

		List<CountryLanguagePojo> listOfPojo = new ArrayList<>();
		List<CountryLanguageEntity> listOfLanguages = countryLanguageRepository.getUnOfficialCountries(countryEntity);
		if (listOfLanguages.isEmpty()) {
			throw new LanguageNotFoundException("unofficial languages are not available for this code " + countrycode);
		}
		for (CountryLanguageEntity languagesCopy : listOfLanguages) {
			CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
			BeanUtils.copyProperties(languagesCopy, pojoLanguage);
			pojoLanguage.setIsOfficial(languagesCopy.getIsOfficialType());
			listOfPojo.add(pojoLanguage);
		}
		return listOfPojo;
	}

	@Override
	public List<CountryLanguagePojo> getMaxSpokenLanguage() {
		List<CountryLanguagePojo> languagePojos = new ArrayList<CountryLanguagePojo>();
		List<CountryLanguageEntity> listOfLanguages = countryLanguageRepository
				.findMaxSpokenLanguageEntitiesInEachCountry();
		if (listOfLanguages.isEmpty()) {
			throw new LanguageNotFoundException("Languages are not available");
		}
		for (CountryLanguageEntity languagesCopy : listOfLanguages) {
			CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
			BeanUtils.copyProperties(languagesCopy, pojoLanguage);
			pojoLanguage.setIsOfficial(languagesCopy.getIsOfficialType());
			languagePojos.add(pojoLanguage);
		}
		return languagePojos;

	}

	@Override
	public CountryLanguagePojo getMaxSpokenLanguageByCode(String code) {

		CountryEntity countryEntity = countryLanguageRepository.FindCountryByCode(code).get(0);

		if (countryEntity == null)
			throw new DataNotFoundException("Country not found for code " + code);

		CountryLanguageEntity countryLanguageEntity = countryLanguageRepository
				.findMaxSpokenLanguageEntities(countryEntity).get(0);
		if (countryLanguageEntity != null) {
			CountryLanguagePojo pojoLanguage = new CountryLanguagePojo();
			BeanUtils.copyProperties(countryLanguageEntity, pojoLanguage);
			pojoLanguage.setIsOfficial(countryLanguageEntity.getIsOfficialType());
			return pojoLanguage;
		}
		throw new LanguageNotFoundException("languages are not available");
	}

	@Override
	public void updatePercentage(String code, String lang, BigDecimal percentage) {

		CountryEntity countryEntity = countryLanguageRepository.FindCountryByCode(code).get(0);

		if (countryEntity == null)
			throw new DataNotFoundException("Country not found for code " + code);

		Optional<CountryLanguageEntity> optional = countryLanguageRepository.findById(lang);
		if (optional.isPresent()) {
			countryLanguageRepository.updateLanguageEntity(lang, percentage);
		} else
			throw new LanguageNotFoundException(lang + " not found.");

	}

	@Override
	public void setOfficialStatus(String code, String lang) {
		CountryEntity countryEntity = countryLanguageRepository.FindCountryByCode(code).get(0);

		if (countryEntity == null)
			throw new DataNotFoundException("Country not found for code " + code);

		if (!countryLanguageRepository.existsByLanguage(lang)) {
			throw new LanguageNotFoundException(lang + " not found.");
		}

		countryLanguageRepository.updateIsOfficialEntity(code, lang);

	}

}
