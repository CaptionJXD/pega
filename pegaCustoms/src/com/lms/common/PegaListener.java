package com.lms.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.lms.ctaa.pojo.RoleDistribution;
import com.lms.ctaa.service.RoleDistributionService;

public class PegaListener implements ServletContextListener {

	
	   private final Logger log = LoggerFactory.getLogger(PegaListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("Roledistribution-Info LOADtoMap中 START--------------");
		ApplicationContext appCtx=RoleDistributionSingleton.getApplicationContext();
		RoleDistributionService  roledistributionservice=(RoleDistributionService) appCtx.getBean(RoleDistributionService.class);
		List<RoleDistribution> list=roledistributionservice.selectAll();
		Map<String,String> map=new HashMap<String,String>();
		Map<String,String> mapName=new HashMap<String,String>();
		for(RoleDistribution p:list){
			map.put(p.getSpell(), p.getDistribution());
			mapName.put(p.getPost(), p.getDistribution());
		}
		RoleDistributionSingleton.getOnstance().setRoleDistributionMap(map);
		RoleDistributionSingleton.getOnstance().setRoleDistributionNameMap(mapName);
		log.info("Roledistribution-Info LOADtoMap中 OVER--------------");
	}
	

}
