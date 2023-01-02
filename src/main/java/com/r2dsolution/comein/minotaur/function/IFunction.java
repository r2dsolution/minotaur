package com.r2dsolution.comein.minotaur.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface IFunction {

	APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent request);
}
