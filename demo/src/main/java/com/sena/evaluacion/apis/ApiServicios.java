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

import com.sena.evaluacion.model.Servicio;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IServicioService;
import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/apiServicio")
public class ApiServicios {

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping
	public List<Servicio> getAllProducts() {
		return servicioService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servicio> getServiceById(@PathVariable Integer id){
		Optional<Servicio> servicio = servicioService.get(id);
		return servicio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Servicio> createService(@RequestBody Servicio servicio) {
				
		Servicio sp = servicioService.save(servicio);
		return ResponseEntity.status(HttpStatus.CREATED).body(sp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Servicio> updateServices(@PathVariable Integer id, @RequestBody Servicio servicio){
		
		Optional<Servicio> s = servicioService.get(id);
		if (!s.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Servicio ser = s.get();
		ser.setNombre(servicio.getNombre());
		ser.setPrecio(servicio.getPrecio());
		ser.setDescripcion(servicio.getDescripcion());
		ser.setDuracion(servicio.getDuracion());

		
		servicioService.update(ser);
		
		return  ResponseEntity.ok(ser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteServices(@PathVariable Integer id) {
		Optional<Servicio> p = servicioService.get(id);
		if (!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Servicio prod = p.get();
		
		servicioService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
