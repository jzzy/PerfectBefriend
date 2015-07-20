package com.befriend.Thread;

import com.befriend.entity.User;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;

public class ModifyThreads implements Runnable {
	public User u = null;
	private static String ip = null;
	private static String url = null;
	private static int port = 0;

	public ModifyThreads(User u, String ip, int port) {
		super();
		this.u = u;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			
			//�����û�״̬
			url = "http://127.0.0.1/Befriend/uIpPort?id="
					+ u.getId() + "&ip=" + ip + "&port=" + port + "";
			System.out.println("udp:"+url);
			WechatKit.sendGet(url);
			Thread.sleep(1000);
			//����δ����Ϣ
			url = "http://127.0.0.1/Befriend/onlineUserSend?id="
					+ u.getId()+"";
			WechatKit.sendGet(url);
		} catch (Exception e) {
			System.out.println("udp:"+url);
			System.out.println("ModifyThreads  �����û�ip port�쳣��"+e.getMessage());
		}

	}

}
