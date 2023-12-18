package com.crazyworld.in.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityPojo {

    private int id;

    @NotNull
    private String name;
    


    private String district;
    
    @NotNull
    private int population;

    @JsonIgnore
    private CountryPojo country;  

    
}

