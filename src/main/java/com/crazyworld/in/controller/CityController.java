package com.crazyworld.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crazyworld.in.model.CityPojo;
import com.crazyworld.in.service.CityServiceImpl;
import com.crazyworld.in.service.CountryServiceImpl;


@RestController
@RequestMapping ("/api/cities")
@CrossOrigin("http://localhost:4200")
public class CityController {
    @Autowired
    CityServiceImpl cityService;
    
	@Autowired
	CountryServiceImpl countryServiceImpl;


      	@GetMapping("/getallcities")
        public List<CityPojo> getAllCities() {
            return cityService.getAllCities();
        }
      	
    	@GetMapping("/{name}/cities")
        public ResponseEntity<List<CityPojo>> getCitiesByCountryName(@PathVariable String name) {
            List<CityPojo> cities = countryServiceImpl.getCitiesByCountryName(name);
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
        @GetMapping("/firsttencities/{startChar}")
        public List<String> getFirstTenCitiesStartingWithChar(@PathVariable char startChar) {
            List<String> city = cityService.getFirstTenCitiesByStartingCharacter(startChar);
			return cityService.getFirstTenCitiesByStartingCharacter(startChar);
        }
        
        @GetMapping("/maxpopulated")
        public ResponseEntity<CityPojo> getCityWithMaxPopulation() {
            CityPojo cityWithMaxPopulation = cityService.getCityWithMaxPopulation();
                return ResponseEntity.ok(cityWithMaxPopulation);
            } 
        
        
        
        
        
        @GetMapping("/toptenpopulatedcities")
        public ResponseEntity<List<CityPojo>> getTopTenPopulatedCities() {
            List<CityPojo> topTenPopulatedCities = cityService.getTopTenPopulatedCities();
            
            if (!topTenPopulatedCities.isEmpty()) {
                return ResponseEntity.ok(topTenPopulatedCities);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
        
    @GetMapping("/districts/{countrycode}")
    public ResponseEntity<List<CityPojo>> getAllCitiesAndDistrictsForCountry(@PathVariable String countrycode) {
    List<CityPojo> citiesAndDistricts = cityService.getAllCitiesAndDistrictsForCountry(countrycode);
    
    if (!citiesAndDistricts.isEmpty()) {
        return ResponseEntity.ok(citiesAndDistricts);
    } else {
        return ResponseEntity.noContent().build();
    }
}
    
    
    
    @GetMapping("/districts")
    public ResponseEntity<List<CityPojo>> getAllUniqueDistrictsAsCityPojo() {
        List<CityPojo> uniqueDistrictsAsCityPojo = cityService.findAllUniqueDistrictsAsCityPojo();
        
        if (!uniqueDistrictsAsCityPojo.isEmpty()) {
            return ResponseEntity.ok(uniqueDistrictsAsCityPojo);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/avgpopulation/{districtname}")
    public ResponseEntity<List<CityPojo>> getAveragePopulationByDistrict(@PathVariable String districtname) {
        List<CityPojo> cityPojoList = cityService.getAveragePopulationByDistrict(districtname);
        
        if (!cityPojoList.isEmpty()) {
            return ResponseEntity.ok(cityPojoList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    
    
    @PatchMapping("/{nm}/updatedistrict")
    public ResponseEntity<List<CityPojo>> updateDistrictForCity(@PathVariable("nm") String cityName, @RequestParam("newdistrict") String newDistrict) {
        List<CityPojo> updatedCities = cityService.updateDistrictForCity(cityName, newDistrict);

        if (!updatedCities.isEmpty()) {
            return ResponseEntity.ok(updatedCities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/updatepopulation/{name}")
    public ResponseEntity<List<CityPojo>> updatePopulationForCity(@PathVariable String name, @RequestParam int newpopulation) {
        List<CityPojo> updatedCities = cityService.updatePopulationForCity(name, newpopulation);

        if (!updatedCities.isEmpty()) {
            return ResponseEntity.ok(updatedCities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



