package com.nursing.client.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entity implementation class for Entity: Supply
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Supply {

	
	//TODO: Is generated in rest server?
	private Long consecutive;

	@NonNull
	@NotNull(message="{field.error}")
	private Medicine medicine;

	@NonNull
	@Min(value = 1, message = "{quantity.error}" )
	private Integer quantity;

	@NonNull
	@NotNull(message="{field.error}")
	private Patient patient;

	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	@Past
	@NotNull(message="{date.error}")
	private LocalDate date;

	private String observations;

	@NonNull
	@NotBlank(message = "{field.error}")
	private String pathology;
	
	private UrgencyAttention urgencyAttention;
}
