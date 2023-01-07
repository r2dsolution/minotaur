package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.business.ViewKYCBookingDelegate;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.model.HotelBooking;


@Component
public class ViewKYCHotelBookingFunc extends ComeInFunction{
	
	@Autowired
	ViewKYCBookingDelegate viewKYCBookingDelegate;

	@Override
	public ComeInAPIResponse doExecute(ComeInAPIRequest request) throws Exception {
		 try {
			 Map<String,Object> output = new HashMap<String,Object>();
//			 String url_pattern = "/hotel-bookings/[a-zA-Z0-9]*";
			
//			 log("path: "+path);
//			 String bookNO = getPathParam(url_pattern,path);
			// 
			
			 String bookNO = request.toQueryStr("book-no");
			// String email = input.getProfile().getEmail();
			 String ownerId = request.getProfile().getComeinId();
			 log(" - bookNO: "+bookNO+" ,owner-id: "+ownerId);
			
//			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
//			 
//			// Optional<BookingInfoM> opt = repo.findByBookingNoAndEmail(bookNO, email);
//			 Optional<BookingInfoM> opt = repo.findByBookingNoAndOwnerId(bookNO,ownerId);
//			 HotelBooking hotelBook  = null;
//			 if (opt.isPresent()) {
//				 BookingInfoM book = opt.get();
//				 log("book-no: "+book.getBookingNo()+" id: "+book.getId());
//				 hotelBook = toJson(book);
//				  
//			 } 
			 
//			 DelegateFactory factory = ctx.getBean(DelegateFactory.class);
//			 ViewKycBookingDelegate bd =  factory.initViewKycBookingDelegate(context);
			 HotelBooking hotelBook  = viewKYCBookingDelegate.viewHotelBooking(bookNO, ownerId);
			 
			output.put("result", hotelBook);
			output.put("book-no",bookNO);
			 
			 return toComeInResults(output);
		 } catch (Exception e) {
				log("error: "+e.getMessage());
				throw e;
			}
			
		}
	}


