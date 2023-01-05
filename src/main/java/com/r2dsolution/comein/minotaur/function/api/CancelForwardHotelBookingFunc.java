package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;

@Component
public class CancelForwardHotelBookingFunc extends ComeInFunction{
	
	@Autowired
	BookingInfoRepository repo;

	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		Map<String,Object> output = new HashMap<String,Object>();
		String bookNO = request.toQueryStr("book-no");
		
		String ownerId = request.getProfile().getComeinId();
		//String name = input.getProfile().getRef_name();
		
		//AdminCognitoClient client = ctx.getBean(AdminCognitoClient.class);
	
		
		//log("email :"+email);
		//log("name :"+name);
		log("ownerId :"+ownerId);
		log("book-no :"+bookNO);
//		
//		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		 repo.resetBookInfo(fullname, profile_email, bookNO);
		// repo.updateBookInfo(name,ownerId,ownerId,bookNO);
		 repo.resetRefBookInfo( ownerId, bookNO);
		 
		 log("reset-BookInfo completed");
		 output.put("book-no",bookNO);

		return toComeInResults(output);
	}

}
