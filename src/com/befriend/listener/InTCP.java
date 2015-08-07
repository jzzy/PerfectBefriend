package com.befriend.listener;


import java.net.ServerSocket;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.befriend.Thread.TCPThread;

public class InTCP implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("TCP Start up port:6666");
		
			new Thread(new TCPThread(ss)).start();
			
		} catch (Exception e) {
			
		}
	}


}
