package com.nursing.client.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class InventaryMedicine {


	private Long id;

	@NonNull
	private Medicine medicine;

	@NonNull
	@Min(value = 0, message = "{quantity.error}")
	private Integer availableQuantity;

	@NonNull
	@NotBlank
	private String ubication;

	@NonNull
	private LocalDate expirationDate;

}
