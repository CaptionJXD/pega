package com.lms.ctaa.dao;

import java.util.List;

import com.lms.ctaa.pojo.RoleDistribution;


public interface RoleDistributionDao extends BaseDao<RoleDistribution>{
	
	public  RoleDistribution selectByRole(String role);
	public  List<RoleDistribution> selectAll();
	public  RoleDistribution selectBySpell(String spell);
	
	
}
