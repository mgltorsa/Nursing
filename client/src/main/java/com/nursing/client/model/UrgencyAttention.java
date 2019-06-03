package com.nursing.client.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UrgencyAttention {

	private Long consecutive;

	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	@NotNull(message = "{date.error}")
	private LocalDate date;

	@NonNull
	@NotNull(message = "{field.error}")
	private Patient patient;

	@NonNull
	@NotBlank(message = "{field.error}")
	private String generalDescription;

	@NonNull
	@NotBlank(message = "{field.error}")
	private String procedure;

	@NonNull
	private Boolean forwarded;	

	private String forwardedPlace;

	private List<Supply> supplies;
	
	
	public UrgencyAttention(Patient patient, LocalDate date ,String generalDescription,String procedure, Boolean forwarded) {
		this.patient=patient;
		this.date=date;
		this.generalDescription=generalDescription;
		this.procedure=procedure;
		this.forwarded=forwarded;
		this.supplies=new ArrayList<>();
		

	}
	

	

}
