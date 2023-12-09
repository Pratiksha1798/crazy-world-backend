
package com.crazyworld.in.model;

import java.math.BigDecimal;

import com.crazyworld.in.util.IsOfficial;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguagePojo {

	@NotBlank(message = "Language cannot be blank")
	private String language;

	@NotNull(message = "isOfficial cannot be null")
	@Enumerated(EnumType.STRING)
	private IsOfficial isOfficial;

	@NotNull(message = "Percentage cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Percentage must be greater than 0")
    private BigDecimal percentage;
    
    @JsonIgnore
    private CountryPojo countryPojo;

}