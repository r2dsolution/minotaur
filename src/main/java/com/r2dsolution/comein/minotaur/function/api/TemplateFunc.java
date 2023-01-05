package com.r2dsolution.comein.minotaur.function.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.r2dsolution.comein.minotaur.function.ComeInFunction;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIRequest;
import com.r2dsolution.comein.minotaur.function.model.ComeInAPIResponse;

@Component
public class TemplateFunc  extends ComeInFunction{
	
	@Override
	public ComeInAPIResponse execute(ComeInAPIRequest request) throws Exception {
		try {
		
			Map<String,Object> input = request.getJsonBody();
			Map<String,Object> output = new HashMap<String,Object>();
			return toComeInResults(output);
		 } catch (Exception e) {
				e.printStackTrace();
				log("error: "+e.getMessage());
				throw e;
		}
	}

}
