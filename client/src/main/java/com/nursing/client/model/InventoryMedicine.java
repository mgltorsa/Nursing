package com.nursing.client.model;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class InventoryMedicine {


	private Long id;
	
	private Medicine medicine;

	@NonNull
	@Min(value = 1, message = "{quantity.error}")
	private Integer availableQuantity;

	@NonNull
	@NotBlank
	private String ubication;

	@NonNull
	@Future(message = "{futuredate.error}")
	@NotNull(message = "{date.error}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate;

}
