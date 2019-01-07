package com.lms.common;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class RoleDistributionSingleton implements ApplicationContextAware{
	
	private RoleDistributionSingleton(){};
	private Map<String,String> RoleDistributionMap;
	private Map<String,String> RoleDistributionNameMap;
	private static ApplicationContext applicationcontext;
	private static RoleDistributionSingleton customssingleton=new RoleDistributionSingleton() ;
	public static RoleDistributionSingleton getOnstance(){
		return customssingleton;
	}
	public Map<String, String> getRoleDistributionMap() {
		return RoleDistributionMap;
	}
	public void setRoleDistributionMap(Map<String, String> RoleDistributionMap) {
		this.RoleDistributionMap = RoleDistributionMap;
	}
	public Map<String, String> getRoleDistributionNameMap() {
		return RoleDistributionNameMap;
	}
	public void setRoleDistributionNameMap(
			Map<String, String> roleDistributionNameMap) {
		RoleDistributionNameMap = roleDistributionNameMap;
	}
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		RoleDistributionSingleton.applicationcontext=arg0;
	}
	public static ApplicationContext getApplicationContext(){
		return applicationcontext;
	}
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	public static <T> T getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name,clazz);
	}
}
