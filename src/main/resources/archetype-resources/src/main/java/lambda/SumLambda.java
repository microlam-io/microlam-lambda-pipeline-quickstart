package ${package}.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import io.microlam.aws.lambda.APIGatewayProxyLambda;
import io.microlam.aws.lambda.pipeline.APIGatewayProxyRequestEventDecoder;
import io.microlam.aws.lambda.pipeline.APIGatewayProxyResponseEventEncoder;
import io.microlam.aws.lambda.pipeline.LambdaChannel;
import ${package}.bs.BusinessProcessorSum;
import ${package}.lambda.body.LambdaBodyIn;
import ${package}.lambda.body.LambdaBodyOut;
import ${package}.lambda.pipeline.PrepareBusinessParameters;
import ${package}.lambda.pipeline.PrepareResponseBody;

public class SumLambda implements APIGatewayProxyLambda {

	public static APIGatewayProxyRequestEventDecoder<LambdaBodyIn> requestDecoder = new APIGatewayProxyRequestEventDecoder<LambdaBodyIn>(LambdaBodyIn.class);
	public static PrepareBusinessParameters prepareBusiness = new PrepareBusinessParameters();
	public static BusinessProcessorSum bodyProcessorSum = new BusinessProcessorSum();
	public static PrepareResponseBody prepareResponse = new PrepareResponseBody();
	public static APIGatewayProxyResponseEventEncoder<LambdaBodyOut> responseEncoder = new APIGatewayProxyResponseEventEncoder<LambdaBodyOut>(LambdaBodyOut.class);

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
		LambdaChannel<APIGatewayProxyRequestEvent> channel = new LambdaChannel<APIGatewayProxyRequestEvent>(input, context);
		try {
			channel.pipeline().addFirst(requestDecoder, prepareBusiness, bodyProcessorSum, responseEncoder, prepareResponse);
			channel.writeInbound(input);
			return channel.readOutbound();
		}
		finally {
			channel.close();
		}
	}

}
