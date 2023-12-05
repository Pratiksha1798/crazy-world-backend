package com.crazyworld.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crazyworld.in.dao.CountryRepository;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.model.CountryPojo;

@Service
public class CountryServiceImpl implements ICountryService{
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public List<CountryPojo> getAllCountries() {
		List<CountryEntity> allCountries=countryRepository.findAll();    // this is my countryEntity array of objects getting from country table
		List<CountryPojo> allCountriesPojo=new ArrayList<CountryPojo>();  //  to copy country entity ,i need to create a list which holds my countrypojo
		for(CountryEntity eachCountry:allCountries) {          //i need to copy the country entity to my pojo --for that i am iterating the country entity
																// and i am copying the enity to my pojo				
			System.out.println();
			CountryPojo eachCountryPojo=new CountryPojo();
			BeanUtils.copyProperties(eachCountry, eachCountryPojo);     //here i am copying each object
			allCountriesPojo.add(eachCountryPojo);                      // that each object i am copying to my list of  pojo
		}
		return allCountriesPojo;
		
	}

	@Override
	public CountryPojo getByCountryName(String name) {
		 CountryEntity countryEntity = countryRepository.findByName(name);
		 CountryPojo countryPojo=new CountryPojo();
		 if(countryEntity!=null) {
			 BeanUtils.copyProperties(countryEntity,countryPojo);
			 return countryPojo;
		 }
		 else {
			 return null;
		 }
	}

}
