package com.r2dsolution.comein.minotaur.function.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.entity.view.TourTicketView;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.model.ComeInMapper;
import com.r2dsolution.comein.minotaur.repository.TourTicketViewRepository;
import com.r2dsolution.comein.minotaur.util.DateUtils;


@Component
public class ListTourTicketByDateFunc  extends ComeInFunction{
	
	@Autowired
	TourTicketViewRepository repo;
	
	@Autowired
	ComeInMapper comeInMapper;
	
	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		try {
		
			Map<String,Object> input = request.getJsonBody();
			Map<String,Object> output = new HashMap<String,Object>();
//			TourTicketViewRepository repo = ctx.getBean(TourTicketViewRepository.class);
			
			List<TourTicketView> list  = new ArrayList<TourTicketView>();
			String tourDateStr = (String) input.get("tour-date");
			log("param tour-date: "+tourDateStr);
			if (tourDateStr!=null) {
				String province = (String) input.get("province");
				log("param province: "+province);
				java.sql.Date tourDate = DateUtils.initSQLDate(tourDateStr);
				
				if (tourDate!=null) {
					if (province==null || province.trim().equals("")) {
						log("search by tour-date: "+tourDate);
						list = repo.findByTourDate(tourDate);
					} else {
						log("search by tour-date, province:"+province);
					}
					for(TourTicketView v: list) {
						log("ticket id:"+v.getFirstTicketId());
					}
					
				};
			};
			output.put("results", comeInMapper.map(list));
			return toComeInResults(output);
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
		}
	}

}
