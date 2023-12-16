package com.crazyworld.in.dao;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crazyworld.in.dao.entity.CountryEntity;
import com.crazyworld.in.dao.entity.CountryLanguageEntity;
import com.crazyworld.in.util.IsOfficial;

import jakarta.transaction.Transactional;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguageEntity, String> {

	List<CountryLanguageEntity> findByCountryEntityRegion(String region);

	List<CountryLanguageEntity> findByCountryEntity(CountryEntity countryEntity);

	@Query("select c from CountryEntity c where c.code=:code")
	List<CountryEntity> FindCountryByCode(@Param("code") String code);

	List<CountryLanguageEntity> findByCountryEntityAndIsOfficialType(CountryEntity countryEntity,
			IsOfficial isOfficial);

	@Query("SELECT cle FROM CountryLanguageEntity cle WHERE cle.isOfficialType = CAST(:officialType AS com.crazyworld.in.util.IsOfficial)")
	List<CountryLanguageEntity> findByIsOfficialType(@Param("officialType") IsOfficial IsOfficial);

	@Query("SELECT cle FROM CountryLanguageEntity cle WHERE cle.isOfficialType = 'F' and cle.countryEntity=?1")
	List<CountryLanguageEntity> getUnOfficialCountries(CountryEntity countryEntity);

	@Query("SELECT cle FROM CountryLanguageEntity cle WHERE (cle.countryEntity, cle.percentage) IN (SELECT cle2.countryEntity, MAX(cle2.percentage) FROM CountryLanguageEntity cle2 GROUP BY cle2.countryEntity)")
	List<CountryLanguageEntity> findMaxSpokenLanguageEntitiesInEachCountry();

	@Query("SELECT cle FROM CountryLanguageEntity cle WHERE (cle.percentage) = (SELECT MAX(cle2.percentage) FROM CountryLanguageEntity cle2 where cle2.countryEntity=:countryEntity)")
	List<CountryLanguageEntity> findMaxSpokenLanguageEntities(@Param("countryEntity") CountryEntity countryEntity);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update CountryLanguageEntity cle set cle.percentage=:percentage where cle.language=:language")
	int updateLanguageEntity(@Param("language") String language, @Param("percentage") BigDecimal percentage);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE CountryLanguageEntity cle SET cle.isOfficialType = 'T' WHERE cle.language = :language AND cle.countryEntity.code = :countryCode")
	int updateIsOfficialEntity(@Param("countryCode") String countryCode, @Param("language") String language);

	@Query("select count(cle) > 0 from CountryLanguageEntity cle where cle.language = :language")
	boolean existsByLanguage(@Param("language") String language);

}
