package com.nursing.client.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;
import com.nursing.client.model.Supply;
import com.nursing.client.model.UrgencyAttention;
import com.nursing.client.view.ViewConsultByDate;

/**
 * AppController
 */
@Controller
public class AppController {

	@Autowired
	private Delegate delegate;

	@GetMapping(value = "/test-medicines", produces = "application/json")
	public String getMedicines() {
		List<Medicine> ms = delegate.getAll(Medicine.class);

		for (Medicine m : ms) {
			System.out.println(m);
		}
		return ms.toString();
	}

	@GetMapping(value = "/consultByDate")
	public String testConsult(Model model, @ModelAttribute ViewConsultByDate<?> viewConsult,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "";

		String type = viewConsult.getType().toLowerCase();
		if (type != null) {

			if (viewConsult.getDate() != null) {
				consult(viewConsult);
			}

			model.addAttribute("consult", viewConsult);

			if (type.equals("attention")) {
				mapping = "urgency/consult";
			} else if (type.equals("supply")) {
				mapping = "supply/consult";
			}
		} else {
			redirectAttributes.addFlashAttribute("error", "error in consult");
		}

		return mapping;
	}

	private void consult(ViewConsultByDate<?> viewConsult) {

		consultByDate(viewConsult);

	}

	private void consultByDate(ViewConsultByDate viewConsult) {

		Date date = viewConsult.getDate();
		switch (viewConsult.getType().toLowerCase()) {

		// TODO:
		case "attention": {
//            List<UrgencyAttention> urgencyAttentions = urgencyService.findByDate(date);
//            viewConsult.setResults(urgencyAttentions);
			break;
		}

		case "supply": {

//            List<Supply> supplies = supplyService.findByDate(date);

//            viewConsult.setResults(supplies);

			break;
		}
		}
	}

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

	@GetMapping(value = "/edit-supply")
	public String editSupply(Model model, @RequestParam(value = "supplyConsecutive") String supplyConsecutive,
			@RequestParam(value = "urgencyConsecutive", required = false) String urgencyConsecutive,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "supply/add_supply";

		// TODO:
//        Supply supply = supplyService.getSupply(supplyConsecutive);

//        urgencyService.removeSupply(urgencyConsecutive, supplyConsecutive);

//        model.addAttribute("medicines", medicineRepository.findAll());
//        model.addAttribute("supply", supply);
//        model.addAttribute("editing", "editing");
//
//        if (urgencyConsecutive != null) {
//            model.addAttribute("urgencyConsecutive", urgencyConsecutive);
//        } else {
//            model.addAttribute("patients", patientRepository.findAll());
//        }
//
		return mapping;
	}

	@GetMapping(value = "/remove-supply")
	public String removeSupply(Model model, @RequestParam(value = "supplyConsecutive") String supplyConsecutive,
			@RequestParam(value = "urgencyConsecutive", required = false) String urgencyConsecutive,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "";
		// TODO:
		if (urgencyConsecutive != null) {
//            urgencyService.removeSupply(urgencyConsecutive, supplyConsecutive);
			mapping = "redirect:/attend-patient?urgencyConsecutive=" + urgencyConsecutive;

		} else {
			redirectAttributes.addFlashAttribute("error", "invalid post to remove-supply");
			mapping = "redirect:/";
		}
		return mapping;
	}

	@GetMapping(value = "/add-supply")
	public String addSupply(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		String mapping = "supply/add_supply";

		// TODO:
		model.addAttribute("supply", new Supply());
		model.addAttribute("patients", delegate.getAll(Patient.class));
		model.addAttribute("medicines", delegate.getAll(Medicine.class));
		model.addAttribute("addressRequest", "/add-supply");
		mapping = "supply/add_supply";

		return mapping;
	}

	@PostMapping(value = "/add-supply")
    public String addSupply(Model model, @Validated @ModelAttribute(value = "supply") Supply supply,
            BindingResult bindingResult, @RequestParam(value = "action", required = false) String action) {

        String mapping = "";
        
        if(bindingResult.hasErrors()) {
//        	delegate.getAll(Patient.class)
        	model.addAttribute("supply", supply);
            model.addAttribute("patients", new ArrayList<>());
            model.addAttribute("medicines", delegate.getAll(Medicine.class));
            model.addAttribute("addressRequest", "/add-supply");
        	return "supply/add_supply";
        }else {
        	delegate.save(supply, Supply.class);
        }
        

        return mapping;
    }

	@GetMapping(value = "/attend")
	public String attendPatient(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "";

		UrgencyAttention urgency = new UrgencyAttention();

		urgency.setSupplies(new ArrayList<>());
		urgency.setForwarded(true);
		redirectAttributes.addFlashAttribute("urgency", urgency);
		mapping = "redirect:/attend-patient";

		return mapping;
	}

	@GetMapping(value = "/attend-patient")
	public String urgencyForm(Model model, @ModelAttribute(value = "urgency") UrgencyAttention urgency,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "";

		if (urgency == null || urgency.getSupplies() == null || urgency.getForwarded() == null) {
			redirectAttributes.addFlashAttribute("error", "An error has ocurred while trying to attend a patient");
			mapping = "redirect:/";
		} else {
			model.addAttribute("patients", delegate.getAll(Patient.class));
			model.addAttribute("urgency", urgency);
			mapping = "urgency/add_urgency";
		}

		return mapping;
	}

	@PostMapping("/attend-patient")
	public String createUrgency(Model model,
			@Validated @ModelAttribute(value = "urgency", binding = true) UrgencyAttention attention,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "removeValue", name = "removeValue", required = false) String removeValue) {

		String mapping = "";

		return mapping;
	}

	@GetMapping(value = "/add-supply-to-urgency")
	public String addSupplyToUrgency(Model model, @ModelAttribute(value = "urgency") UrgencyAttention urgency,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String mapping = "";

		Supply supply = new Supply();
		supply.setPatient(urgency.getPatient());
		supply.setDate(urgency.getDate());
		model.addAttribute("urgency", urgency);
		model.addAttribute("supply", supply);
		model.addAttribute("medicines", delegate.getAll(Medicine.class));
		model.addAttribute("addressRequest", "/add-supply-to-urgency");
		mapping = "supply/add_supply";

		return mapping;
	}

	@PostMapping(value = "/add-supply-to-urgency")
	public String addSupplyToUrgency(Model model, @Validated @ModelAttribute(value = "supply") Supply supply,
			BindingResult bindingResult, @ModelAttribute UrgencyAttention urgency,
			RedirectAttributes redirectAttributes, @RequestParam(value = "action", required = false) String action) {
		String mapping = "";

		if (action.toLowerCase().equals("cancel")) {
			redirectAttributes.addFlashAttribute("urgency", urgency);
			mapping = "redirect:/attend-patient";

		} else if (bindingResult.getErrorCount() > 0) {
			model.addAttribute("urgency", urgency);
			model.addAttribute("medicines", delegate.getAll(Medicine.class));
			model.addAttribute("addressRequest", "/add-supply-to-urgency");
			mapping = "supply/add_supply";
		} else {

			switch (action.toLowerCase()) {
			case "accept": {

				// REVIEW: Validate ya usa el modelo para enviar a la vista.
				if (validateSupply(model, urgency, supply)) {
					// REVIEW: Accept add required attributes to model or redirectAttributes
					mapping = acceptSupply(urgency, redirectAttributes, supply, model);
				} else {
					mapping = "supply/add_supply";
				}
			}
			}

		}

		return mapping;
	}

	private boolean validateSupply(Model model, UrgencyAttention urgency, Supply supply) {

		boolean validated = true;

		validated = validateNonExist(model, urgency, supply);
		if (validated) {
			validated = validateToSupply(model, urgency, supply);
		}

		return validated;
	}

	private boolean validateNonExist(Model model, UrgencyAttention urgency, Supply supply) {
		boolean validated = true;

		// TODO:
		validated = delegate.get(supply.getConsecutive(), Supply.class) != null;

		if (validated) {
			List<Supply> supplies = urgency.getSupplies();
			int size = supplies.size();
			for (int i = 0; i < size && validated; i++) {
				Supply auxSupply = supplies.get(i);
				if (auxSupply != null) {
					validated = !auxSupply.getConsecutive().equals(supply.getConsecutive());
				}
			}
		}

		if (!validated) {
			model.addAttribute("existError",
					"the current urgency already has an supply with consecutive" + supply.getConsecutive());
			model.addAttribute("urgency", urgency);
			model.addAttribute("medicines", delegate.getAll(Medicine.class));
			model.addAttribute("addressRequest", "/add-supply-to-urgency");
		}

		return validated;
	}

	private boolean validateToSupply(Model model, UrgencyAttention urgency, Supply supply) {
		// TODO:
		return false;

	}

	private String acceptSupply(UrgencyAttention urgency, RedirectAttributes redirectAttributes, Supply supply,
			Model model) {

		String mapping = "";
		// TODO:
//        int totalOnInventary = inventaryService.getTotalInventaryMedicine(supply.getMedicine().getConsecutive());
//        if (supply.getQuantity() > totalOnInventary) {
//            model.addAttribute("quantityError",
//                    "quantity must be equal or smaller than available quantity:" + totalOnInventary + " in inventary");
//            model.addAttribute("urgency", urgency);
//            model.addAttribute("medicines", medicineRepository.findAll());
//            model.addAttribute("addressRequest", "/add-supply-to-urgency");
//            mapping = "supply/add_supply";
//
//        } else if (supplyService.existSupply(supply.getConsecutive())) {
//
//            model.addAttribute("error",
//                    "An supply with consecutive: " + supply.getConsecutive() + " already exists, change it!");
//            model.addAttribute("urgency", urgency);
//            model.addAttribute("medicines", medicineRepository.findAll());
//            model.addAttribute("addressRequest", "/add-supply-to-urgency");
//            mapping = "supply/add_supply";
//
//        } else {
//
//            urgency.getSupplies().add(supply);
//            redirectAttributes.addFlashAttribute("urgency", urgency);
//
//            mapping = "redirect:/attend-patient";
//
//        }
		return mapping;
	}

}