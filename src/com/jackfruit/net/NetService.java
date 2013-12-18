package com.jackfruit.net;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public enum NetService {

	INS;

//	/** 玩家-下一级网关的映射 */
//	public Map<ISession, ServerMember> sessionServerMap = new ConcurrentHashMap<ISession, ServerMember>();
	/** ID-玩家的映射 */
	private Map<Long, ISession> idSessionMap = new ConcurrentHashMap<Long, ISession>();

	/** session计数 */
	private AtomicLong sessionCount = new AtomicLong(0); 

	public synchronized long buildSessionCount() {
		do {
			sessionCount.incrementAndGet();
			if(sessionCount.get() <= 0) {
				sessionCount.set(1);
			}
		} while(idSessionMap.containsKey(sessionCount.get()));

		return sessionCount.get();
	}


	public ISession getSession(long sessionId) {
		return idSessionMap.get(sessionId);
	}
	
	public void addSession(ISession session) {
		idSessionMap.put(session.getId(), session);
	}
	
	public void removeSession(ISession session) {
		if(idSessionMap.containsKey(session.getId())) {
			idSessionMap.remove(session.getId());
		}
	}

//	public void redirectRouting(int sessionId, ServerMember serverMember) {
//		IoSession session = idSessionMap.get(sessionId);
//		if(session == null) {
//			return;
//		}
//		sessionServerMap.put(session, serverMember);
//	}
//
//	public RoutingMember getRouingMember(IoSession session) {
//		ServerMember serverMember = sessionServerMap.get(session);
//		if(serverMember == null) {
//			return defaultRoutingMember;
//		}
//		return serverRoutingMap.get(serverMember);
//	}

}
