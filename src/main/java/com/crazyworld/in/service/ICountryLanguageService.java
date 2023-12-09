package com.crazyworld.in.service;

import java.math.BigDecimal;
import java.util.List;

import com.crazyworld.in.model.CountryLanguagePojo;
import com.crazyworld.in.util.IsOfficial;
import com.crazyworld.in.util.MaxSpokenInEachCountryResoponse;

public interface ICountryLanguageService {
	List<CountryLanguagePojo> getAllLanguagesByRegion(String region);
	
	List<CountryLanguagePojo> getAllCountryLanguages();

    List<CountryLanguagePojo> getCountryLanguagesByCode(String code);

    List<CountryLanguagePojo> getAllOfficialCountryLanguages();

    List<CountryLanguagePojo> getUnofficialCountryLanguagesByCode(String code);

    List<CountryLanguagePojo> getMaxSpokenLanguage();

    CountryLanguagePojo getMaxSpokenLanguageByCode(String code);

    void updatePercentage(String code, String lang, BigDecimal percentage);

    public void setOfficialStatus(String code, String lang);



}
