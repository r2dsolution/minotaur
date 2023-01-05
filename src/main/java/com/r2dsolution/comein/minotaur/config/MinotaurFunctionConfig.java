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
import com.r2dsolution.comein.minotaur.function.api.AddBookingKYCFunc;
import com.r2dsolution.comein.minotaur.function.api.AddKYCInfoFunc;
import com.r2dsolution.comein.minotaur.function.api.CancelForwardHotelBookingFunc;
import com.r2dsolution.comein.minotaur.function.api.ForwardHotelBookingFunc;
import com.r2dsolution.comein.minotaur.function.api.InitBookingByIdFunc;
import com.r2dsolution.comein.minotaur.function.api.ListBookingByEmailFunc;
import com.r2dsolution.comein.minotaur.function.api.ListBookingByRefFunc;
import com.r2dsolution.comein.minotaur.function.api.ListTourBookingByEmailFunc;
import com.r2dsolution.comein.minotaur.function.api.ListTourTicketByDateFunc;
import com.r2dsolution.comein.minotaur.function.api.LoadBookedTourTicketFunc;
import com.r2dsolution.comein.minotaur.function.api.LoadTourTicketByDateFunc;
import com.r2dsolution.comein.minotaur.function.api.ReserveTourBookingFunc;
import com.r2dsolution.comein.minotaur.function.api.ViewHotelBookingByBookNOFunc;
import com.r2dsolution.comein.minotaur.function.api.ViewKYCHotelBookingFunc;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;

@Configuration
@ComponentScan({"com.r2dsolution.comein.minotaur.function","com.r2dsolution.comein.minotaur.business","com.r2dsolution.comein.minotaur.client"})
@EnableJpaRepositories(basePackages = "com.r2dsolution.comein.minotaur.repository")
public class MinotaurFunctionConfig {
	
	@Autowired
	LoadTourTicketByDateFunc loadTourTicketByDateFunc;
	
	@Autowired
	ListBookingByEmailFunc listBookingByEmailFunc;
	
	@Autowired
	ListBookingByRefFunc listBookingByRefFunc;
	
	@Autowired
	ViewKYCHotelBookingFunc viewKYCHotelBookingFunc;
	
	@Autowired
	AddKYCInfoFunc addKYCInfoFunc;
	
	@Autowired
	AddBookingKYCFunc addBookingKYCFunc;
	
	@Autowired
	ForwardHotelBookingFunc forwardHotelBookingFunc;
	
	@Autowired
	CancelForwardHotelBookingFunc cancelForwardHotelBookingFunc;
	
	@Autowired
	InitBookingByIdFunc initBookingByIdFunc;
	
	@Autowired
	ListTourTicketByDateFunc listTourTicketByDateFunc;
	
	@Autowired
	ListTourBookingByEmailFunc listTourBookingByEmailFunc;
	
	@Autowired
	LoadBookedTourTicketFunc loadBookedTourTicketFunc;
	
	@Autowired
	ReserveTourBookingFunc reserveTourBookingFunc;
	
	@Autowired
	ViewHotelBookingByBookNOFunc viewHotelBookingByBookNOFunc;
	
	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getHelloWorld(){
		System.out.println("init....................getHelloWorld");
		return request -> {
			APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
			response.setBody("Get event: Hello World by spring-cloud-function.");
			return response;
		};
		
	}
	
	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> postHelloWorld(){
		System.out.println("init....................postHelloWorld");
		return request -> {
			
			APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
			response.setBody("Post event: Hello World by spring-cloud-function.");
			return response;
		};
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> loadTourTicketByDate() throws Exception{
		System.out.println("init....................loadTourTicketByDate");
		return request ->  doExecute(loadTourTicketByDateFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> listBookingByEmail() throws Exception{
		System.out.println("init....................listBookingByEmail");
		return request ->  doExecute(listBookingByEmailFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> listBookingByRef() throws Exception{
		System.out.println("init....................listBookingByRef");
		return request ->  doExecute(listBookingByRefFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> viewKYCHotelBooking() throws Exception{
		System.out.println("init....................viewKYCHotelBooking");
		return request ->  doExecute(viewKYCHotelBookingFunc,request );
			
	}
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> addKYCInfo() throws Exception{
		System.out.println("init....................addKYCInfo");
		return request ->  doExecute(addKYCInfoFunc,request );
			
	}
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> addBookingKYC() throws Exception{
		System.out.println("init....................addBookingKYC");
		return request ->  doExecute(addBookingKYCFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> forwardHotelBooking() throws Exception{
		System.out.println("init....................forwardHotelBooking");
		return request ->  doExecute(forwardHotelBookingFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> cancelForwardHotelBooking() throws Exception{
		System.out.println("init....................cancelForwardHotelBooking");
		return request ->  doExecute(cancelForwardHotelBookingFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> initBookingById() throws Exception{
		System.out.println("init....................initBookingById");
		return request ->  doExecute(initBookingByIdFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> listTourTicketByDate() throws Exception{
		System.out.println("init....................listTourTicketByDate");
		return request ->  doExecute(listTourTicketByDateFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> listTourBookingByEmail() throws Exception{
		System.out.println("init....................listTourBookingByEmail");
		return request ->  doExecute(listTourBookingByEmailFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> loadBookedTourTicket() throws Exception{
		System.out.println("init....................loadBookedTourTicket");
		return request ->  doExecute(loadBookedTourTicketFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> reserveTourBooking() throws Exception{
		System.out.println("init....................reserveTourBooking");
		return request ->  doExecute(reserveTourBookingFunc,request );
			
	}
	
	@Bean
	public Function<ComeInAPIRequest, ComeInAPIResponse> viewHotelBookingByBookNO() throws Exception{
		System.out.println("init....................viewHotelBookingByBookNO");
		return request ->  doExecute(viewHotelBookingByBookNOFunc,request );
			
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
