package com.r2dsolution.comein.minotaur.function;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;

public abstract class ComeInFunction implements IFunction{
	
	public static final int SC_OK = 200;
	protected ObjectMapper mapper = new ObjectMapper();

	
	
	protected Map<String,Object> toJsonMap(String body) throws Exception{
		JsonNode nodes = mapper.readTree(body);
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>(){};	
		Map<String, Object> jsonBody = mapper.convertValue(nodes, typeRef);
		return jsonBody;
	}
	
	protected String toJsonStr(Map<String,Object> json)throws Exception{
		return mapper.writeValueAsString(json);
	}

	protected void log(String s) {
		System.out.println(s);
	}
	
	protected APIGatewayProxyResponseEvent toResults(Map<String,Object> json) throws Exception{
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setBody(toJsonStr(json));
		response.setHeaders(initJsonHeaders());
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		return response;
	}
	protected ComeInAPIResponse toComeInResults(Map<String,Object> json) throws Exception{
		
//		  this.headers = Collections.unmodifiableMap(new HashMap<>(_headers));
//	        this.body = Collections.unmodifiableMap(new HashMap<>(json_body));
//	        this.statusCode = HttpStatus.SC_OK;
	        
		ComeInAPIResponse response = new ComeInAPIResponse();
		response.setBody(toJsonStr(json));
		response.setJsonBody(Collections.unmodifiableMap(new HashMap<>(json)));
		response.setHeaders(Collections.unmodifiableMap(new HashMap<>(initJsonHeaders())));
		response.setIsBase64Encoded(false);
		response.setStatusCode( SC_OK);
		return response;
	}
	Map<String,String> initJsonHeaders(){
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		return headers;
	}
}
