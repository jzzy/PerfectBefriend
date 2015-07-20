package com.befriend.listener;


import java.net.ServerSocket;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.befriend.Thread.TCPThread;

public class InTCP implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("关闭执行的");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// 建立TCP监听 6666 这个socket仅仅是监听
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("TCP Start up port:6666");
			//创建TCP监听客户端线程
			new Thread(new TCPThread(ss)).start();
			
		} catch (Exception e) {
			System.out.println("TCP监听异常"+e.getMessage());
		}
	}


}
