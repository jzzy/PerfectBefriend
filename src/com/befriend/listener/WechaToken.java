package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.wechat.RefreshAccessToken;
public class WechaToken  implements ServletContextListener{
	public final static int Second=518400;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
			new RefreshAccessToken(Second);
			
			
		
	}


}
