package com.nursing.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Medicine
 *
 */	
@Entity
@Table(name = "t_medicine")
@NamedQuery(name = "Medicine.findAll", query = "SELECT t FROM Medicine t")
@Data
@ToString(exclude = { "inventaries", "supplies" })
@RequiredArgsConstructor
@NoArgsConstructor
public class Medicine {

	@Id
	@NonNull
	private Long consecutive;

	@NonNull
	private String name;

	@NonNull
	private String genericName;

	@NonNull
	private String laboratory;

	private String administrationType;

	@NonNull
	private String indications;

	private String contraIndications;

	@OneToMany(mappedBy = "medicine", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<InventoryMedicine> inventaries = new ArrayList<>();

	@OneToMany(mappedBy = "medicine", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Supply> supplies = new ArrayList<>();


}
