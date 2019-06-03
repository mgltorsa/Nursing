package com.nursing.client.model;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entity implementation class for Entity: Medicine
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonComponent
public class Medicine {


	@NonNull
	@JsonProperty
	private Long consecutive;

	@NonNull
	@NotBlank
	@JsonProperty
	private String name;

	@NonNull
	@NotBlank
	@JsonProperty
	private String genericName;

	@NonNull
	@NotBlank
	@JsonProperty
	private String laboratory;

	@JsonProperty
	private String administrationType;

	@NonNull
	@NotBlank
	@JsonProperty
	private String indications;

	@JsonProperty
	private String contraIndications;
	
	private List<InventoryMedicine> inventaries = new ArrayList<>();

	private List<Supply> supplies = new ArrayList<>();
	



}
