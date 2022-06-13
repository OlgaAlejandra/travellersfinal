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

import pe.edu.upc.ejemplo.entities.Destination;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.ITypeTripService;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

	@Autowired
	private IDestinationService dService;
	
	@Autowired
	private ITypeTripService tpService;
	
	@GetMapping("/new")
	public String newDestination(Model model) {
		model.addAttribute("d",new Destination());
		model.addAttribute("listaTypeTrips", tpService.list());
		return "destination/frmRegistro";
	}
	 
	@PostMapping("/save")
	public String saveDestination(@Valid Destination des, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return "destination/frmRegistro";
		}else {
			dService.insert(des);
			return "redirect:/destinations/list";
		}
	}
	
	@GetMapping("/list")
	public String listDestination(Model model) {
		try {
			model.addAttribute("listaDestinations", dService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return"/destination/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteDestination(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				dService.delete(id);
				model.put("listaDestinations", dService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/destination/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateDestination(@PathVariable int id, Model model){
		Optional<Destination> objDes = dService.listId(id);
		model.addAttribute("listaTypeTrips", tpService.list());
		model.addAttribute("des",objDes.get());
		return "destination/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateDestination(Destination destination) {
		dService.update(destination);
		return "redirect:/destinations/list";
	}
	
}
