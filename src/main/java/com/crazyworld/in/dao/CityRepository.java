package com.crazyworld.in.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.crazyworld.in.dao.entity.CityEntity;
import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.model.CityPojo;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {

	@Query(value = "SELECT name FROM City WHERE name LIKE CONCAT(:startChar, '%') LIMIT 10", nativeQuery = true)
	List<String> findFirstTenCitiesByStartingCharacter(@Param("startChar") String startChar);

	@Query(value = "SELECT * FROM City ORDER BY population DESC LIMIT 1", nativeQuery = true)
	CityEntity findCityWithMaxPopulation();

	@Query(value = "SELECT * FROM City ORDER BY population DESC LIMIT 10", nativeQuery = true)
	List<CityEntity> findTop10PopulatedCities();

	@Query(value = "SELECT * FROM City WHERE country_code = :countryCode", nativeQuery = true)
	List<CityEntity> getCitiesAndDistrictsfromCountryCode(@Param("countryCode") String countryCode);

	@Query(value = "SELECT DISTINCT district FROM City", nativeQuery = true)
	List<String> findAllUniqueDistricts();

	@Query(value = "SELECT AVG(population) FROM City WHERE district = :districtName GROUP BY district", nativeQuery = true)
	Integer findAveragePopulationByDistrict(@Param("districtName") String districtName);

	@Query("SELECT c FROM CityEntity c WHERE c.name = :name")
	List<CityEntity> findAllByName(@Param("name") String name);

	CityEntity findByName(String cityName);

	List<CityEntity> findByCountryEntityName(String countryName);

	long countByCountryEntity(CountryEntity country);
	

}
