package com.crazyworld.in.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityPojo {

    private int id;

    @NotNull
    private String name;
    
    @NotNull
    private String countryCode;  // This is a foreign key to the Country entity

    private String district;
    
    @NotNull
    private int population;

    private CountryPojo country;  // a Many-to-One relationship with Country

    
}

