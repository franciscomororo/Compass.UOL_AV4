package com.compass.uol.av_4.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.enums.CargoPolitico;
import com.compass.uol.av_4.repository.AssociadoRepository;

@Service
public class AssociadoService {

	@Autowired
	AssociadoRepository associadoRepository;

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

}
