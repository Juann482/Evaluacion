package com.sena.evaluacion.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.evaluacion.model.Profesional;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IProfesionalService;
import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/apiProfesional")
public class ApiProfesional {

	@Autowired
	private IProfesionalService profesionalService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public List<Profesional> getAllProfesionals(){
		return profesionalService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profesional> getProfesionalById(@PathVariable Integer id){
		Optional<Profesional> prof = profesionalService.get(id);
		return prof.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Profesional> createProfesion(@RequestBody Profesional profesional){
		
		Usuario p = usuarioService.findById(1).get();
		profesional.setUsuario(p);
		Profesional pr = profesionalService.save(profesional);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pr);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Profesional> updateProfesional(@PathVariable Integer id, @RequestBody Profesional profesional){
		
		Optional<Profesional> pf = profesionalService.get(id);
		if (!pf.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Profesional auch = pf.get();
		auch.setEspecialidad(profesional.getEspecialidad());
		auch.setCita(profesional.getCita());
		
		profesionalService.update(auch);
		return ResponseEntity.ok(auch);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProfes(@PathVariable Integer id){
		
		Optional<Profesional> uwu = profesionalService.get(id);
		if (!uwu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		profesionalService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}
