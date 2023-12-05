package com.crazyworld.in.model;
import java.io.Serializable;
import java.math.BigDecimal;

import com.crazyworld.in.util.Continent;

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
//@ToString
public class CountryPojo implements Serializable{

	 	private String code;
	 	
	 	@NotNull
	    private String name;

	 	@NotNull
	    private Continent continentType;  //need to change

	    private String region;

	    @NotNull
	    private BigDecimal surfaceArea;

	    private short indepYear; //change to small int

	    @NotNull
	    private Integer population;

	    private BigDecimal lifeExpectancy;

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
