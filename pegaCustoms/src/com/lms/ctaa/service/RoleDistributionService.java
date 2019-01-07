package com.lms.ctaa.service;


import java.util.List;

import com.lms.ctaa.pojo.RoleDistribution;

public interface RoleDistributionService extends BaseService<RoleDistribution> {

	public  RoleDistribution selectByRole(String role);
	public  List<RoleDistribution> selectAll();
	public  RoleDistribution selectBySpell(String spell);
}
