package com.j4ltechnologies.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {
	
	private static final long serialVersionID = 1L;
	
	public NotFoundException(ErrorMessage message) {
		super(
				Response.status(Response.Status.NOT_FOUND)
				.entity(message)
				.type(MediaType.APPLICATION_JSON)
				.build()
				);
	}

}
