package com.github.restfuljavagae.resources;

public class ErrorResourceResponse extends ResourceResponse {

	public ErrorResourceResponse(String message) {
		super();
		setSuccess(false);
		setData("message", message);
	}
}
