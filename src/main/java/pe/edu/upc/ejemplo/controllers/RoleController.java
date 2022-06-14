package pe.edu.upc.ejemplo.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.ejemplo.entities.Role;
import pe.edu.upc.ejemplo.serviceinterface.IRoleService;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private IRoleService roService;
	@Autowired
	private IUserService userService;
	
	@GetMapping("/nuevo")
	public String newRole(Model model) {
		model.addAttribute("ro", new Role());
		model.addAttribute("listaUsuarios",userService.listar());
		return"role/frmRegistro";
	}
	
	@PostMapping("/guardar")
	public String registrarRole(@Valid Role objTel, BindingResult binRes, Model model)throws ParseException
	{
		if(binRes.hasErrors()) {
			return"role/frmRegistro";
		}else {
			roService.insertar(objTel);
			model.addAttribute("mensaje","Se guardó correctamente");
			return"redirect:/roles/listar";
		}
	}
	
	@GetMapping("/listar")
	public String listarRoles(Model model) {
		try {
			model.addAttribute("listaRoles",roService.listar());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return"/role/frmLista";
	}
	
}
