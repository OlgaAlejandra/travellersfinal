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

import pe.edu.upc.ejemplo.entities.ReviewAtraccion;
import pe.edu.upc.ejemplo.entities.ReviewTour;
import pe.edu.upc.ejemplo.serviceinterface.IAtraccionService;
import pe.edu.upc.ejemplo.serviceinterface.IReviewAtraccionService;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Controller
@RequestMapping("/reviewatraccions")
public class ReviewAtraccionController {
	@Autowired
	private IReviewAtraccionService reService;
	@Autowired
	private IAtraccionService acService;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("re", new ReviewTour());
		model.addAttribute("listaAtraciones",acService.list());
		model.addAttribute("listaUsuarios",userService.listar());
		return"review/frmRegistroAtracion";
	}
	
	@PostMapping("/save")
	public String saveReview(@Valid ReviewAtraccion rev, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"review/frmRegistroAtracion";
		}else {
			reService.insert(rev);
			return"redirect:/reviewatraccions/list";
		}
	}
	
	@GetMapping("/list")
	public String listReview(Model model) {
		try {
			model.addAttribute("listaReviewsA", reService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/review/frmListaAtraccion";
	}
	
	@RequestMapping("/delete")
	public String deleteReview(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("listaReviewsA", reService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/review/frmListaAtraccion";
	}
	@RequestMapping("/goupdate/{id}")
	public String goUpdateReview(@PathVariable int id, Model model) {
		Optional<ReviewAtraccion> objRev = reService.listId(id);
		model.addAttribute("listaUsuarios",userService.listar());
		model.addAttribute("listaAtraciones",acService.list());
		model.addAttribute("revi",objRev.get());
		return "review/frmActualizarAtraccion";
	}
	@PostMapping("/update")
	public String updateReview(ReviewAtraccion review) {
		reService.update(review);
		return"redirect:/reviewatraccions/list";
	}
	
	
	@RequestMapping("/reportReviewAtra")
	public String ReviewAtraccionmayor(Map<String, Object> model) {
		model.put("listaReviewAtraccionmayor", reService.ReviewAtraccionmayor());
		return "reviewatraccions/reporte";
	}
}
