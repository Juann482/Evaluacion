package com.sena.evaluacion.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/apiusuario")
public class ApiUsuario {

	@Autowired
	private IUsuarioService usuarioService;
	
	
}
