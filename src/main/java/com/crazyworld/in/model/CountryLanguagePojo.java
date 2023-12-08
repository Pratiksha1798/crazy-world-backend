package com.crazyworld.in.model;

import java.math.BigDecimal;

import com.crazyworld.in.util.IsOfficial;
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
public class CountryLanguagePojo {
	

	 @NotNull
	 private String language;
	
	 @NotNull
	 private IsOfficial isOfficial;
	 
	 @NotNull
     private BigDecimal percentage;
     
 

}
