package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.wechat.RefreshAccessToken;
public class WechaToken  implements ServletContextListener{
	public static int Second=518400;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�ر�WechaTolen");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			/**
			 * ��ʼ ���� ��ȡ token 518400��  6day
			 */
			System.out.println("��ʼWechaTolen....");
			new RefreshAccessToken(Second);
			System.out.println("ÿ��"+Second+"�� ִ��һ��....");
			
		
	}


}
