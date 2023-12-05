package com.crazyworld.in.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crazyworld.in.dao.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity,String> {

	 CountryEntity findByName(String name);
}
