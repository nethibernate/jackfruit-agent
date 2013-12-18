package com.jackfruit.net;

public interface ISession {
	
	/**
	 * 获取唯一标识ID
	 * @return
	 */
	long getId();
	
	/**
	 * 判断会话是否处于连接状态
	 * @return
	 */
	boolean isConnected();
	
	/**
	 * 写数据
	 * @param msg
	 */
	void write(Object msg);
	
}
