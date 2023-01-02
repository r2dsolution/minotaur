package com.r2dsolution.comein.minotaur.function.model;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
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
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	
	public ComeInAPIRequest() {
		
	}
	
	public ComeInAPIRequest(APIGatewayProxyRequestEvent request) {
		
		
	}

	public Map<String, Object> getJsonBody() throws Exception {
		JsonNode nodes = mapper.readTree(this.getBody());
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>(){};
		
		Map<String, Object> jsonBody = mapper.convertValue(nodes, typeRef);
		return jsonBody;
	}

	
}
