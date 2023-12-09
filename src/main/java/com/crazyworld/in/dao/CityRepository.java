package com.crazyworld.in.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazyworld.in.dao.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity,Integer> {
	
	List<CityEntity> findByCountryEntityName(String countryName);
}
