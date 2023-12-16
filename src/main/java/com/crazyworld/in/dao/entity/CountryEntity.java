package com.crazyworld.in.dao.entity;

import java.math.BigDecimal;
import java.util.List;

import com.crazyworld.in.util.Continent;
import com.crazyworld.in.util.IsOfficial;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "country")
public class CountryEntity {

	@Id
	@Column(name = "code", columnDefinition = "VARCHAR(255)")
	private String code;
  
    @Column(name="cname")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "continent_type", columnDefinition = "VARCHAR(255)")
    private Continent continentType;
    
    @Column(name="region")
    private String region;

    @Column(name="surface_area")
    private BigDecimal surfaceArea;

    @Column(name="indep_year")
    private short indepYear;

    @Column(name="population")
    private Integer population;
    
    @Column(name="life_expectancy")
    private BigDecimal lifeExpectancy;

    @Column(name="gnp")
    private double gnp;
    
    @Column(name="gnp_old")
    private BigDecimal gnpOld;

    @Column(name="local_name")
    private String localName;
    
    @Column(name="government_form")
    private String governmentForm;
    
    @Column(name="head_of_state")
    private String headOfState;

    @Column(name="capital")
    private Integer capital;

    @Column(name="code2")
    private String code2;
    
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "history", columnDefinition = "TEXT")
    private String history;

    @Column(name = "geography", columnDefinition = "TEXT")
    private String geography;

    @Column(name = "traditions", columnDefinition = "TEXT")
    private String traditions;
    
    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL)
    private List<CountryLanguageEntity> languages;
    
    
    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL)
    private List<CityEntity> cities;

}
