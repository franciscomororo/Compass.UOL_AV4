package com.compass.uol.av_4.dto;

import java.time.LocalDate;

import com.compass.uol.av_4.entity.enums.CargoPolitico;
import com.compass.uol.av_4.entity.enums.Sexo;

import lombok.Data;

@Data
public class AssociadoDTO {
	
	private Integer id;
	private String nome;
	private CargoPolitico cargoPolitico;
	private LocalDate dataNascimento;
	private Sexo sexo;
}
