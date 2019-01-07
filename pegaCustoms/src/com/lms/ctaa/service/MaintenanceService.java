package com.lms.ctaa.service;

import java.util.List;

import com.lms.ctaa.pojo.Maintenance;
import com.lms.ctaa.pojo.Rolecastcode;
import com.lms.ctaa.util.PageUtil;


public interface MaintenanceService extends BaseService<Maintenance>{

	public List<Maintenance> selectMaintenance(PageUtil<Maintenance> maintenance);
	public Maintenance selectMaintenanceById(String id);
	public int maintenanceDelete(String id);
	public int maintenanceSave(List<Maintenance> mlist);
	public int maintenanceSaveRole(List<Maintenance> mlist,List<Rolecastcode> rlist);
	public int selectCount();
	public int selectCountCondition(Maintenance maintenance);
	public Maintenance selectInfo(Maintenance maintenance);
	public int RolecastcodeSave(List<Rolecastcode> list);
	public int maintenanceDeleteRole(String id,List<Rolecastcode> rlist);
	public List<Maintenance> queryMaintenance();
}
