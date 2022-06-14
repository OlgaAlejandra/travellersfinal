package pe.edu.upc.ejemplo.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.ejemplo.entities.Restaurant;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.IRestaurantService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;


@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private IRestaurantService rService;
	@Autowired
	private IDestinationService dService;
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		Resource recurso =null;
		
		try {
			recurso=uploadFileService.load(filename);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
		
	}
	
	@GetMapping("/new")
	public String newRestaurant(Model model) {
		model.addAttribute("r", new Restaurant());
		model.addAttribute("listaDestinations", dService.list());
		return "restaurant/frmRegistro";
	}

	@PostMapping("/save")
	public String saveRestaurant (@Valid Restaurant rest , BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
					throws Exception {
		if (binRes.hasErrors()) {
			return "restaurant/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (rest.getIdRestaurant() > 0 && rest.getImage() != null && rest.getImage().length() > 0) {
					uploadFileService.delete(rest.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				rest.setImage(uniqueFilename);
			}
			rService.insert(rest);
		}

		return "redirect:/restaurants/list";
	}
	
	@GetMapping("/list")
	public String listRestaurant(Model model) {
		try {
			model.addAttribute("listaRestaurantes", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "restaurant/frmLista";
	}
	
	
	
	@RequestMapping("/delete")
	public String deleteRestaurant(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!= null && id>0) {
				rService.delete(id);
				model.put("listaRestaurantes", rService.list());
			}
		} catch (Exception e) {
			model.put("error",e.getMessage());
		}
		return "restaurant/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdaterestaurant(@PathVariable int id, Model model) {
		Optional<Restaurant> objrest = rService.listId(id);
		
	
		model.addAttribute("listaDestinations",dService.list());
		model.addAttribute("rest", objrest.get());
		return "restaurant/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updaterestaurant(@Valid Restaurant rest, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			return "restaurant/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (rest.getIdRestaurant() > 0 && rest.getImage() != null && rest.getImage().length() > 0) {
					uploadFileService.delete(rest.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				rest.setImage(uniqueFilename);
			}
			rService.update(rest);
		}
		return "redirect:/resturants/list";
	}
}
