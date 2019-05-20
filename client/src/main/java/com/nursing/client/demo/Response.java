package com.nursing.client.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nursing.client.model.Medicine;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Response{
	@JsonProperty(value="medicines")
	private List<Medicine> medicines;
	
	public List<Medicine> getMedicines() {
		return medicines;
	}
}
