package com.compass.uol.av_4.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.uol.av_4.controllers.dto.AssociadoPartidoDTO;
import com.compass.uol.av_4.entity.enums.CargoPolitico;
import com.compass.uol.av_4.entity.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Associado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @Enumerated(EnumType.STRING)
	private CargoPolitico cargoPolitico;
    @JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
	@NotNull @Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	public AssociadoPartidoDTO converter(Associado associado, Partido partido) {
		return new AssociadoPartidoDTO(associado, partido);
	}
	

}
