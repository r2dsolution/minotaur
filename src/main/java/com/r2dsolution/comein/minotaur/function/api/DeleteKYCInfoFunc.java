package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.business.ViewKYCBookingDelegate;
import com.r2dsolution.comein.minotaur.entity.BookingInfoM;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.model.HotelBooking;
import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;
import com.r2dsolution.comein.minotaur.repository.UserKYCRepository;


@Component
public class DeleteKYCInfoFunc extends ComeInFunction{
	
	@Autowired
	BookingInfoRepository repo;
	
	@Autowired
	UserKYCRepository kycRepo;
	
	@Autowired
	ViewKYCBookingDelegate viewKYCBookingDelegate;

	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		String ownerId = request.getProfile().getComeinId();
		
		Map<String,Object> input = request.getJsonBody();
		Map<String,Object> output = new HashMap<String,Object>();
		
		//String email = (String) input.getBody().get("email");
		String refId  = (String) input.get("ref-id");
		String refType  = (String) input.get("ref-type");
		
		String bookno = (String) input.get("book-no");
		log("refId="+refId+" owner="+ownerId);
		
		Object isConfirmObj = (String) input.get("confirm");
		boolean isConfirm = false;
		if (isConfirmObj!=null) {
			String isConfirmStr = (String)isConfirmObj;
			if (isConfirmStr.trim().equals("Y")) {
				isConfirm = true;
			}
		}
		
		if (isConfirm) {
//			BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			Optional<BookingInfoM> bookInfoOpt = repo.findByBookingNoAndOwnerId(bookno, ownerId);
			if (bookInfoOpt.isPresent()) {
				
				log("refId="+refId+" ownerId="+ownerId);
				BookingInfoM bookInfo = bookInfoOpt.get();
				
				bookInfo.removeBookingKYC(refId);
				repo.save(bookInfo);
				log("delete kyc-booking-info success");
			}
		}
		
//		UserKYCRepository kycRepo = ctx.getBean(UserKYCRepository.class);
		kycRepo.deleteKYCByRef(refId,refType,ownerId);
		log("delete kyc-info success");
		
//		 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//		Optional<BookingInfoM> result = repo.findByBookingNoAndOwnerId(bookno, ownerId);
//		if (result.isPresent()) {
//			//output.put("hotel-booking", result.get());
//			output.put("result", result.get());
//		}
		
//		DelegateFactory factory = ctx.getBean(DelegateFactory.class);
//		 ViewKycBookingDelegate bd =  factory.initViewKycBookingDelegate(context);
		 HotelBooking book  = viewKYCBookingDelegate.viewHotelBooking(bookno, ownerId);
		 output.put("result",book);
		
		return toComeInResults(output);
	}

}
