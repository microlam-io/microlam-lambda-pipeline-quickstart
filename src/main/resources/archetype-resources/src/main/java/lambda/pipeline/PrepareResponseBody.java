package ${package}.lambda.pipeline;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandler.Sharable;
import ${package}.lambda.body.LambdaBodyOut;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

@Sharable
public class PrepareResponseBody  extends MessageToMessageEncoder<Integer> {

	private static Logger LOGGER = LoggerFactory.getLogger(PrepareResponseBody.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
		LOGGER.debug("Entering PrepareResponseBody.encode()");
		out.add(new LambdaBodyOut(msg));
	}

}
