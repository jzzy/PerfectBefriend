package com.befriend.listener;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.befriend.Thread.StasTime;
import com.befriend.Thread.TCPThread;
import com.befriend.config.ServerConfig;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.News;
import com.befriend.entity.User;
import com.befriend.util.ApplicationUtil;

/**
 * Application Lifecycle Listener implementation class InitListener
 * 
 */
@WebListener
public class InitListener implements ServletContextListener
{

	private static final Log logger = LogFactory.getLog(InitListener.class);
	/**
	 * Default constructor.
	 */
	public InitListener()
	{
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)
	{
		
		new Thread(new StasTime()).start();
		
		ServerConfig.getServerConfig().initialize(event);
		logger.debug(ServerConfig.getServerConfig().getRealPath() + "\n"
				+ ServerConfig.getServerConfig().getContextpath());
		logger.debug("RealPath:" + event.getServletContext().getRealPath("/"));
		logger.debug("Contextpath:"
				+ event.getServletContext().getContextPath());
		 // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message.");  
       
				
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

}
