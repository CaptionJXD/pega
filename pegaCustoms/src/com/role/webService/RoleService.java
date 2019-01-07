package com.role.webService;

import javax.jws.WebService;

@WebService
public interface RoleService {
	public String getRoleInfo(String post,String roleArea);

}
