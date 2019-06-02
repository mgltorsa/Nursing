package com.nursing.client.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Supply
 *
 */
@Data
@RequiredArgsConstructor
public class Supply {

	
	@NonNull
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

	
	public Supply() {
		super();
	}

      
	public String getObservations() {
		return observations;
	}


	public void setObservations(String observations) {
		this.observations = observations;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
   
}
