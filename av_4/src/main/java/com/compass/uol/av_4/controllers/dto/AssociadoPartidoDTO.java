package com.compass.uol.av_4.controllers.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.Partido;
import com.compass.uol.av_4.entity.enums.CargoPolitico;

import lombok.Data;

@Data
public class AssociadoPartidoDTO {
	
	
	public AssociadoPartidoDTO(Associado associado, Partido partido) {
		this.setId(associado.getId());
		this.setNome(associado.getNome());
		this.setCargoPolitico(associado.getCargoPolitico());
		this.setSigla(partido.getSigla());
		this.setNomePartido(partido.getNomePartido());
	}
	private Integer id;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @Enumerated(EnumType.STRING)
	private CargoPolitico cargoPolitico;
	@NotNull @NotEmpty
	private String nomePartido;
	@NotNull @NotEmpty
	private String sigla;

}
