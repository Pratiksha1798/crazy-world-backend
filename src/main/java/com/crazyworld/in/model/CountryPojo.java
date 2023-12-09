package com.crazyworld.in.model;
import java.math.BigDecimal;

import com.crazyworld.in.util.Continent;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
@ToString
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
	    @NotNull
	    private BigDecimal gnp;

	    private BigDecimal gnpOld;

	    @NotNull
	    private String localName;

	    @NotNull
	    private String governmentForm;

	    private String headOfState;

	    private Integer capital;

	    @NotNull
	    private String code2;
	    
	    
	
}
