package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.udp.UDPServer;

public class InUDP implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.out.println("UDP Start up port:6500");
			UDPServer.Startup();
		} catch (Exception e) {
			
		}
	}


}
