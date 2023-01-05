package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;
import com.r2dsolution.comein.minotaur.service.BookingService;


@Component
public class ForwardHotelBookingFunc extends ComeInFunction{
	
//	@Autowired
//	BookingInfoRepository repo;
	
	@Autowired
	BookingService service;

	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		
		Map<String,Object> input = request.getJsonBody();
		Map<String,Object> output = new HashMap<String,Object>();
		
		String email = (String) input.get("email");
//		AdminCognitoClient client = ctx.getBean(AdminCognitoClient.class);
//		UserType user = client.findByEmail(email);
//		String targetId = null;
//		if (user!=null) {
//		String	targetId = client.getAttr(user, AdminCognitoClient.ATTRIBUTE_COMEIN_ID);
			
//			String name = client.getAttr(user, AdminCognitoClient.ATTRIBUTE_REF_NAME);
//			if (input.getBody().containsKey("name")) {
			String	name = (String) input.get("name");
		
				
//			}
			//String profile_email = input.getProfile().getEmail();
			String ownerId = request.getProfile().getComeinId();
			//String bookNO = input.getPath("bookNO");
			String bookNO = request.toQueryStr("book-no");
			log("email :"+email);
			log("name :"+name);
			log("owner-id :"+ownerId);
//			log("target-id :"+targetId);
			log("book-no :"+bookNO);
			
//			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//			repo.updateRefBookInfo(name,ownerId, bookNO);
			service.updateRef(name,ownerId, bookNO);
			 
			 log("updateBookInfo completed");
//		}else {
//			log("not found email");
//		}
		
		
			 output.put("book-no",bookNO);
		

		return toComeInResults(output);
	}
	
//	@Transactional
//	public void updateRef(String name,String ownerId,String bookNO) {
//		log("do transactional - Update Database");
//		 repo.updateRefBookInfo(name,ownerId, bookNO);
//		
//	}

}
