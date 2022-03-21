package com.compass.uol.av_4.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import com.compass.uol.av_4.controllers.dto.AssociaPartidoAssociadoDTO;
import com.compass.uol.av_4.controllers.dto.AssociadoPartidoDTO;
import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.enums.CargoPolitico;
import com.compass.uol.av_4.service.AssociadoService;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoService associadoService;

	@GetMapping
	public ResponseEntity<List<Associado>> filtrarCargoPolitico(CargoPolitico cargoPolitico) {
		return ResponseEntity.ok(associadoService.filtrarCargoPolitico(cargoPolitico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Associado> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(associadoService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Associado> insert(@RequestBody @Valid Associado associado) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(associadoService.insert(associado));
	}

	@PostMapping("/partidos")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AssociadoPartidoDTO> insertAssociaPartido(@RequestBody @Valid AssociaPartidoAssociadoDTO associaPartidoAssociado) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/associados/{id}").buildAndExpand(associaPartidoAssociado.getIdAssociado()).toUri();
		return ResponseEntity.created(uri).body(associadoService.insertAssociaPartido(associaPartidoAssociado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		associadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Associado> update(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid Associado associado) {
		associadoService.update(id, associado);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idAssociado}/partidos/{idPartido}")
	@Transactional
	public ResponseEntity<?> deletarAssociacaoPartido(@PathVariable Integer idAssociado, @PathVariable Integer idPartido){
		associadoService.deletarAssociaPartidoAssociado(idAssociado, idPartido);
		return ResponseEntity.noContent().build();
	}

}
