package com.r2dsolution.comein.minotaur.function.model;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComeInAPIRequest extends APIGatewayProxyRequestEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> jsonBody = new HashMap<String,Object>();

	@JsonProperty("json-body")
	public Map<String, Object> getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(Map<String, Object> jsonBody) {
		this.jsonBody = jsonBody;
	}

	
}
