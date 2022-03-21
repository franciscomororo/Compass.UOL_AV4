package com.compass.uol.av_4.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.uol.av_4.controllers.dto.AssociaPartidoAssociadoDTO;
import com.compass.uol.av_4.controllers.dto.AssociadoPartidoDTO;
import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.Partido;
import com.compass.uol.av_4.entity.enums.CargoPolitico;
import com.compass.uol.av_4.repository.AssociadoRepository;
import com.compass.uol.av_4.repository.PartidoRepository;

@Service
public class AssociadoService {

	@Autowired
	AssociadoRepository associadoRepository;

	@Autowired
	PartidoRepository partidoRepository;

	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}

	public Associado findById(Integer id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		return associado.get();
	}

	public Associado insert(@Valid Associado associado) {
		return associadoRepository.save(associado);
	}

	public void delete(Integer id) {
		findById(id);
		associadoRepository.deleteById(id);
	}

	public Associado update(Integer id, @Valid Associado associado) {
		Associado newAssociado = associadoRepository.findById(id).orElse(null);
		updateAssociado(newAssociado, associado);
		return associadoRepository.save(newAssociado);
	}

	private void updateAssociado(Associado newAssociado, @Valid Associado associado) {
		newAssociado.setNome(associado.getNome());
		newAssociado.setCargoPolitico(associado.getCargoPolitico());
		newAssociado.setDataNascimento(associado.getDataNascimento());
		newAssociado.setSexo(associado.getSexo());
	}

	public List<Associado> filtrarCargoPolitico(CargoPolitico cargoPolitico) {
		return associadoRepository.findByCargoPolitico(cargoPolitico);
	}

	public AssociadoPartidoDTO insertAssociaPartido(@Valid AssociaPartidoAssociadoDTO associaPartidoAssociado) {
		Partido partido = partidoRepository.findById(associaPartidoAssociado.getIdPartido())
				.orElse(null);
		Associado associado = associadoRepository.findById(associaPartidoAssociado.getIdAssociado()).orElse(null);
		partido.adicionaAssociado(associado);
		return new AssociadoPartidoDTO(associado, partido);
	}

	public void deletarAssociaPartidoAssociado(Integer idAssociado, Integer idPartido) {
		Associado associado = associadoRepository.findById(idAssociado).orElse(null);
		Partido partido = partidoRepository.findById(idPartido).orElse(null);
		partido.removerAssociado(associado);
	}

}
