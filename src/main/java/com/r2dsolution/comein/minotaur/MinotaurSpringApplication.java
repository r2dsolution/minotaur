package com.r2dsolution.comein.minotaur;

import java.util.function.Function;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.minotaur.config.MinotaurAWSConfig;
import com.r2dsolution.comein.minotaur.config.MinotaurFunctionConfig;


@SpringBootApplication
@Import({MinotaurFunctionConfig.class,MinotaurAWSConfig.class})
@EnableAutoConfiguration
public class MinotaurSpringApplication {

	/*
	 * You need this main method (empty) or explicit <start-class>example.FunctionConfiguration</start-class>
	 * in the POM to ensure boot plug-in makes the correct entry
	 */
	public static void main(String[] args) {
		// empty unless using Custom runtime at which point it should include
		// SpringApplication.run(FunctionConfiguration.class, args);
	}

	
	
	
}
