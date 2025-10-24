package com.sena.evaluacion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.evaluacion.model.Profesional;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IProfesionalService;
import com.sena.evaluacion.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IProfesionalService profesionalService;
	
	@GetMapping("/RegistroUSER")
	private String GuardarUsuario(Usuario usuario, Model model) {
		
		model.addAttribute("CrearUSER", usuarioService.findAll());
		model.addAttribute("especializacion", profesionalService.findAll());
		model.addAttribute("usuario", new Usuario());
		
		return "redirect:/usuario/RegistroUSER";
	}
	
	@PostMapping("/GuardarUSER")
	private String EnviarnewUSER(Usuario usuario) {
		
		usuarioService.save(usuario);
		LOGGER.warn("Usuarioguarado con exito; {}", usuario);
		
		return "usuario/RegistroUSER";
	}
	
	///================================
	
	@GetMapping("/RegistroESPE")
	private String  GuardarESPE(Profesional profesional, Model model) {
		
		model.addAttribute("especialidad", profesionalService.findAll());
		
		return "";
	}

}
