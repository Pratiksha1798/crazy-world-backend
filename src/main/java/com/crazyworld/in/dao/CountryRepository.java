package com.crazyworld.in.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.dao.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity,String> {

	 CountryEntity findByName(String name);
	 
	 @Query("SELECT COUNT(c) FROM CityEntity c WHERE c.countryEntity.name = :countryName")
	 long countCitiesByName(@Param("countryName") String countryName);
	 
	 public interface PopulationAndLifeExpectancy {
		    String getCode();
		    Integer getPopulation();
		    BigDecimal getLifeExpectancy();
		}


	 @Query("SELECT ct.code AS code, ci.population AS population, ct.lifeExpectancy AS lifeExpectancy " +
	            "FROM CityEntity ci " +
	            "JOIN CountryEntity ct ON ci.countryEntity.code = ct.code " +
	            "WHERE ct.code = :countryCode")
	 PopulationAndLifeExpectancy findPopulationAndLifeExpectancyByCountryCode(@Param("countryCode") String countryCode);
	 

	 @Query(value = "SELECT * FROM Country ORDER BY gnp DESC LIMIT 10", nativeQuery = true)
	 List<CountryEntity> findTop10ByGnp();
	 
	 @Query("SELECT cl.language FROM CountryEntity c JOIN c.languages cl WHERE c.region = :region")
	    List<String> findLanguagesByRegion(@Param("region") String region);
	 
	 @Query("SELECT DISTINCT c.governmentForm FROM CountryEntity c")
	 List<String> findDistinctGovernmentForms();
	 
	 @Query(value="SELECT * FROM Country  ORDER BY population LIMIT 10", nativeQuery=true)
	 List<CountryEntity> findTop10PopulatedCountries();
	 
	 @Query("SELECT c.name, COUNT(l.language) FROM CountryEntity c JOIN c.languages l GROUP BY c.name")
	    List<Object[]> findCountriesWithLanguageCount();
	    @Query("SELECT c FROM CityEntity c " +
	            "JOIN c.countryEntity co " +
	            "WHERE co.name = :countryName AND c.id = co.capital")
	     CityEntity findCapitalCityByCountryName(@Param("countryName") String countryName);


}		
