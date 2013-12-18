package com.jackfruit.net;

import io.netty.channel.ChannelHandlerContext;

public class NettyClientSession implements ISession {

	private ChannelHandlerContext ctx;
	private long sessionId;
	
	public NettyClientSession(ChannelHandlerContext ctx, long sessionId) {
		this.ctx = ctx;
		this.sessionId = sessionId;
	}
	
	public ChannelHandlerContext getCtx() {
		return ctx;
	}
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public long getId() {
		return sessionId;
	}
	
	@Override
	public boolean isConnected() {
		if(ctx != null) {
			return ctx.channel().isActive();
		}
		return false;
	}

	@Override
	public void write(Object msg) {
		if(ctx != null) {
			ctx.writeAndFlush(msg);
		}
	}

}
