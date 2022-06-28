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

import pe.edu.upc.ejemplo.entities.Review;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationService;
import pe.edu.upc.ejemplo.serviceinterface.IReviewService;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private IReviewService reService;
	@Autowired
	private IAccommodationService aService;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("re", new Review());
		model.addAttribute("listaAlojamientos",aService.list());
		model.addAttribute("listaUsuarios",userService.listar());
		return"review/frmRegistro";
	}
	
	@PostMapping("/save")
	public String saveReview(@Valid Review rev, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"review/frmRegistro";
		}else {
			reService.insert(rev);
			return"redirect:/reviews/list";
		}
	}
	
	@GetMapping("/list")
	public String listReview(Model model) {
		try {
			model.addAttribute("listaReviews", reService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/review/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteReview(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("listaReviews", reService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/review/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateReview(@PathVariable int id, Model model) {
		Optional<Review> objRev = reService.listId(id);
		model.addAttribute("listaAlojamientos",aService.list());
		model.addAttribute("listaUsuarios",userService.listar());
		model.addAttribute("revi",objRev.get());
		return "review/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateReview(Review review) {
		reService.update(review);
		return"redirect:/reviews/list";
	}
	
	
	
}
