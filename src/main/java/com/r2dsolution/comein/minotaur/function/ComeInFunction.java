package com.r2dsolution.comein.minotaur.function;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ComeInFunction implements IFunction{
	protected ObjectMapper mapper = new ObjectMapper();

	
	
	protected Map<String,Object> toJsonMap(String body) throws Exception{
		JsonNode nodes = mapper.readTree(body);
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>(){};	
		Map<String, Object> jsonBody = mapper.convertValue(nodes, typeRef);
		return jsonBody;
	}

	protected void log(String s) {
		System.out.println(s);
	}
	
}
