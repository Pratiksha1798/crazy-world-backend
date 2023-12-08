package com.crazyworld.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crazyworld.in.dao.CountryLanguageRepository;
import com.crazyworld.in.dao.entity.CountryLanguageEntity;
import com.crazyworld.in.exception.LanguageNotFoundException;
import com.crazyworld.in.model.CountryLanguagePojo;

@Service
public class CountryLanguageServiceImpl implements ICountryLanguageService{
	
	@Autowired
	CountryLanguageRepository countryLanguageRepository;

	@Override
	public List<CountryLanguagePojo> getAllLanguagesByRegion(String region) {
		// TODO Auto-generated method stub
		List<CountryLanguageEntity>  listOfLanguages=countryLanguageRepository.findByCountryEntityRegion(region);
		List<CountryLanguagePojo> listOfPojo=new ArrayList<>();
		if(listOfLanguages.isEmpty()) {
			throw new LanguageNotFoundException("Languages are Not Found for the given region" + region);
		}
		for(CountryLanguageEntity languagesCopy:listOfLanguages) {
			CountryLanguagePojo pojoLanguage=new CountryLanguagePojo();
			BeanUtils.copyProperties(languagesCopy, pojoLanguage);  
			listOfPojo.add(pojoLanguage);
			System.out.println(listOfPojo.size());
		}
		return listOfPojo;
		
	}

}
