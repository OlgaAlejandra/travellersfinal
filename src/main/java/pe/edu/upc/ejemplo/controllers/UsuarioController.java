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
	
	@RequestMapping("/delete")
	public String deleteUsuario(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				uService.delete(id);
				model.put("listaUsers", uService.list());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return"/usuario/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateUsuario(@PathVariable int id, Model model) {
		Optional<Usuario> objUsu = uService.listId(id);
		model.addAttribute("usu", objUsu.get());
		return "usuario/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateUsuario(Usuario usuario) {
		uService.update(usuario);
		return "redirect:/usuarios/list";
	}
	
}
