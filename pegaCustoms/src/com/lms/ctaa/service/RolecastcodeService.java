package com.lms.ctaa.service;

import java.util.List;

import com.lms.ctaa.pojo.Rolecastcode;

public interface RolecastcodeService extends BaseService<Rolecastcode>{

	/**
	 * 查询
	 * @param t
	 * @return
	 */
	public  int  RolecastcodeSave(List<Rolecastcode> list);
	public  Rolecastcode  selectRolecastcode(Rolecastcode bean);
	public  Rolecastcode  isRolecastcode(Rolecastcode bean);
	public  int  RolecastcodeDelete(List<Rolecastcode> list);
}
