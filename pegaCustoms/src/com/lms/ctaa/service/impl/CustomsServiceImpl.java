package com.lms.ctaa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.lms.common.getLoginInfo;
import com.lms.ctaa.dao.CustomsDao;
import com.lms.ctaa.pojo.Customs;
import com.lms.ctaa.service.CustomsService;

public class CustomsServiceImpl implements CustomsService{
	
	@Autowired
	private CustomsDao customsdao;

	@Override
	public List<Customs> select() {
		return customsdao.select();
	}
	
	@Override
	public Customs selectById(String id) {
		return customsdao.selectById(id);
	}
	
	@Override
	public int saveCustoms(List<Customs> list){
		
		return customsdao.saveCustoms(list);
	}
	
	@Override
	public Customs getCustomsName(String CustomsCode){
		
		return customsdao.getCustomsName(CustomsCode);
	}
	
	@Override
	public List<Customs> selectByCustomsCode(String CustomsCode){
		
		return customsdao.selectByCustomsCode(CustomsCode);
	}
	
	@Override
	public int deleteAllData(){
		
		return customsdao.deleteAllData();
	}
	@Scheduled(cron = "36 0 0 1 * ?")//每月1号0点0分36秒触发
	public void CronCustomsData(){
        System.out.println("定时任务开启START*********************************");
		int count =customsdao.deleteAllData();
		System.out.println("清空表数据:"+count+"条");
        int j=0;
		getLoginInfo getlogininfo= new getLoginInfo();
        List<Customs> list = getlogininfo.GetAllInfo(getlogininfo.getRootDept().getOrg_guid()); 

        for(Customs c:list){
     	   List<Customs> listc= getlogininfo.GetAllInfo(c.getOrg_guid());
     	   j +=customsdao.saveCustoms(listc);
     	  System.out.println("保存表数据:"+j+"条");
        }              
        System.out.println("定时任务完成END*********************************");
	}
	
}
