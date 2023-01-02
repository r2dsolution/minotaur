package com.r2dsolution.comein.minotaur.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.minotaur.function.LoadTourTicketByDateFunc;

@Configuration
@ComponentScan("com.r2dsolution.comein.minotaur.function")
@EnableJpaRepositories(basePackages = "com.r2dsolution.comein.minotaur.repository")
public class MinotaurFunctionConfig {
	
	@Autowired
	LoadTourTicketByDateFunc LoadTourTicketByDateFunc;
	
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
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> loadTourTicketByDate(){
		return request -> {
			return LoadTourTicketByDateFunc.execute(request);
		};
	}
	

}
