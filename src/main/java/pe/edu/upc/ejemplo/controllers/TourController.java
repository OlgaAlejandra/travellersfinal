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

import pe.edu.upc.ejemplo.entities.Tour;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.ITourService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/tours")
public class TourController {

	@Autowired
	private ITourService tService;
	@Autowired
	private IDestinationService dService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping("/new")
	public String newTour(Model model) {
		model.addAttribute("t", new Tour());
		model.addAttribute("listaDestinations", dService.list());
		return "tour/frmRegistro";
	}

	@PostMapping("/save")
	public String saveTour(@Valid Tour to, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if(binRes.hasErrors()) {
			return "tour/frmRegistro";
		}else {
			if(!foto.isEmpty()) {
				if(to.getIdTour()>0 && to.getImage() !=null && to.getImage().length()>0) {
					uploadFileService.delete(to.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				to.setImage(uniqueFilename);
			}
			tService.insert(to);
		}
		return "redirect:/tours/list";
	}
	
	@GetMapping("/list")
	public String listTour(Model model) {
		try {
			model.addAttribute("listaTours", tService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tour/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteTour(Map<String, Object> model,@RequestParam(value = "id") Integer id) {
		try {
			if(id!= null && id>0) {
				tService.delete(id);
				model.put("listaTours", tService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "tour/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateTour(@PathVariable int id, Model model) {
		Optional<Tour> objTo = tService.listId(id);
		
		model.addAttribute("listaDestinations",dService.list());
		model.addAttribute("to", objTo.get());
		return "tour/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateTour(@Valid Tour to, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if(binRes.hasErrors()) {
			return "tour/frmRegistro";
		}else {
			if(!foto.isEmpty()) {
				if(to.getIdTour()>0 && to.getImage() !=null && to.getImage().length()>0) {
					uploadFileService.delete(to.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				to.setImage(uniqueFilename);
			}
			tService.update(to);
		}
		return "redirect:/tours/list";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Tour> tour = tService.listId(id);
		if(tour==null) {
			flash.addFlashAttribute("error", "El Tour no existe en la base de datos");
			return "redirect:/tours/list";
		}
		model.put("tour", tour.get());
		return"tour/ver";
	}
}