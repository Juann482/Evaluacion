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

import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class ApiUsuario {

	@Autowired
	private IUsuarioService usuarioService;

	// ===============================================================

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
		Optional<Usuario> usuario = usuarioService.get(id);
		return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Endpoint - Crear usuario
	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {

		Usuario saved = usuarioService.save(usuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	// Endspoint - Actualizar usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {

		Optional<Usuario> ui = usuarioService.get(id);
		if (!ui.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Usuario u = ui.get();
		u.setNombre(usuario.getNombre());
		u.setEmail(usuario.getEmail());
		u.setPassword(usuario.getPassword());
		u.setTelefono(usuario.getTelefono());

		usuarioService.update(u);

		return ResponseEntity.ok(u);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {

		Optional<Usuario> ud = usuarioService.get(id);
		if (!ud.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}

}
