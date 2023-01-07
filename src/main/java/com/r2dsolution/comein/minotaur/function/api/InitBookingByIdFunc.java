package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.business.InitBookingByIdDelegate;
import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;

@Component
public class InitBookingByIdFunc extends ComeInFunction{
	
	@Autowired
	InitBookingByIdDelegate initBookingByIdDelegate;
	
	@Override
	public ComeInAPIResponse doExecute(ComeInAPIRequest request) throws Exception {
		
		Map<String,Object> input = request.getJsonBody();
		Map<String,Object> output = new HashMap<String,Object>();
		try {
//			 DelegateFactory factory = ctx.getBean(DelegateFactory.class);
//			 InitBookingByIdDelegate delete = factory.initInitBookingByIdDelegate(context);
			 
			 List<Integer> idList = (List<Integer>) input.get("id-list");
			 log(idList.toString());
			 String ownerId = request.getProfile().getComeinId();
			 
			 initBookingByIdDelegate.initBookingById(idList, ownerId);
			 return toComeInResults(output);
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
			}

		
	}

}
