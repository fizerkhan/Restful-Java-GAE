package com.github.restfuljavagae.resources;

import java.util.HashMap;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import flexjson.JSONSerializer;

public class ResourceResponse {
	
	Object data;
	HashMap responseMap;
	
	public ResourceResponse() {
		responseMap = new HashMap();
	}
	
	public ResourceResponse(boolean success, String message, HashMap data) {
		responseMap = data;
		this.setSuccess(true);
		if( message != null )
			this.setData("message", message);
	}
	
	public void setData(String key, Object data) {
		responseMap.put(key, data);
	}
	
	public void setSuccess(boolean success) {
		responseMap.put("success", success);
	}

	public Representation asJSon() {
		return(new StringRepresentation(
				new JSONSerializer().serialize(responseMap)));
	}
	
	public Representation asXml() {
		return(new StringRepresentation("asXML() not implemented yet"));
	}
}
