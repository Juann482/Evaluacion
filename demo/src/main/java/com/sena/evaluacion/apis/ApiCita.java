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

import com.sena.evaluacion.model.Cita;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.ICitaService;
import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/apiCita")
public class ApiCita {

	@Autowired
	private ICitaService citaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public List<Cita> getAllCitas(){
		return citaService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cita> getCitaById(@PathVariable Integer id){
		Optional<Cita> cita = citaService.get(id);
		return cita.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Cita> crearUsuario(@RequestBody Cita cita) {

		Usuario upa = usuarioService.findById(1)
				.orElseThrow(() -> new RuntimeException("Usuario con ID 1 no existe"));
		cita.setUsuario(upa);
		Cita ct = citaService.save(cita);

		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cita> updateCita(@PathVariable Integer id, @RequestBody Cita cita){

		Optional<Cita> c = citaService.get(id);
		if (!c.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cita ct = c.get();
		ct.setEstado(cita.getEstado());
		//ct.setUsuario(cita.getUsuario());
		ct.setProfesional(cita.getProfesional());
		
		citaService.update(ct);
		return ResponseEntity.ok(ct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCita(@PathVariable Integer id){
		
		Optional<Cita> d = citaService.get(id);
		if (!d.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		citaService.delete(id);
		return ResponseEntity.ok().build();
	}	
}
