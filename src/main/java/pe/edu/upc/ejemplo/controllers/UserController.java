package pe.edu.upc.ejemplo.controllers;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.ejemplo.entities.Users;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/home")
	public String irHome() {
		return "home/home";
	}
	@GetMapping("/nuevo")
	public String newUser(Model model) {
		model.addAttribute("us", new Users());
		return"user/frmRegistro";
	}
	
	@PostMapping("/guardar")
	public String registrarUser(@Valid Users objTel, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "user/frmRegistro";
		} else {
			String p = objTel.getPassword();
			String pE = passwordEncoder.encode(p);
			Users us = new Users();
			us.setUsername(objTel.getUsername());
			us.setEnabled(objTel.getEnabled());
			us.setPassword(pE);
			us.setFullName(objTel.getFullName());
			us.setEmail(objTel.getEmail());
			us.setNumPhone(objTel.getNumPhone());
			us.setNationality(objTel.getNationality());

			userService.insertar(us);
			model.addAttribute("mensaje", "Se guardó correctamente");
			// status.setComplete();
			return "redirect:/users/listar";
		}
	}
	
	@GetMapping("/listar")
	public String listarUsuarios(Model model) {
		try {
			model.addAttribute("listaUsuarios",userService.listar());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return"/user/frmLista";
	}
	
	@RequestMapping("/delete")
	public String deleteUsuario(Map<String, Object> model, @RequestParam(value = "id") Long id) {
		try {
			if (id != null && id > 0) {
				userService.delete(id);
				model.put("listaUsuarios", userService.listar());
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return"/user/frmLista";
	}
	
	@RequestMapping("/goupdate/{id}")
	public String goUpdateUsuario(@PathVariable Long id, Model model) {
		Optional<Users> objUs = userService.listId(id);
		model.addAttribute("use", objUs.get());
		return "user/frmActualizar";
	}
	
	@PostMapping("/update")
	public String updateUser(Users u) {

			userService.update(u);
			// status.setComplete();
			return "redirect:/users/listar";
		
	}


}
