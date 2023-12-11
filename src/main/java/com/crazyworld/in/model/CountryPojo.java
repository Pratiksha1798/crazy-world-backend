package com.crazyworld.in.model;
import java.math.BigDecimal;
import java.util.List;

import com.crazyworld.in.util.Continent;


import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CountryPojo{

	 	private String code;
	 	
	 	@NotNull
	    private String name;

	 	@NotNull
	    private Continent continentType;  

	    private String region;
	    
	    
	    @NotNull
	    private BigDecimal surfaceArea;

	    private short indepYear; 
	    
	    @NotNull
	    @Digits(integer = 10, fraction = 0, message = "Population must be a positive integer")
	    private Integer population;

	    private BigDecimal lifeExpectancy;


	    @DecimalMin(value = "0.0", message = "GNP must be a non-negative value")
	    @PositiveOrZero(message = "GNP must be a non-negative value")
	    private BigDecimal gnp;

	    private BigDecimal gnpOld;

	    @NotNull
	    private String localName;

	    @NotNull
	    private String governmentForm;
	    
	    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Head of state must contain only letters and spaces")
	    private String headOfState;

	    private Integer capital;

	    @NotNull
	    private String code2;
	    
	    private List<CountryLanguagePojo> languages;
	    

	    private List<CityPojo> cities;

	
}
