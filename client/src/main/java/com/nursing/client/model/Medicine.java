package com.nursing.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Medicine
 *
 */
@Data
@ToString(exclude = { "inventaries", "supplies" })
@RequiredArgsConstructor
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private Long consecutive;

	@NonNull
	@NotBlank
	private String name;

	@NonNull
	@NotBlank
	private String genericName;

	@NonNull
	@NotBlank
	private String laboratory;

	private String administrationType;

	@NonNull
	@NotBlank
	private String indications;

	private String contraIndications;

	private List<InventaryMedicine> inventaries = new ArrayList<>();

	private List<Supply> supplies = new ArrayList<>();

	public Medicine() {
		super();
	}

}
