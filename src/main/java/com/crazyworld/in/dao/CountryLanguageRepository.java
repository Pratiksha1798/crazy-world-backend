package com.crazyworld.in.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazyworld.in.dao.entity.CountryLanguageEntity;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguageEntity,String>{

	
}
