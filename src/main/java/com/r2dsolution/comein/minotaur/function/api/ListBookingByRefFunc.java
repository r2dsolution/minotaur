package com.r2dsolution.comein.minotaur.function.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


import reactor.core.publisher.Flux;

@Component
public class ListBookingByRefFunc extends ComeInFunction{
	
	@Autowired
	BookingInfoRepository bookingInfoRepository;
	
	@Autowired
	ComeInMapper comeInMapper;
	
	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		try {
		
		Map<String,Object> input = request.getJsonBody();
		Map<String,Object> output = new HashMap<String,Object>();
		
		 
//		BusinessDelegateFactory factory = ctx.getBean(BusinessDelegateFactory.class);
//		ListBookingInfoByRefDelegate delegate = factory.initListBookingInfoByRefDelegate(context);
//		List<BookingInfoM> bookList = delegate.getBookingInfo(input.getProfile().getEmail(), input.getProfile().getRef_name());
		
		String email = request.getProfile().getEmail();
		String refname2 = request.getProfile().getRefName();
		log("email: "+email);
//		 log("owner-id: "+ownerId);
		 log("refname2: "+refname2);
		 
		//List<BookingInfoM> books = repo.findByEmailOrCustomerEmail(email,email);
		 List<BookingInfoM> bookList = new ArrayList<BookingInfoM>();
		 List<BookingInfoM> book1 = bookingInfoRepository.findByReference(email);
		 log("result by email size:"+book1.size());
		 List<BookingInfoM> book2 = bookingInfoRepository.findByRefName2(refname2);
		 log("result by refname size:"+book2.size());
		 bookList.addAll(book1);
		 bookList.addAll(book2);
		 log("result size:"+bookList.size());
		
	//	MapBookingInfoToHotelBookingDelegate map = factory.initMapBookingInfoToHotelBookingDelegate(context);
		Flux<HotelBooking> results = mapHotelBooking(bookList);
		
		 
//		for(BookingInfoM book: bookList) {
//			Long hotelId = book.getHotelId().getId();
//			doCache(hotelId,hotelRepo,HotelM.class);
//			HotelM hotel = getCache(hotelId,HotelM.class);
//			//HotelM hotel = hotels.get(hotelId);
//			log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
//			HotelBooking result = ComeInMapper.map(book,hotel);
//			results.add(result);
//		}
		output.put("email", request.getProfile().getEmail());
		output.put("ref-name", request.getProfile().getRefName());
		
//		Map<String,Object> bookings = toResult("hotel-booking",results.collectList().block());
		List<HotelBooking> bookings = results.collectList().block();
		 output.put("results", bookings);
		 output.put("size", bookList.size());
		
		 return toComeInResults(output);
	 } catch (Exception e) {
			e.printStackTrace();
			log("error: "+e.getMessage());
			throw e;
		}
	}
	
	public Flux<HotelBooking> mapHotelBooking(List<BookingInfoM> bookList) {
		Flux<BookingInfoM> books = Flux.fromIterable(bookList);
		Flux<HotelBooking> hotelBooks = books.map(b->mapHotelBooking(b))
											.filter(b -> b!=null);
		return hotelBooks;
	}

	public HotelBooking mapHotelBooking(BookingInfoM book) {
		log("book-id:"+book.getId());
		Long hotelId = book.getHotelInfo().getId();
		log("hotel-id: "+hotelId);
////		doCache(hotelId,hotelRepo,HotelM.class);
////		HotelM hotel = getCache(hotelId,HotelM.class);
		HotelM hotel = book.getHotelInfo();
//		Optional<HotelM> hotelOpt = hotelRepository.findById(hotelId);
//		if (hotelOpt.isPresent()) {
//			hotel = hotelOpt.get();
//		}
		//HotelM hotel = hotels.get(hotelId);
		if (hotel!=null) {
			log("book-no: "+book.getBookingNo()+" hotel-name: "+hotel.getHotelName());
			HotelBooking result = comeInMapper.map(book,hotel);
			return result;
		} else {
			return null;
		}
	}
}
