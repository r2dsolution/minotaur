package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.entity.BookingInfoM;
import com.r2dsolution.comein.minotaur.entity.HotelM;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.model.ComeInMapper;
import com.r2dsolution.comein.minotaur.model.HotelBooking;
import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;
;

@Component
public class ViewHotelBookingByBookNOFunc  extends ComeInFunction{
	
	@Autowired
	BookingInfoRepository repo;
	
	@Autowired
	ComeInMapper comeInMapper;
	
	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		try {
		
			Map<String,Object> input = request.getJsonBody();
			Map<String,Object> output = new HashMap<String,Object>();
			 String bookNO = request.toQueryStr("book-no");
			 //String email = input.getProfile().getEmail();
			 String ownerId = request.getProfile().getComeinId();
			 log(" - bookNO: "+bookNO+" ,owner-id: "+ownerId);
			
//			 BookingInfoRepository repo = ctx.getBean(BookingInfoRepository.class);
			 
			 //Optional<BookingInfoM> opt = repo.findByBookingNoAndEmail(bookNO, email);
			 Optional<BookingInfoM> opt = repo.findByBookingNoAndOwnerId(bookNO,ownerId);
			 HotelBooking hotelBook  = null;
			 if (opt.isPresent()) {
				 BookingInfoM book = opt.get();
				 
				 Long hotelId = book.getHotelInfo().getId();
				 log("book-no: "+book.getBookingNo()+", hotel: "+hotelId);
//				 HotelRepository hotelRepo = ctx.getBean(HotelRepository.class);
//				 HotelM hotel = hotelRepo.findById(hotelId).get();
				 HotelM hotel = book.getHotelInfo();
				  hotelBook = comeInMapper.map(book, hotel);
			 } 
			output.put("result", hotelBook);
			output.put("book-no", bookNO);
			return toComeInResults(output);
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
		}
	}

}
