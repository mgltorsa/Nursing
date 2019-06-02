package com.nursing.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString(exclude = "attentions")
public class Patient {

	@NonNull
	@NotBlank(message = "{field.error}")
	private String document;

	@NonNull
	@NotBlank(message = "{field.error}")
	private String names;

	@NonNull
	@NotBlank(message = "{field.error}")
	private String lastnames;

	private String academicProgram;

	private String academicDependency;

	private String state;
	
	
	private List<UrgencyAttention> attentions;

	private List<Supply> supplies;
	
	
	public Patient(String document, String names, String lastnames) {
		this.document=document;
		this.names=names;
		this.lastnames=lastnames;
		this.state="ACTIVO";
		this.attentions=new ArrayList<>();
		this.supplies=new ArrayList<>();
	}
	

}
