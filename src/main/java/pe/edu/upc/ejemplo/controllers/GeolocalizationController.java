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

import pe.edu.upc.ejemplo.entities.Geolocalization;
import pe.edu.upc.ejemplo.serviceinterface.IGeolocalizationService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/geolocalizations")
public class GeolocalizationController {

	@Autowired
	private IGeolocalizationService gService;
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
	public String newGeolocalization(Model model) {
		model.addAttribute("g", new Geolocalization());
		return "geolocalizacion/frmRegistro";
	}

	@PostMapping("/save")
	public String saveGeolocalization(@Valid Geolocalization geo, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if(binRes.hasErrors()) {
			return "geolocalizacion/frmRegistro";
		}else {
			if(!foto.isEmpty()) {
				if(geo.getIdGeolocalization()> 0 && geo.getImage()!=null && geo.getImage().length()>0) {
					uploadFileService.delete(geo.getImage());
				}
				String uniqueFilename= null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				}catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				geo.setImage(uniqueFilename);
			}
			gService.insert(geo);
		}
		return "redirect:/geolocalizations/list";
	}

	@GetMapping("/list")
	public String listGeolocalization(Model model) {
		try {
			model.addAttribute("listaGeolocalizaciones", gService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/geolocalizacion/frmLista";
	}

	@RequestMapping("/delete")
	public String deleteGeolocalization(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
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
	public String updateGeolocalization(@Valid Geolocalization geo, BindingResult binRes, Model model,
	@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception  {
		if(binRes.hasErrors()) {
			return "geolocalizacion/frmRegistro";
		}else {
			if(!foto.isEmpty()) {
				if(geo.getIdGeolocalization()> 0 && geo.getImage()!=null && geo.getImage().length()>0) {
					uploadFileService.delete(geo.getImage());
				}
				String uniqueFilename= null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				}catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				geo.setImage(uniqueFilename);
			}
			gService.update(geo);
		}
		return "redirect:/geolocalizations/list";
	}
}