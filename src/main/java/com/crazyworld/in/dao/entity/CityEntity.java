package com.crazyworld.in.dao.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name="city")
public class CityEntity {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="district")
	private String district;

	@Column(name="population")
	private int population;
	
	@ManyToOne
	@JoinColumn(name = "country_code")
	private CountryEntity countryEntity; //here the countrycode column has to insert into the city table

}
