package com.nursing.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nursing.client.delegate.Delegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.wrappers.InventoryWrapper;

/**
 * AppController
 */
@Controller
public class AppController {

	@Autowired
	private Delegate delegate;

	@GetMapping("/")
	public String index(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam(value = "error", required = false) String error) {
		String mapping = "";

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("userName", username);
		if (error != null) {
			model.addAttribute("error", error);
		}

		mapping = "index";

		return mapping;
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping(value = "/test-medicines", produces = "application/json")
	public String getMedicines() {
		List<Medicine> ms = delegate.getAll(Medicine.class);

		for (Medicine m : ms) {
			System.out.println(m);
		}
		return ms.toString();
	}

	@GetMapping(value = "/add-inventory")
	public String addInventory(Model model) {
		model.addAttribute("medicines", delegate.getAll(Medicine.class));
		model.addAttribute("inventory", new InventoryWrapper());
		model.addAttribute("addressRequest", "/add-inventory");
		return "inventories/add_inventory";
	}

	@PostMapping(value = "/add-inventory")
	public String postAddInventory(Model model, @ModelAttribute InventoryWrapper inventoryMedicine  ,BindingResult binding) {

		if (binding.hasErrors()) {
			model.addAttribute("medicines", delegate.getAll(Medicine.class));
			model.addAttribute("inventory", new InventoryWrapper());
			model.addAttribute("addressRequest", "/add-inventory");
			return "inventories/add_inventory";
		}

		else {			
			delegate.save(inventoryMedicine.get(delegate), InventoryMedicine.class);
			return "redirect:/";

		}

	}

	@GetMapping(value = "/inventories")
	public String getInventories(Model model) {
		List<InventoryMedicine> inventories = delegate.getAll(InventoryMedicine.class);
		model.addAttribute("inventories", inventories);
		return "inventories/consult";
	}
	
	@GetMapping(value = "/delete")
	public String deletInventory(@RequestParam Long id) {
		delegate.delete(id, InventoryMedicine.class);
		return "redirect:/inventories";
	}

}