package com.r2dsolution.comein.minotaur.function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.minotaur.entity.view.TourTicketView;
import com.r2dsolution.comein.minotaur.repository.TourTicketViewRepository;
import com.r2dsolution.comein.minotaur.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;


@Component
public class LoadTourTicketByDateFunc implements IFunction{
	
	@Autowired
	TourTicketViewRepository tourTicketViewRepository;

	@Override
	public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent request) {
		
//		TourTicketViewRepository repo = ctx.getBean(TourTicketViewRepository.class);
//		
//		String tourDateStr = (String) input.getBody().get("tour-date");
//		int tourIdInt = (int) input.getBody().get("tour-id");
//		log("param tour-date: "+tourDateStr);
//		log("param tour-id: "+tourIdInt);
		int tourIdInt = 46;
		String tourDateStr = "2022-12-27";
		TourTicketView v = tourTicketViewRepository.findByTourDateAndTourId(DateUtils.initSQLDate(tourDateStr), new Long(tourIdInt));
		System.out.println("tour-id: "+v.getTourId());
//		output.put("result", ComeInMapper.map((TicketView)v,new TourTicket()));
//		return output;
		
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setBody("hello by date");
		return response;
	}

}
