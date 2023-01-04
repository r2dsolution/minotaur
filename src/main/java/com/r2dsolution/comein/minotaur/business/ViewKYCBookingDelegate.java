package com.r2dsolution.comein.minotaur.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.entity.BookingInfoM;
import com.r2dsolution.comein.minotaur.entity.HotelM;
import com.r2dsolution.comein.minotaur.entity.UserKYCInfoM;
import com.r2dsolution.comein.minotaur.model.ComeInMapper;
import com.r2dsolution.comein.minotaur.model.HotelBooking;
import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;
import com.r2dsolution.comein.minotaur.repository.HotelRepository;
import com.r2dsolution.comein.minotaur.repository.UserKYCRepository;



@Component
public class ViewKYCBookingDelegate extends BusinessDelegate{
	
	@Autowired
	private BookingInfoRepository bookingInfoRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserKYCRepository userKYCRepository;
	
	@Autowired
	private ComeInMapper comeInMapper;
	
	
	
	public HotelBooking viewHotelBooking(String bookNO,String ownerId) throws Exception {
		 Optional<BookingInfoM> opt = bookingInfoRepository.findByBookingNoAndOwnerId(bookNO,ownerId);
		 HotelBooking hotelBook  = null;
		 if (opt.isPresent()) {
			 BookingInfoM book = opt.get();
			 log("book-no: "+book.getBookingNo()+" id: "+book.getId());
			 hotelBook = toJson(book);
			  
		 } ;
		 return hotelBook;
	}
	

	HotelBooking toJson(BookingInfoM book) throws Exception {
//		Long hotelId = book.getHotelId().getId();
//		
//		 HotelM hotel = hotelRepository.findById(hotelId).get();
		HotelM hotel = book.getHotelInfo();
		 HotelBooking  hotelBook = comeInMapper.map(book, hotel);
		 hotelBook = comeInMapper.map(hotelBook, book.getKycInfo().keySet());
		  
		 
		  List<UserKYCInfoM> kycList = userKYCRepository.findByOwnerId(book.getOwnerId());
		  log("kycList size: "+kycList.size());
		 hotelBook = comeInMapper.map(hotelBook, kycList);
		 return hotelBook;
	}

}
