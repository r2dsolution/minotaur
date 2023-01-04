package com.r2dsolution.comein.minotaur.config;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.minotaur.function.IFunction;
import com.r2dsolution.comein.minotaur.function.api.ListBookingByEmailFunc;
import com.r2dsolution.comein.minotaur.function.api.LoadTourTicketByDateFunc;
import com.r2dsolution.comein.minotaur.function.api.ViewKYCHotelBookingFunc;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;

@Configuration
@ComponentScan({"com.r2dsolution.comein.minotaur.function","com.r2dsolution.comein.minotaur.business"})
@EnableJpaRepositories(basePackages = "com.r2dsolution.comein.minotaur.repository")
public class MinotaurFunctionConfig {
	
	@Autowired
	LoadTourTicketByDateFunc loadTourTicketByDateFunc;
	
	@Autowired
	ListBookingByEmailFunc listBookingByEmailFunc;
	
	@Autowired
	ViewKYCHotelBookingFunc viewKYCHotelBookingFunc;
	
	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getHelloWorld(){
		System.out.println("........Hello............");
		return request -> {
			APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
			response.setBody("Get event: Hello World by spring-cloud-function.");
			return response;
		};
		
	}
	
	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> postHelloWorld(){
		System.out.println("........Hello............");
		return request -> {
			
			APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
			response.setBody("Post event: Hello World by spring-cloud-function.");
			return response;
		};
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> loadTourTicketByDate() throws Exception{

		return request ->  doExecute(loadTourTicketByDateFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> listBookingByEmail() throws Exception{

		return request ->  doExecute(listBookingByEmailFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> viewKYCHotelBooking() throws Exception{

		return request ->  doExecute(viewKYCHotelBookingFunc,request );
			
	}
	

	protected ComeInAPIResponse doExecute(IFunction func,ComeInAPIRequest request){
		try {
			return func.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
			return errorExecute(e,request);
		}
	}

	protected ComeInAPIResponse errorExecute(Exception e,ComeInAPIRequest request) {
		ComeInAPIResponse response = new ComeInAPIResponse();
		response.setStatusCode(200);
		response.setBody(e.getMessage());
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("error", e.getMessage());
		response.setJsonBody(json );
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		response.setHeaders(headers);
		return response;
	}
}
