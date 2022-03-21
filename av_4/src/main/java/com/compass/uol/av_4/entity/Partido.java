package com.compass.uol.av_4.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
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
	@NotNull @NotEmpty
	private String nomePartido;
	@NotNull @NotEmpty
	private String sigla;
	@NotNull 
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataFundacao;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPartido")
	private List<Associado> associados = new ArrayList<Associado>();
	
	
	public void adicionaAssociado(Associado associado) {
		associados.add(associado);
	}
	public void removerAssociado(Associado associado) {
		associados.remove(associado);
	}
	
	public boolean procurarAssociado(Associado associado) {
		return associados.contains(associado);
	}
	
}



