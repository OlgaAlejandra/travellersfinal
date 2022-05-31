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

import pe.edu.upc.ejemplo.entities.TypeTrip;
import pe.edu.upc.ejemplo.serviceinterface.ITypeTripService;

@Controller
@RequestMapping("/typetrips")
public class TypeTripController {

	@Autowired
	private ITypeTripService tpService;
	
	@GetMapping("/new")
	public String newTypeTrip(Model model) {
		model.addAttribute("tp", new TypeTrip());
		return "typetrip/frmRegistro";
	}
	
	@PostMapping("/save")
	public String saveTypeTrip(@Valid TypeTrip typ, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return "typetrip/frmRegistro";
		}else {
			tpService.insert(typ);
			return "redirect:/typetrips/new";
		}
	}
	
	@GetMapping("/list")
	public String listTypeTrips(Model model) {
		try {
			model.addAttribute("listaTypeTrips", tpService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/typetrip/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteTypeTrip(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				tpService.delete(id);
				model.put("listaTypeTrips", tpService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/typetrip/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateTypeTrip(@PathVariable int id, Model model) {
		Optional<TypeTrip> objTyp = tpService.listId(id);
		model.addAttribute("type",objTyp.get());
		return "typetrip/frmActualizar";
	}
	@PostMapping("/update")
	public String updateTypeTrip(TypeTrip typeTrip) {
		tpService.update(typeTrip);
		return "redirect:/typetrips/list";
	}
}
