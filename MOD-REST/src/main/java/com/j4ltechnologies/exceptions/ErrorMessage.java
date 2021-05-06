package com.j4ltechnologies.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
	
	private String code;
	private String description;
	private String uri;
	private Response.Status status;

}
