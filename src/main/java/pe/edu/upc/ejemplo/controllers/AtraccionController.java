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

import pe.edu.upc.ejemplo.entities.Atraccion;

import pe.edu.upc.ejemplo.serviceinterface.IAtraccionService;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/atraccions")
public class AtraccionController {
	@Autowired
	private IAtraccionService acService;
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
	public String newAtraccion(Model model) {
		model.addAttribute("ac", new Atraccion());

		model.addAttribute("listaDestinations", dService.list());
		return "atraccion/frmRegistro";
	}

	@PostMapping("/save")
	public String saveAtraccion(@Valid Atraccion acc, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			return "atraccion/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (acc.getIdAtraccion() > 0 && acc.getImage() != null && acc.getImage().length() > 0) {
					uploadFileService.delete(acc.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				acc.setImage(uniqueFilename);
			}
			acService.insert(acc);
		}

		return "redirect:/atraccions/list";
	}

	@GetMapping("/list")
	public String listAtraccion(Model model) {
		try {
			model.addAttribute("listaAtraciones", acService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "atraccion/frmLista";
	}

	@RequestMapping("/delete")
	public String deleteAtraccion(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				acService.delete(id);
				model.put("listaAtraciones", acService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "atraccion/frmLista";
	}

	@RequestMapping("/goupdate/{id}")
	public String goUpdateAtraccion(@PathVariable int id, Model model) {
		Optional<Atraccion> objAcc = acService.listId(id);
		model.addAttribute("listaDestinations", dService.list());
		model.addAttribute("acc", objAcc.get());
		return "atraccion/frmActualizar";
	}

	@PostMapping("/update")
	public String updateAtraccion(@Valid Atraccion acc, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			return "atraccion/frmRegistro";
		} else {
			if (!foto.isEmpty()) {
				if (acc.getIdAtraccion() > 0 && acc.getImage() != null && acc.getImage().length() > 0) {
					uploadFileService.delete(acc.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				acc.setImage(uniqueFilename);
			}
			acService.update(acc);
		}

		return "redirect:/atraccions/list";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Atraccion> attraction = acService.listId(id);
		if(attraction==null) {
			flash.addFlashAttribute("error", "La Atracción no existe en la base de datos");
			return "redirect:/atraccions/list";
			}
		model.put("attraction", attraction.get());
		return "atraccion/ver";
	}
}
