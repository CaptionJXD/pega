package com.lms.ctaa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.ctaa.dao.RolecastcodeDao;
import com.lms.ctaa.pojo.Customs;
import com.lms.ctaa.pojo.Rolecastcode;
import com.lms.ctaa.service.RolecastcodeService;

public class RolecastcodeServiceImpl implements RolecastcodeService{
	
	@Autowired
	private RolecastcodeDao rolecastcodedao;

	@Override
	public  int  RolecastcodeSave(List<Rolecastcode> list){		
		return rolecastcodedao.RolecastcodeSave(list);
	}
	@Override
	public  Rolecastcode  selectRolecastcode(Rolecastcode bean){	
		return rolecastcodedao.selectRolecastcode(bean);
	}
	@Override
	public  Rolecastcode  isRolecastcode(Rolecastcode bean){	
		return rolecastcodedao.isRolecastcode(bean);
	}
	@Override
	public  int  RolecastcodeDelete(List<Rolecastcode> list){
		return rolecastcodedao.RolecastcodeDelete(list);
	}

}
