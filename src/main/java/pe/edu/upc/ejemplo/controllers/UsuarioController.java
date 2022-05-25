package pe.edu.upc.ejemplo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.ejemplo.entities.Usuario;
import pe.edu.upc.ejemplo.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService uService;
	
	@GetMapping("/new")
	public String newUsuario(Model model) {
		model.addAttribute("u", new Usuario());
		return "usuario/frmRegistro";
	}
	
	@PostMapping("/save")
	public String saveUsuario(@Valid Usuario usu, BindingResult binRes, Model model) {
		if(binRes.hasErrors()) {
			return"usuario/frmRegistro";
		}else {
			uService.insert(usu);
			return "redirect:/usuarios/new";
		}
	}
	
	@GetMapping("/list")
	public String listUsuario(Model model) {
		try {
			model.addAttribute("listaUsers", uService.list());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return"/usuario/frmLista";
	}
}
