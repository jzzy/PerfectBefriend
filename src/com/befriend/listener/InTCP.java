package com.befriend.listener;


import java.net.ServerSocket;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.befriend.Thread.TCPThread;

public class InTCP implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�ر�ִ�е�");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// ����TCP���� 6666 ���socket�����Ǽ���
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("TCP Start up port:6666");
			//����TCP�����ͻ����߳�
			new Thread(new TCPThread(ss)).start();
			
		} catch (Exception e) {
			System.out.println("TCP�����쳣"+e.getMessage());
		}
	}


}
