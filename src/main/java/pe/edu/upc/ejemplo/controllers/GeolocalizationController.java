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

import pe.edu.upc.ejemplo.entities.Geolocalization;
import pe.edu.upc.ejemplo.serviceinterface.IGeolocalizationService;

@Controller
@RequestMapping("/geolocalizations")
public class GeolocalizationController {

	@Autowired
	private IGeolocalizationService gService;
	
	@GetMapping("/new")
	public String newGeolocalization(Model model) {
		model.addAttribute("g", new Geolocalization());
		return "geolocalizacion/frmRegistro";
	}
	
	
	@PostMapping("/save")
	public String saveGeolocalization(@Valid Geolocalization geo, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return "geolocalizacion/frmRegistro";
		}else {
			gService.insert(geo);
			return "redirect:/geolocalizations/new";
		}
		
	}
	
	@GetMapping("/list")
	public String listGeolocalization(Model model) {
		try {
			model.addAttribute("listaGeolocalizaciones", gService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/geolocalizacion/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteGeolocalization(Map<String, Object> model,@RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				gService.delete(id);
				model.put("listaGeolocalizaciones", gService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/geolocalizacion/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateGeolocalization(@PathVariable int id, Model model) {
		Optional<Geolocalization> objGeo = gService.listId(id);
		model.addAttribute("geo", objGeo.get());
		return "/geolocalizacion/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateGeolocalization(Geolocalization geolocalization) {
		gService.update(geolocalization);
		return "redirect:/geolocalizations/list";
	}
}