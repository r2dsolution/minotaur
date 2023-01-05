package com.r2dsolution.comein.minotaur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.r2dsolution.comein.minotaur.repository.BookingInfoRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingInfoRepository repo;
	
	@Transactional
	public void updateRef(String name,String ownerId,String bookNO) {
		System.out.println("do transactional by service");
		 repo.updateRefBookInfo(name,ownerId, bookNO);
		
	}

}
