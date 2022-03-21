package com.compass.uol.av_4.service;

import java.util.List;

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
import com.compass.uol.av_4.service.exception.MethodArgumentNotValidException;
import com.compass.uol.av_4.service.exception.ObjectNotFoundException;

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
		Associado associado = associadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		return associado;
	}

	public Associado insert(@Valid Associado associado) {

		try {
			return associadoRepository.save(associado);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}

	}

	public void delete(Integer id) {
		findById(id);
		associadoRepository.deleteById(id);
	}

	public Associado update(Integer id, @Valid Associado associado) {
		Associado newAssociado = associadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		try {
			updateAssociado(newAssociado, associado);
			return associadoRepository.save(newAssociado);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
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
				.orElseThrow(() -> new ObjectNotFoundException("Partido com ID não encontrado"));
		Associado associado = associadoRepository.findById(associaPartidoAssociado.getIdAssociado())
				.orElseThrow(() -> new ObjectNotFoundException("Associado com ID não encontrado"));
		partido.adicionaAssociado(associado);
		return new AssociadoPartidoDTO(associado, partido);
	}

	public void deletarAssociaPartidoAssociado(Integer idAssociado, Integer idPartido) {
		Partido partido = partidoRepository.findById((idPartido))
				.orElseThrow(() -> new ObjectNotFoundException("Partido com ID não encontrado"));
		Associado associado = associadoRepository.findById(idAssociado)
				.orElseThrow(() -> new ObjectNotFoundException("Associado com ID não encontrado"));
		partido.removerAssociado(associado);
	}

}
