package com.crazyworld.in.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazyworld.in.dao.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	UserEntity findByUserName(String name);

}
