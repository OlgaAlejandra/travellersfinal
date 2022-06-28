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

import pe.edu.upc.ejemplo.entities.ReviewTour;
import pe.edu.upc.ejemplo.serviceinterface.IReviewTourService;
import pe.edu.upc.ejemplo.serviceinterface.ITourService;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Controller
@RequestMapping("/reviewtours")
public class ReviewTourController {
	@Autowired
	private IReviewTourService reService;
	@Autowired
	private ITourService tService;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("re", new ReviewTour());
		model.addAttribute("listaTours",tService.list());
		model.addAttribute("listaUsuarios",userService.listar());
		return"review/frmRegistroTour";
	}
	
	@PostMapping("/save")
	public String saveReview(@Valid ReviewTour rev, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"review/frmRegistroTour";
		}else {
			reService.insert(rev);
			return"redirect:/reviewtours/list";
		}
	}
	
	@GetMapping("/list")
	public String listReview(Model model) {
		try {
			model.addAttribute("listaReviewsT", reService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/review/frmListaTour";
	}
	
	@RequestMapping("/delete")
	public String deleteReview(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("listaReviewsT", reService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/review/frmListaTour";
	}
	@RequestMapping("/goupdate/{id}")
	public String goUpdateReview(@PathVariable int id, Model model) {
		Optional<ReviewTour> objRev = reService.listId(id);
		model.addAttribute("listaUsuarios",userService.listar());
		model.addAttribute("listaTours",tService.list());
		model.addAttribute("revi",objRev.get());
		return "review/frmActualizarTour";
	}
	@PostMapping("/update")
	public String updateReview(ReviewTour review) {
		reService.update(review);
		return"redirect:/reviewtours/list";
	}
}
