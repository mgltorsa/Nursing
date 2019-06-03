package com.nursing.rest.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "t_inventory")
@NamedQuery(name="InventoryMedicine.findAll", query="SELECT t FROM InventoryMedicine t")
public class InventoryMedicine {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Inventory_IDInventory_Generator")
	@SequenceGenerator(name = "Inventory_IDInventory_Generator", allocationSize = 1)
	private Long id;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "medicine_consecutive", referencedColumnName = "consecutive")
	private Medicine medicine;

	@NonNull
	private Integer availableQuantity;

	@NonNull
	private String ubication;

	@NonNull
	private LocalDate expirationDate;

}
