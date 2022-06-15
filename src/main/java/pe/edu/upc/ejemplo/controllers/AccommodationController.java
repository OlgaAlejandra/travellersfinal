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

import pe.edu.upc.ejemplo.entities.Accommodation;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationService;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationTypeService;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/accommodations")
public class AccommodationController {

	@Autowired
	private IAccommodationService aService;
	@Autowired
	private IAccommodationTypeService atService;
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
	public String newAccommodation(Model model) {
		model.addAttribute("a", new Accommodation());
		model.addAttribute("listaAccommodationTypes", atService.list());
		model.addAttribute("listaDestinations", dService.list());
		return "accommodation/frmRegistro";
	}

	@PostMapping("/save")
	public String saveAccommodation(@Valid Accommodation acm, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			return "accommodation/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (acm.getIdAccommodation() > 0 && acm.getImage() != null && acm.getImage().length() > 0) {
					uploadFileService.delete(acm.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				acm.setImage(uniqueFilename);
			}
			aService.insert(acm);
		}

		return "redirect:/accommodations/list";
	}
	
	@GetMapping("/list")
	public String listAccommodation(Model model) {
		try {
			model.addAttribute("listaAlojamientos", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "accommodation/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteAccommodation(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!= null && id>0) {
				aService.delete(id);
				model.put("listaAlojamientos", aService.list());
			}
		} catch (Exception e) {
			model.put("error",e.getMessage());
		}
		return "accommodation/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateAccommodation(@PathVariable int id, Model model) {
		Optional<Accommodation> objAcm = aService.listId(id);
		
		model.addAttribute("listaAccommodationTypes",atService.list());
		model.addAttribute("listaDestinations",dService.list());
		model.addAttribute("acm", objAcm.get());
		return "accommodation/frmActualizar";
	}

	@PostMapping("/update")
	public String updateAccommodation(@Valid Accommodation acm, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			return "accommodation/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (acm.getIdAccommodation() > 0 && acm.getImage() != null && acm.getImage().length() > 0) {
					uploadFileService.delete(acm.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				acm.setImage(uniqueFilename);
			}
			aService.update(acm);
		}

		return "redirect:/accommodations/list";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Accommodation> accommodation = aService.listId(id);
		if(accommodation==null) {
			flash.addFlashAttribute("error", "El Alojamiento no existe en la base de datos");
			return "redirect:/accommodations/list";
			
		}
		model.put("alojamiento", accommodation.get());
		return "accommodation/ver";	}

}
