package com.compass.uol.av_4.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compass.uol.av_4.entity.Partido;
import com.compass.uol.av_4.entity.enums.Ideologia;
import com.compass.uol.av_4.service.PartidoService;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidoService partidoService;

	@GetMapping
	public ResponseEntity<List<Partido>> filtrarIdeologia(Ideologia ideologia) {
		return ResponseEntity.ok(partidoService.filtrarIdeologia(ideologia));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Partido> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(partidoService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Partido> insert(@RequestBody @Valid Partido partido) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(partido.getId())
				.toUri();
		return ResponseEntity.created(uri).body(partidoService.insert(partido));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		partidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Partido> update(@PathVariable(value = "id") Integer id, @RequestBody @Valid Partido partido) {
		partidoService.update(id, partido);
		return ResponseEntity.ok().build();
	}
}
