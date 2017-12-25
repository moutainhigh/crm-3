package com.hefei.agg.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器中Bean实例的工具类，该工具类本身也是由spring容器管理
 * @author wugennong
 *
 */
@Component
public class AppContextHandler implements ApplicationContextAware {

	private static ApplicationContext appContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		appContext = arg0;
	}
	
	public static ApplicationContext getAppContext(){return appContext;}
	
	/**
	 * 根据Bean名称获取Bean的实例
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return appContext.getBean(name);
	}

	/**
	 * 根据Bean的Class获取Bean的实例
	 * @param cls
	 * @return
	 */
	public static Object getBean(Class cls){
		return appContext.getBean(cls);
	}
}
