package com.nursing.client.model.wrappers;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.nursing.client.delegate.Delegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor 
public class InventoryWrapper {
	
	private Long id;
	
	@NonNull
	@NotNull
	private Long medicine;

	@NonNull
	@Min(value = 1, message = "{quantity.error}")
	private Integer availableQuantity;

	@NonNull
	@NotBlank
	private String ubication;

	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message = "{future.date.error}")
	@NotNull
	private LocalDate expirationDate;
	
	public InventoryWrapper(InventoryMedicine inventory) {
		this.id=inventory.getId();
		this.medicine=inventory.getMedicine().getConsecutive();
		availableQuantity=inventory.getAvailableQuantity();
		ubication=inventory.getUbication();
		expirationDate=inventory.getExpirationDate();
	}
	
	public InventoryMedicine get(Delegate delegate) {
		
//		Medicine medicine = new Medicine();
//		medicine.setConsecutive(this.medicine);
//		InventoryMedicine inv = new InventoryMedicine(availableQuantity, ubication, expirationDate);
//		inv.setMedicine(medicine);
		InventoryMedicine inventory = new InventoryMedicine(delegate.get(medicine, Medicine.class), availableQuantity, ubication, expirationDate);
		inventory.setId(id);
		return inventory;
	}
	

}
