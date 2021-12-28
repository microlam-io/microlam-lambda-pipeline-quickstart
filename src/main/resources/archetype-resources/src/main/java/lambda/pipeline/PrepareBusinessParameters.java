package ${package}.lambda.pipeline;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.microlam.example.lambda.body.LambdaBodyIn;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;

@Sharable
public class PrepareBusinessParameters  extends MessageToMessageDecoder<LambdaBodyIn> {

	private static Logger LOGGER = LoggerFactory.getLogger(PrepareBusinessParameters.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, LambdaBodyIn msg, List<Object> out) throws Exception {
		LOGGER.debug("Entering PrepareBusinessParameters.decode(): {}", msg);
		out.add(msg.arguments);
		LOGGER.debug("Exiting PrepareBusinessParameters.decode(): {}", msg.arguments);
	}


}
