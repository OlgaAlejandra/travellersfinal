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


import pe.edu.upc.ejemplo.entities.BudgeTrip;

import pe.edu.upc.ejemplo.serviceinterface.IBudgetripService;
import pe.edu.upc.ejemplo.serviceinterface.ITypeTripService;


@Controller
@RequestMapping("/budgetrips")
public class BudgetripController {
	
	
	@Autowired
	private IBudgetripService bService;
	@Autowired
    private ITypeTripService tService;

	
	
	
	@GetMapping("/new")
	public String newbudgetrip(Model model) {
		model.addAttribute("b", new BudgeTrip());
		model.addAttribute("listaTypeTrips", tService.list());
		return "budgetrip/frmRegistro";
	}
	
	
	@PostMapping("/save")
	public String savebudgetrip(@Valid BudgeTrip btt, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return "budgetrip/frmRegistro";
		}else {
			bService.insert(btt);
			return "redirect:/budgetrips/new";
		}
	}
	
	@GetMapping("/list")
	public String listbudgetrip(Model model) {
		try {
			model.addAttribute("listabudgetrip", bService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "budgetrip/frmLista";
	}
	@RequestMapping("/delete")
	public String deletebudgetrip(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!= null && id>0) {
				bService.delete(id);
				model.put("listabudgetrip", bService.list());
			}
		} catch (Exception e) {
			model.put("error",e.getMessage());
		}
		return "budgetrip/frmLista";
	}
	
	
	
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdatebudgetrip(@PathVariable int id, Model model) {
		Optional<BudgeTrip> objbtt = bService.listId(id);
		
		model.addAttribute("listaTypeTrips", tService.list());
		model.addAttribute("btt", objbtt.get());
		return "budgetrip/frmActualizar";
	}

	
	@PostMapping("/update")
	public String updatebudgetrip(BudgeTrip budgetrip) {
		bService.update(budgetrip);
		return "redirect:/budgetrips/list";
	}
	
	@RequestMapping("/reportbudget")
	public String budgetAmount(Map<String, Object> model) {
		model.put("listaBudgetAmount", bService.budgetAmount());
		return "budgetrip/reporte";
	}

}
