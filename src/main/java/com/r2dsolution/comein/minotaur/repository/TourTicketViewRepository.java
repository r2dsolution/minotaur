package com.r2dsolution.comein.minotaur.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.r2dsolution.comein.minotaur.entity.view.TourTicketView;



public interface TourTicketViewRepository extends Repository<TourTicketView,Long>{
	
	List<TourTicketView> findByTourDate(java.sql.Date date);
	TourTicketView findByTourDateAndTourId(java.sql.Date date,Long tourId);
	TourTicketView findByFirstTicketId(Long ticketId);

}
