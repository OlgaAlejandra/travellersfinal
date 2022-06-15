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

import pe.edu.upc.ejemplo.entities.ReviewRestaurant;
import pe.edu.upc.ejemplo.entities.ReviewTour;
import pe.edu.upc.ejemplo.serviceinterface.IRestaurantService;
import pe.edu.upc.ejemplo.serviceinterface.IReviewRestaurantService;
import pe.edu.upc.ejemplo.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/reviewrestaurants")
public class ReviewRestaurantController {
	@Autowired
	private IReviewRestaurantService reService;
	@Autowired
	private IRestaurantService rService;
	@Autowired
	private IUsuarioService uService;
	
	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("re", new ReviewTour());
		model.addAttribute("listaRestaurantes",rService.list());
		model.addAttribute("listaUsers",uService.list());
		return"review/frmRegistroRestaurant";
	}
	
	@PostMapping("/save")
	public String saveReview(@Valid ReviewRestaurant rev, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"review/frmRegistroRestaurant";
		}else {
			reService.insert(rev);
			return"redirect:/reviewrestaurants/list";
		}
	}
	
	@GetMapping("/list")
	public String listReview(Model model) {
		try {
			model.addAttribute("listaReviewsR", reService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/review/frmListaRestaurant";
	}
	
	@RequestMapping("/delete")
	public String deleteReview(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("listaReviewsR", reService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/review/frmListaRestaurant";
	}
	@RequestMapping("/goupdate/{id}")
	public String goUpdateReview(@PathVariable int id, Model model) {
		Optional<ReviewRestaurant> objRev = reService.listId(id);
		model.addAttribute("listaUsers",uService.list());
		model.addAttribute("listaRestaurantes",rService.list());
		model.addAttribute("revi",objRev.get());
		return "review/frmActualizarRestaurant";
	}
	@PostMapping("/update")
	public String updateReview(ReviewRestaurant review) {
		reService.update(review);
		return"redirect:/reviewrestaurants/list";
	}
}
