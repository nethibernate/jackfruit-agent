package com.jackfruit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class NettyClientIoHandler extends ChannelInboundHandlerAdapter {
	
	private static AttributeKey<NettyClientSession> CLIENT_SESSION = 
			new AttributeKey<NettyClientSession>("ClientSession");
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		long sessionId = NetService.INS.buildSessionCount();
		NettyClientSession session = new NettyClientSession(ctx, sessionId);
		ctx.attr(CLIENT_SESSION).set(session);
		
		// TODO 是否放在此处不确定
		NetService.INS.addSession(session);
	}
	
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyClientSession session = ctx.attr(CLIENT_SESSION).get();
		if(session == null) {
			return;
		}
		ctx.attr(CLIENT_SESSION).set(null);
		
		// TODO 与channelActive中对应
		NetService.INS.removeSession(session);
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		NettyClientSession session = ctx.attr(CLIENT_SESSION).get();
		if(session == null) {
			return;
		}
		if(!(obj instanceof byte[])) {
			return;
		}
		byte[] msg = (byte[])obj;
		// TODO
	}

}
