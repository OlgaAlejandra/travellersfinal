package pe.edu.upc.ejemplo.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.ejemplo.entities.AccommodationType;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationTypeService;

@Controller
@RequestMapping("/accommodationtypes")
public class AccommodationTypeController {

	@Autowired
	private IAccommodationTypeService atService;
	
	@GetMapping("/new")
	public String newAccommodationType(Model model) {
		model.addAttribute("at", new AccommodationType());
		return "accommodationtype/frmRegistro";
	}
	
	@PostMapping("/save")
	public String saveAccommodationType(@Valid AccommodationType acc, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"accommodationtype/frmRegistro";
		}else {
			atService.insert(acc);
			return"redirect:/accommodationtypes/list";
		}
	}
	
	@GetMapping("/list")
	public String listAccommodationType(Model model) {
		try {
			model.addAttribute("listaAccommodationTypes", atService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/accommodationtype/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteAccommodationType(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				atService.delete(id);
				model.put("listaAccommodationTypes", atService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		
		return "/accommodationtype/frmLista";
	}

	@RequestMapping("/goupdate/{id}")
	public String goUpdateAccommodationType(@PathVariable int id, Model model) {
		Optional<AccommodationType> objAcc = atService.listId(id);
		
		model.addAttribute("atype",objAcc.get());
		
		return "accommodationtype/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateAccommodationType(AccommodationType accommodationType) {
		atService.update(accommodationType);
		
		return "redirect:/accommodationtypes/list";
	}
}
