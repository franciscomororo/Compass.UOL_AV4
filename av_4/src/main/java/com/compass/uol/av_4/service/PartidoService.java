package com.compass.uol.av_4.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.uol.av_4.controllers.dto.AssociadoPartidoDTO;
import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.Partido;
import com.compass.uol.av_4.entity.enums.Ideologia;
import com.compass.uol.av_4.repository.PartidoRepository;

@Service
public class PartidoService {

	@Autowired
	PartidoRepository partidoRepository;

	public List<Partido> findAll() {
		return partidoRepository.findAll();
	}

	public Partido findById(Integer id) {
		Optional<Partido> partido = partidoRepository.findById(id);
		return partido.get();
	}

	public Partido insert(Partido partido) {
		return partidoRepository.save(partido);
	}

	public void delete(Integer id) {
		findById(id);
		partidoRepository.deleteById(id);
	}

	public Partido update(Integer id, @Valid Partido partido) {
		Partido newPartido = partidoRepository.findById(id).orElse(null);
		updatePartido(newPartido, partido);
		return partidoRepository.save(newPartido);
	}

	private void updatePartido(Partido newPartido, @Valid Partido partido) {
		newPartido.setNomePartido(partido.getNomePartido());
		newPartido.setDataFundacao(partido.getDataFundacao());
		newPartido.setSigla(partido.getSigla());
		newPartido.setIdeologia(partido.getIdeologia());
	}

	public List<Partido> filtrarIdeologia(Ideologia ideologia) {
		return partidoRepository.findByIdeologia(ideologia);
	}

	public List<AssociadoPartidoDTO> findByPartidoAssociados(Integer id) {
		Partido partido = findByPartido(id);
		List<Associado> associados = partido.getAssociados();
		List<AssociadoPartidoDTO> associadosDTO = associados.stream().map(ass -> ass.converter(ass, partido))
				.collect(Collectors.toList());
		return associadosDTO;
	}

	private Partido findByPartido(Integer id) {
		Partido partido = partidoRepository.findById(id).orElse(null);
		return partido;
	}

}
