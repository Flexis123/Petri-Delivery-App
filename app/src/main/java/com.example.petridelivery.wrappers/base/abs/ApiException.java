package com.example.petridelivery.wrappers.base.abs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{
	private Integer status;
	private String body;
	private String statusMessage;
}
