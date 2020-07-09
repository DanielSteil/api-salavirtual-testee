package br.com.ottimizza.salasvirtuais.controllers;

import java.math.BigInteger;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.salasvirtuais.domain.criterias.PageCriteria;
import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;
import br.com.ottimizza.salasvirtuais.domain.responses.GenericPageableResponse;
import br.com.ottimizza.salasvirtuais.domain.responses.GenericResponse;
import br.com.ottimizza.salasvirtuais.models.sala_usuario.SalaUsuario;
import br.com.ottimizza.salasvirtuais.services.SalaService;

@RestController
@RequestMapping("/api/sala")
public class SalaController {

	@Inject
	SalaService salaService;
	
	@GetMapping
	public ResponseEntity<?> buscaPorFiltro(@Valid SalaDTO filtro,
											@Valid PageCriteria pageCriteria) throws Exception {
		return ResponseEntity.ok(new GenericPageableResponse<SalaDTO>(salaService.buscaPorFiltro(filtro, pageCriteria)));
	}
	
	@PostMapping
	public ResponseEntity<?> salvaSala(@RequestBody SalaDTO sala) throws Exception {
		return ResponseEntity.ok(salaService.salvaSala(sala));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchSala(@PathVariable("id") BigInteger id, @RequestBody SalaDTO sala) throws Exception {
		return ResponseEntity.ok(new GenericResponse<SalaDTO>(salaService.patch(id, sala)));
	}
	
	@DeleteMapping("/{salaId}")
	public ResponseEntity<?> deletaSala(@PathVariable("salaId") BigInteger id) throws Exception {
		salaService.deletaSala(id);
		return ResponseEntity.ok("{}");
	}
	
	@PostMapping("/entrar")
	public ResponseEntity<?> salvaUsuarioSala(@RequestBody SalaUsuario salauser) throws Exception {
		salaService.salvaUsuarioSala(salauser);
		return ResponseEntity.ok("{}");
	}
	
	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> deletaUsuarioSala(@PathVariable("usuarioId") BigInteger usuarioId) throws Exception {
		salaService.deletaUsuarioSala(usuarioId);
		return ResponseEntity.ok("{}");
	}
}
