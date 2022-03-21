package com.compass.uol.av_4.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
