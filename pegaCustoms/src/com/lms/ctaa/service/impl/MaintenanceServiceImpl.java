package com.lms.ctaa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.ctaa.dao.MaintenanceDao;
import com.lms.ctaa.dao.RolecastcodeDao;
import com.lms.ctaa.pojo.Maintenance;
import com.lms.ctaa.pojo.Rolecastcode;
import com.lms.ctaa.service.MaintenanceService;
import com.lms.ctaa.util.PageUtil;

public class MaintenanceServiceImpl implements MaintenanceService{

	@Autowired
	private MaintenanceDao maintenancedao;
	@Autowired
	private RolecastcodeDao rolecastcodedao;
	@Override
	public List<Maintenance> selectMaintenance(PageUtil<Maintenance> maintenance) {
		return maintenancedao.selectMaintenance(maintenance);
	}

	@Override
	public Maintenance selectMaintenanceById(String id) {
		return maintenancedao.selectMaintenanceById(id);
	}

	@Override
	public int maintenanceDelete(String id) {
		return maintenancedao.maintenanceDelete(id);
	}

	@Override
	@Transactional
	public int maintenanceSaveRole(List<Maintenance> mlist,List<Rolecastcode> rlist){
		 int i = maintenancedao.maintenanceSave(mlist);
		 int j=0;
		 if(i>0){
			  j = rolecastcodedao.RolecastcodeSave(rlist);
		 }
		 return i+j;
	}
	@Transactional
	public int maintenanceDeleteRole(String id,List<Rolecastcode> rlist){
		int j=0;
		int i=maintenancedao.maintenanceDelete(id);
		if(i>0){
		 j=rolecastcodedao.RolecastcodeDelete(rlist);
		}
		return i+j;
	}
	public int maintenanceSave(List<Maintenance> mlist){
		return maintenancedao.maintenanceSave(mlist);
	}
	@Override
	public int selectCount(){
		return maintenancedao.selectCount();
	}
	@Override
	public int selectCountCondition(Maintenance maintenance){
		return maintenancedao.selectCountCondition(maintenance);
	}
	@Override
	public Maintenance selectInfo(Maintenance maintenance){	
		return maintenancedao.selectInfo(maintenance);
	}
	@Override
	public int RolecastcodeSave(List<Rolecastcode> list){
		return maintenancedao.RolecastcodeSave(list);

	}

	@Override
	public List<Maintenance> queryMaintenance() {
		// TODO Auto-generated method stub
		return maintenancedao.queryMaintenance();
	}
}
