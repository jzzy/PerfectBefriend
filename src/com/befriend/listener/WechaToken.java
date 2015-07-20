package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.wechat.RefreshAccessToken;
public class WechaToken  implements ServletContextListener{
	public static int Second=518400;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("关闭WechaTolen");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			/**
			 * 开始 向环信 获取 token 518400秒  6day
			 */
			System.out.println("开始WechaTolen....");
			new RefreshAccessToken(Second);
			System.out.println("每隔"+Second+"秒 执行一次....");
			
		
	}


}
