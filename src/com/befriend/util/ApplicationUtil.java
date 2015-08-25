package com.befriend.util;



import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class  ApplicationUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationUtil.applicationContext = applicationContext;
	}
	/**
	 * 	gyn 2015/8/24
	 *  在java普通类 中调用dao方法
	 *  UserDAO udao=(UserDAO) ApplicationUtil.getBean("userDAO");
		NewsDAO ndao=(NewsDAO) ApplicationUtil.getBean("newsDAO");
		User u=udao.byAccnumnoIdMax();
		List<News> nl=ndao.maxNewsId();
		
		if(u!=null&&nl.size()>0){
			System.out.println("ApplicationUtil:"+u.getAccnumno()+" n "+nl.get(0).getId());
		}else{
			System.out.println("ApplicationUtil:null");
		}
	 * @param dao name
	 * @return
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
