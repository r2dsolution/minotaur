package com.r2dsolution.comein.minotaur.function;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.entity.view.TicketView;
import com.r2dsolution.comein.minotaur.function.model.TourTicket;


@Component
public class ComeInMapper {
	
	private  DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

	public  <T extends TourTicket> T map(TicketView v,T t) {
		
		t.setAdultRate(v.getAdultRate());
		t.setCancelable(v.getCancelable());
		t.setCancelBefore(v.getCancelBefore());
		t.setChildRate(v.getChildRate());
		t.setCompanyId(v.getCompanyId());
		t.setCompanyName(v.getCompanyName());
		t.setCompanyAddress(v.getCompanyAddress());
		t.setCountry(v.getCountry());
		t.setDetail(v.getDetail());
		t.setEndDate(dFormat.format(v.getEndDate()));
		t.setFirstTicketId(v.getFirstTicketId());
		t.setInventoryId(v.getInventoryId());
		t.setProvince(v.getProvince());
		t.setStartDate(dFormat.format(v.getStartDate()));
		t.setTicketCode(v.getTicketCode());
		t.setTicketCount(v.getTicketCount());
		t.setTicketStatus(v.getTicketStatus());
		t.setTotal(v.getTotal());
		t.setTourDate(dFormat.format(v.getTourDate()));
		t.setTourDesc(v.getTourDesc());
		t.setTourId(v.getTourId());
		t.setTourName(v.getTourName());
		t.setTourStatus(v.getTourStatus());
		
		return t;
	}
}
