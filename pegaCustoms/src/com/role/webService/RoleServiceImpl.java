package com.role.webService;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.common.RoleDistributionSingleton;
import com.lms.ctaa.dao.RolecastcodeDao;
import com.lms.ctaa.pojo.Rolecastcode;


@WebService(endpointInterface="com.role.webService.RoleService") 
public class RoleServiceImpl implements RoleService {
	

	@Autowired
	private RolecastcodeDao rolecastcodedao;
	public String getRoleInfo(String spell,String roleAreaCast){
		System.out.println(spell);
		System.out.println(roleAreaCast);
		String post =RoleDistributionSingleton.getOnstance().getRoleDistributionMap().get(spell);
		if(null==post||"".equals(post))return "";
		Rolecastcode r=new Rolecastcode();
		r.setPost(post);
		r.setCastCode(roleAreaCast);
		Rolecastcode rc=rolecastcodedao.selectRolecastcode(r);
		return null==rc?roleAreaCast:rc.getRole();
  }

}
