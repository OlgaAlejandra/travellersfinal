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

import pe.edu.upc.ejemplo.entities.Destination;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;
import pe.edu.upc.ejemplo.serviceinterface.ITypeTripService;
import pe.edu.upc.ejemplo.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

	@Autowired
	private IDestinationService dService;

	@Autowired
	private ITypeTripService tpService;

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
	public String newDestination(Model model) {
		model.addAttribute("d", new Destination());
		model.addAttribute("listaTypeTrips", tpService.list());
		return "destination/frmRegistro";
	}

	@PostMapping("/save")
	public String saveDestination(@Valid Destination des, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception{
		if (binRes.hasErrors()) {
			return "destination/frmRegistro";
		} else {
			if(!foto.isEmpty()) {
				if(des.getIdDestination()>0 && des.getImage() !=null && des.getImage().length()>0) {
					uploadFileService.delete(des.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				des.setImage(uniqueFilename);
			}
			dService.insert(des);
		}
		return "redirect:/destinations/list";
	}

	@GetMapping("/list")
	public String listDestination(Model model) {
		try {
			model.addAttribute("listaDestinations", dService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/destination/frmLista";
	}

	@RequestMapping("/delete")
	public String deleteDestination(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.delete(id);
				model.put("listaDestinations", dService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "/destination/frmLista";
	}

	@RequestMapping("/goupdate/{id}")
	public String goUpdateDestination(@PathVariable int id, Model model) {
		Optional<Destination> objDes = dService.listId(id);
		model.addAttribute("listaTypeTrips", tpService.list());
		model.addAttribute("des", objDes.get());
		return "destination/frmActualizar";
	}

	@PostMapping("/update")
	public String updateDestination(@Valid Destination des, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception{
		if (binRes.hasErrors()) {
			return "destination/frmRegistro";
		} else {
			if(!foto.isEmpty()) {
				if(des.getIdDestination()>0 && des.getImage() !=null && des.getImage().length()>0) {
					uploadFileService.delete(des.getImage());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				des.setImage(uniqueFilename);
			}
			dService.update(des);
		}
		return "redirect:/destinations/list";
	}
	
	@RequestMapping("/reportdestination")
	public String destinationAmount(Map<String, Object> model) {
		model.put("listaDestinationAmount", dService.destinationAmount());
		model.put("listaDestinationAmountRes", dService.destinationAmountRes());
		model.put("listsDestinationAmountAtr", dService.destinationAmountAtr());
		model.put("listaDestinationAmountTou", dService.destinationAmountTou());
		return "destination/reporte";
	}
	

}
