package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.udp.UDPServer;

public class InUDP implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�ر�ִ�е�");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.out.println("UDP Start up port:6500");
			UDPServer.Startup();
		} catch (Exception e) {
			System.out.println("UDP�����쳣"+e.getMessage());
		}
	}


}
