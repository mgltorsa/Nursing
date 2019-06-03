package com.nursing.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nursing.client.delegate.Delegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;
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
	public String postAddInventory(Model model, @Validated @ModelAttribute InventoryWrapper wrapper,
			BindingResult binding, @RequestParam(name = "action") String action) {

		if (action.toLowerCase().equals("cancel")) {
			return "redirect:/";
		} else {

			if (binding.hasErrors()) {
				model.addAttribute("medicines", delegate.getAll(Medicine.class));
				model.addAttribute("inventory", wrapper);
				model.addAttribute("addressRequest", "/add-inventory");
				return "inventories/add_inventory";
			}

			else {
				delegate.save(wrapper.get(delegate), InventoryMedicine.class);
				return "redirect:/";

			}
		}

	}

	@GetMapping(value = "/edit-inventory")
	public String editInventory(Model model, @RequestParam Long id) {
		InventoryMedicine inventory = delegate.get(id, InventoryMedicine.class);
		model.addAttribute("medicines", delegate.getAll(Medicine.class));
		model.addAttribute("inventory", new InventoryWrapper(inventory));
		model.addAttribute("addressRequest", "/edit-inventory");
		return "inventories/add_inventory";
	}

	@PostMapping(value = "/edit-inventory")
	public String postEditInventory(Model model, @Validated @ModelAttribute InventoryWrapper wrapper, BindingResult binding,
			@RequestParam(name = "action") String action) {
		// TODO: process POST request

		if (action.toLowerCase().equals("cancel")) {
			return "redirect:/inventories";
		} else {

			if (binding.getErrorCount()>0) {
				model.addAttribute("medicines", delegate.getAll(Medicine.class));
				model.addAttribute("inventory", wrapper);
				model.addAttribute("addressRequest", "/add-inventory");
				return "inventories/add_inventory";
			}

			else {
				delegate.update(wrapper.get(delegate), InventoryMedicine.class);
				return "redirect:/inventories";

			}
		}
	}

	@GetMapping(value = "/add-patient")
	public String addPatient(@ModelAttribute Patient patient, RedirectAttributes redirect) {

		delegate.save(patient, Patient.class);

		redirect.addFlashAttribute("error", "added patient");

		return "redirect:/";
	}

	@GetMapping(value = "/update-patient")
	public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes redirect) {
		delegate.update(patient, Patient.class);
		redirect.addFlashAttribute("error", "updated patient");

		return "redirect:/";
	}

	@GetMapping(value = "/delete-patient")
	public String deletePatient(@RequestParam String id, RedirectAttributes redirect) {
		delegate.delete(id, Patient.class);
		redirect.addFlashAttribute("error", "deleted patient");

		return "redirect:/";
	}

	@GetMapping(value = "/add-medicine")
	public String addMedicine(@ModelAttribute Medicine medicine, RedirectAttributes redirect) {

		delegate.update(medicine, Medicine.class);
		redirect.addFlashAttribute("error", "added medicine");

		return "redirect:/";
	}

	@GetMapping(value = "/update-medicine")
	public String updateMedicine(@ModelAttribute Medicine medicine, RedirectAttributes redirect) {

		delegate.update(medicine, Medicine.class);
		redirect.addFlashAttribute("error", "updated medicine");

		return "redirect:/";
	}

	@GetMapping(value = "/delete-medicine")
	public String deleteMedicine(@RequestParam String consecutive, RedirectAttributes redirect) {
		delegate.delete(consecutive, Medicine.class);
		redirect.addFlashAttribute("error", "deleted medicine");

		return "redirect:/";
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