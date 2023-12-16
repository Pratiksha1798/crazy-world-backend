package com.crazyworld.in.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crazyworld.in.exception.CountryNotFoundException;
import com.crazyworld.in.model.CountryGnpPojo;
import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.model.CountryPojo;
import com.crazyworld.in.model.CountryWithCityCountDto;
import com.crazyworld.in.service.CountryLanguageServiceImpl;
import com.crazyworld.in.service.CountryServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/countries")
@Validated
public class CountryController {
	
	@Autowired
	CountryServiceImpl countryServiceImpl;
	
	@Autowired
	CountryLanguageServiceImpl countryLanguageService;
	
	@GetMapping
	public ResponseEntity<List<CountryPojo>> getAllCountries(){
		List<CountryPojo> allCountries=countryServiceImpl.getAllCountries();
		return new ResponseEntity<>(allCountries, HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public CountryPojo getByName( @PathVariable String name) {
		CountryPojo countryName=countryServiceImpl.getByCountryName(name);
		return countryName;
		
	}


    @GetMapping("/{countrycode}/population")
    public ResponseEntity<String> getPopulationAndLifeExpectancy(@PathVariable String countrycode) {
        String response = countryServiceImpl.getPopulationAndLifeExpectancy(countrycode);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/toptengnp")
    public ResponseEntity<List<CountryGnpPojo>> getTop10CountriesByGnp() {
        List<CountryGnpPojo> top10Countries = countryServiceImpl.getTop10CountriesByGnp();
        return new ResponseEntity<>(top10Countries, HttpStatus.OK);
    }
    
    
    @PatchMapping("/updategnp/{name}")
    public ResponseEntity<CountryPojo> updateGnp(@PathVariable String name,  @RequestBody @Valid Map<String, Object> updates) {
         CountryPojo updatedCountry = countryServiceImpl.updateGnp(name, updates);
         return new ResponseEntity<>(updatedCountry,HttpStatus.OK);
      
    }
    
    @PatchMapping("/updatepopulation/{name}")
    public ResponseEntity<?> updatePopulation(@PathVariable String name, @RequestBody @Valid Map<String, Object> updates) {
        try {
            CountryPojo updatedCountry = countryServiceImpl.updatePopulation(name, updates);
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (CountryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/countriesWithCityCount")
    public ResponseEntity<List<CountryWithCityCountDto>> getCountriesWithCityCount() {
        List<CountryWithCityCountDto> countriesWithCityCount = countryServiceImpl.getCountriesWithCityCount();
        return new ResponseEntity<>(countriesWithCityCount, HttpStatus.OK);
    }
    
    @GetMapping("/highestlifeexpectancy")
    public ResponseEntity<CountryPojo> getCountryWithHighestLifeExpectancy() {
        CountryPojo countryWithHighestLifeExpectancy = countryServiceImpl.getCountryWithHighestLifeExpectancy();
        if (countryWithHighestLifeExpectancy != null) {
            return ResponseEntity.ok(countryWithHighestLifeExpectancy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{region}/alllanguages")
    public ResponseEntity<List<CountryLanguagePojo>> getLanguagesByRegion(@PathVariable("region") String region) {
        List<CountryLanguagePojo> languages = countryServiceImpl.getLanguagesByRegion(region);
        return ResponseEntity.ok(languages);
    }
    
    @GetMapping("/uniquegovermentforms")
    public ResponseEntity<List<CountryPojo>> getDistinctGovernmentForms() {
    	List<CountryPojo> response = countryServiceImpl.getDistinctGovernmentForms();
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/toptenpopulated")
    public ResponseEntity<List<CountryPojo>> getTop10PopulatedCountries() {
    	List<CountryPojo> response = countryServiceImpl.getTop10PopulatedCountries();
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    
    }
    
    
    @PatchMapping("/updateheadofstate/{name}")
    public ResponseEntity<CountryPojo> updateHeadOfState(@PathVariable String name, @RequestBody Map<String, Object> updates) {
        CountryPojo updatedCountry = countryServiceImpl.updateHeadOfState(name, updates);
        return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
    }
    
    @GetMapping("/languages")
    public ResponseEntity<List<Object[]>> getCountriesWithLanguageCount() {
        List<Object[]> countriesWithLanguageCount = countryServiceImpl.getCountriesWithLanguageCount();
        return new ResponseEntity<>(countriesWithLanguageCount, HttpStatus.OK);
    }

 }

    

