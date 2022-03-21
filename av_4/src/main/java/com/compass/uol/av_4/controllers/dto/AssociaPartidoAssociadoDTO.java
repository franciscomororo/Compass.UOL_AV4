 package com.compass.uol.av_4.controllers.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssociaPartidoAssociadoDTO {
	
	@NotNull
	private Integer idAssociado;
	@NotNull
	private Integer idPartido;
	
	

}
