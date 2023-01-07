package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.entity.view.BookedTourTicketView;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;
import com.r2dsolution.comein.minotaur.model.ComeInMapper;
import com.r2dsolution.comein.minotaur.repository.BookedTourTicketViewRepository;


@Component
public class LoadBookedTourTicketFunc  extends ComeInFunction{
	
	@Autowired
	BookedTourTicketViewRepository repo;
	
	@Autowired
	ComeInMapper comeInMapper;
	
	@Override
	public ComeInAPIResponse doExecute(ComeInAPIRequest request) throws Exception {
		try {
		
			Map<String,Object> input = request.getJsonBody();
			Map<String,Object> output = new HashMap<String,Object>();
			
//			BookedTourTicketViewRepository repo = ctx.getBean(BookedTourTicketViewRepository.class);
			
			String code = (String) request.toQueryStr("code");
			String ownerId = request.getProfile().getComeinId();
			log("param booking-code: "+code);
			BookedTourTicketView v = repo.findByCodeAndOwnerId(code, ownerId);
			output.put("result", comeInMapper.map(v));
			
			return toComeInResults(output);
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
		}
	}

}
