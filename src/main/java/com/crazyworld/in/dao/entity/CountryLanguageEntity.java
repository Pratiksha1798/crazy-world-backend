package com.crazyworld.in.dao.entity;

import java.math.BigDecimal;

import com.crazyworld.in.util.IsOfficial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name="Country_language")
public class CountryLanguageEntity {
	
    @Id
    @Column(name="language")
    private String language;

    
    @Enumerated(EnumType.STRING)
    @Column(name="is_official_type")
    private  IsOfficial isOfficial;
    
    
    @Column(name="percentage")
    private BigDecimal percentage;
    
    @ManyToOne
    @JoinColumn(name="countryCode")
    private CountryEntity countryEntity;



}
