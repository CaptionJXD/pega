package com.lms.ctaa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.ctaa.dao.RoleDistributionDao;
import com.lms.ctaa.pojo.RoleDistribution;
import com.lms.ctaa.service.RoleDistributionService;

public class RoleDistributionServiceImpl implements RoleDistributionService{
	
	@Autowired                  
	private RoleDistributionDao roleDistributiondao;

	@Override
	public RoleDistribution selectByRole(String role) {
		return roleDistributiondao.selectByRole(role);
	}
	@Override
	public  List<RoleDistribution> selectAll(){
		return roleDistributiondao.selectAll();
	}
	@Override
	public  RoleDistribution selectBySpell(String spell){
		return roleDistributiondao.selectBySpell(spell);
	}

}
