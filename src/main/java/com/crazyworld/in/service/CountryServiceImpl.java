package com.crazyworld.in.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crazyworld.in.dao.CityRepository;
import com.crazyworld.in.dao.CountryRepository;
import com.crazyworld.in.dao.CountryRepository.PopulationAndLifeExpectancy;
import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.exception.CountryNotFoundException;
import com.crazyworld.in.exception.GovernmentNotFoundException;

import com.crazyworld.in.exception.ValidateFieldException;

import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.model.CountryGnpPojo;
import com.crazyworld.in.model.CountryPojo;
import com.crazyworld.in.model.CountryWithCityCountDto;

import jakarta.transaction.Transactional;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@Service
public class CountryServiceImpl implements ICountryService{
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CityRepository cityRepository;

	@Override
	public List<CountryPojo> getAllCountries() {
	        List<CountryEntity> allCountries = countryRepository.findAll();

	        if (allCountries.isEmpty()) {
	        	throw new CountryNotFoundException("Country details not found");
	        }


	        List<CountryPojo> allCountriesPojo = new ArrayList<>();
	        for (CountryEntity eachCountry : allCountries) {
	            CountryPojo eachCountryPojo = new CountryPojo();
	            BeanUtils.copyProperties(eachCountry, eachCountryPojo);
	            allCountriesPojo.add(eachCountryPojo);
	        }

	        return allCountriesPojo;
	           
	}

	//fetching the country details by passing the country name
	@Override
	public CountryPojo getByCountryName(String name) {
		 CountryEntity countryEntity = countryRepository.findByName(name);
		 CountryPojo countryPojo=new CountryPojo();
		 if(countryEntity!=null) {
			 BeanUtils.copyProperties(countryEntity,countryPojo);
			 return countryPojo;
		 }
		 else {
			 throw new CountryNotFoundException("Country Details Not Found for Country : "+name);
		 }
	}
	
	

    public List<CityPojo> getCitiesByCountryName(String name) {
        List<CityEntity> country = cityRepository.findByCountryEntityName(name);
        if(country.isEmpty()) {
        	throw new CountryNotFoundException("Country Details Not Found for Country : "+name);
        }
        List<CityPojo> allCitiesPojo=new ArrayList<CityPojo>();
        for(CityEntity allCities:country) {
        	CityPojo city=new CityPojo();
        	
        	BeanUtils.copyProperties(allCities, city);
        	allCitiesPojo.add(city);
        	
        }
        return allCitiesPojo;
    }
    
    
    public long getCityCountByCountryName(String name) {
        long count= countryRepository.countCitiesByName(name);
        if(count>0) {
        	return count;
        }
        throw new CountryNotFoundException("Cities Details Not Found for Country : "+name); 
    }
    
    
    public String getPopulationAndLifeExpectancy(String countrycode) {
        try {
            PopulationAndLifeExpectancy result = countryRepository.findPopulationAndLifeExpectancyByCountryCode(countrycode);

            if (result != null) {
                String countryCode = result.getCode();
                Integer population = result.getPopulation();
                BigDecimal lifeExpectancy = result.getLifeExpectancy();

                if (population != null) {
                    return String.format("Country Code: %s, Population: %d, Life Expectancy: %s",
                            countryCode, population, lifeExpectancy);
                } else {
                    return String.format("Country Code: %s, Population: N/A, Life Expectancy: %s",
                            countryCode, lifeExpectancy);
                }
            } else {
                throw new CountryNotFoundException("Country not found with code: " + countrycode);
            }
        } catch (Exception e) {
            throw new CountryNotFoundException("Error retrieving population and life expectancy data");
        }
    }


    
    
    @Override
    public List<CountryGnpPojo> getTop10CountriesByGnp() {
        List<CountryEntity> top10Countries = countryRepository.findTop10ByGnp();
        if(top10Countries.isEmpty()) {
        	throw new CountryNotFoundException("Country Details Not Found");
        }
        return top10Countries.stream()
                .map(countryEntity -> {
                    CountryGnpPojo countryGnpPojo = new CountryGnpPojo();
                    countryGnpPojo.setGnp(countryEntity.getGnp());
                    countryGnpPojo.setName(countryEntity.getName());
                    return countryGnpPojo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryPojo> getDistinctGovernmentForms() {
        List<String> distinctGovernmentForms = countryRepository.findDistinctGovernmentForms();
        List<CountryPojo> allCountriesPojo = new ArrayList<>();
        if(distinctGovernmentForms.isEmpty()) {
        	throw new GovernmentNotFoundException("Unique Government details not Found");
        }
        for (String governmentForm : distinctGovernmentForms) {
            CountryPojo countryPojo = new CountryPojo();
            countryPojo.setGovernmentForm(governmentForm);
            allCountriesPojo.add(countryPojo);
        }

        return allCountriesPojo;
    }


    @Override
    public List<CountryPojo> getTop10PopulatedCountries() {
        List<CountryEntity> top10CountriesData = countryRepository.findTop10PopulatedCountries();

        if (top10CountriesData.isEmpty()) {
        	throw new CountryNotFoundException("Top 10 Populated Countries Details Not Found");
        }
            return top10CountriesData.stream()
                    .map(data -> {
                        CountryPojo countryPojo = new CountryPojo();
                        countryPojo.setName(data.getName());
                        countryPojo.setPopulation(data.getPopulation());
                        return countryPojo;
                    })
                    .collect(Collectors.toList());
        
    }
    
    @Transactional
    @Override
    public CountryPojo updateGnp(String name, CountryPojo updates)  {
        CountryEntity existingCountry = countryRepository.findByName(name);

        if (existingCountry != null) {
            if (updates.getGnp()!=null) {
                //BigDecimal newGnp = new BigDecimal(updates.get("gnp").toString());
                existingCountry.setGnp(updates.getGnp());
            }
=======

    @Override
    public List<CountryPojo> getTop10PopulatedCountries() {
        List<CountryEntity> top10CountriesData = countryRepository.findTop10PopulatedCountries();

        if (top10CountriesData.isEmpty()) {
        	throw new CountryNotFoundException("Top 10 Populated Countries Details Not Found");
        }
            return top10CountriesData.stream()
                    .map(data -> {
                        CountryPojo countryPojo = new CountryPojo();
                        countryPojo.setName(data.getName());
                        countryPojo.setPopulation(data.getPopulation());
                        return countryPojo;
                    })
                    .collect(Collectors.toList());
        
    }
    
    @Transactional
    @Override
    public CountryPojo updateGnp(String name, Map<String, Object> updates) {
        CountryEntity existingCountry = countryRepository.findByName(name);

        if (existingCountry != null) {
            if (updates.containsKey("gnp")) {
                Object gnpObject = updates.get("gnp");
                if (gnpObject == null) {
                    throw new ValidateFieldException("GNP must not be null");
                }
                System.out.println(gnpObject.toString());
                System.out.println();
                if (!(gnpObject instanceof BigDecimal)) {
                    throw new ValidateFieldException("GNP must be a BigDecimal");
                }

                BigDecimal newGnp = (BigDecimal) gnpObject;
                if (newGnp.compareTo(BigDecimal.ZERO) < 0) {
                    throw new ValidateFieldException("GNP must be a non-negative value");
                }

                existingCountry.setGnp(newGnp);
            }

            CountryEntity updatedCountryEntity = countryRepository.save(existingCountry);
            CountryPojo updatedCountryPojo = new CountryPojo();
            BeanUtils.copyProperties(updatedCountryEntity, updatedCountryPojo);

            return updatedCountryPojo;
        } else {
            throw new CountryNotFoundException("Country not found with name: " + name);
        }
    }

    
    
    public CountryPojo updatePopulation(String name, Map<String, Object> updates) {
        CountryEntity countryEntity = countryRepository.findByName(name);

        if (countryEntity != null) {
            if (updates.containsKey("population")) {
                Object populationObject = updates.get("population");
                if (populationObject == null) {
                    throw new ValidateFieldException("Population must not be null");
                }

                if (!(populationObject instanceof Integer)) {
                    throw new ValidateFieldException("Population must be an integer");
                }

                Integer newPopulation = (Integer) populationObject;
                if (newPopulation < 0) {
                    throw new ValidateFieldException("Population must be a non-negative value");
                }

                countryEntity.setPopulation(newPopulation);
            }

            CountryEntity updatedCountryEntity = countryRepository.save(countryEntity);

            CountryPojo updatedCountryPojo = new CountryPojo();
            BeanUtils.copyProperties(updatedCountryEntity, updatedCountryPojo);
            return updatedCountryPojo;
        } else {
            throw new CountryNotFoundException("Country not found with name " + name + " to update the population");
        }
    }


    @Transactional
    @Override
    public CountryPojo updateHeadOfState(String name, Map<String, Object> updates) {
        CountryEntity existingCountry = countryRepository.findByName(name);

        if (existingCountry != null) {
            if (updates.containsKey("headOfState")) {
                Object headOfStateObject = updates.get("headOfState");
                if (headOfStateObject == null) {
                    throw new ValidateFieldException("headofstate must not be null");
                }

                if (!(headOfStateObject instanceof String)) {
                    throw new ValidateFieldException("headofstate must be a string");
                }

                String newHeadOfState = (String) headOfStateObject;
                if (newHeadOfState.isEmpty()) {
                    throw new ValidateFieldException("headofstate must not be empty");
                }

                existingCountry.setHeadOfState(newHeadOfState);
            }


            CountryEntity updatedCountryEntity = countryRepository.save(existingCountry);
            CountryPojo updatedCountryPojo = new CountryPojo();
            BeanUtils.copyProperties(updatedCountryEntity, updatedCountryPojo);

            return updatedCountryPojo;
        } else {
            throw new CountryNotFoundException("Country not found with name: " + name);
        }
    }
    
    

    public CountryPojo updatePopulation(String name, CountryPojo updates) {
    	CountryEntity countryEntity = countryRepository.findByName(name);

       if(countryEntity!=null) {
    	   if (updates.getPopulation() != null) {
               if (updates.getPopulation() < 0) {
                   throw new ValidationException("Population must be a non-negative value service");
               }
               System.out.println(updates.getPopulation());
               countryEntity.setPopulation(updates.getPopulation());
           }

           CountryEntity updatedCountryEntity = countryRepository.save(countryEntity);

           CountryPojo updatedCountryPojo = new CountryPojo();
           BeanUtils.copyProperties(updatedCountryEntity, updatedCountryPojo);
           return updatedCountryPojo;
       }
       else {
    	   throw new CountryNotFoundException("Country not found with name " + name +" to Update the population");
       }
       
    }
    
    
//    add this also
    @Override
	public CountryPojo getCountryByCode(String code) {
		Optional<CountryEntity>  countryEntityOpt = countryRepository.findById(code);
		System.out.println(countryEntityOpt.toString());
		if(countryEntityOpt.isPresent()) {
			CountryEntity countryEntity =  countryEntityOpt.get();
			CountryPojo countryPojo=new CountryPojo();
			BeanUtils.copyProperties(countryEntity,countryPojo);
			System.out.println(countryPojo.toString());
			 return countryPojo;
		}
		return null;
	}
}

    @Override
    public List<CountryWithCityCountDto> getCountriesWithCityCount() {
        List<CountryEntity> countries = countryRepository.findAll();
        List<CountryWithCityCountDto> result = new ArrayList<>();

        for (CountryEntity country : countries) {
            long cityCount = cityRepository.countByCountryEntity(country);
           
            CountryWithCityCountDto dto = new CountryWithCityCountDto( );
            
            dto.setCountryName(country.getName());
            dto.setCityCount(cityCount);
            result.add(dto);
        }

        return result;
    }
}

	

    
    
    


