package com.compass.uol.av_4.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.compass.uol.av_4.entity.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String nomePartido;
	@NotNull
	private String sigla;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataFundacao;
}
