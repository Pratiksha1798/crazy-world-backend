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
    


    private String district;
    
    @NotNull
    private int population;

    private CountryPojo country;  

    
}

