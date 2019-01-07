package com.lms.ctaa.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gov.customs.casp.sdk.h4a.entity.ObjectsDetail;
import cn.gov.customs.casp.sdk.h4a.entity.Roles;
import cn.gov.customs.casp.sdk.h4a.entity.RolesOfUser;
import cn.gov.customs.casp.sdk.h4a.sso.passport.PassportFilter;

import com.lms.common.H4AHelper;
import com.lms.common.getLoginInfo;
import com.lms.ctaa.pojo.Customs;
import com.lms.ctaa.pojo.Maintenance;
import com.lms.ctaa.service.CustomsService;
import com.lms.ctaa.service.MaintenanceService;

/**
 * 用户登录校验和退出登录
 * @author xueyankai
 *
 */
@Controller
@RequestMapping("login")
public class LoginAction {
	
	private String userName;
	private String password;
	@Autowired
	private CustomsService customsservice;
	
	@Autowired
	private MaintenanceService maintenanceservice;

	private Map<String,String> map=new HashMap<String,String>();

	//用户退出
	@RequestMapping(value = {"/logout"})
	@ResponseBody//用于与方法上返回结果直接写入到HTTP response body用于异步获取数据AJAX json数据
	public Map<String,String> logout(HttpServletRequest request){
		Map<String,String> map=new HashMap<String,String>();
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		Enumeration<String> enumeration = session.getAttributeNames();
		while(enumeration.hasMoreElements()){
			session.removeAttribute(enumeration.nextElement());		
		}
		 String path = request.getContextPath();
		 String index="/login/index";
		String basePath = request.getScheme() + "://" + request.getServerName() + 
			      ":" + request.getServerPort() + path;
	    String url = PassportFilter.logOffUrl + "?ru=" + basePath + 
	    		index+ "&lar=" + 
	      PassportFilter.isDirectLogOff;
		//String url=PassportManager.getLogoutUrl(request);
		map.put("url", url);
		return map;
	}
	
	//用户三统一登录
	@RequestMapping(value = {"/index"})
	public String index(HttpServletRequest req, HttpSession session,Model model){
		ObjectsDetail userDetail = getLoginInfo.getLoginInformation(req);
		if(userDetail==null||userDetail.equals("")){
			return "error";
		}
		session.setAttribute("user_parent_guid", userDetail.getParent_guid());
		session.setAttribute("username", userDetail.getDisplay_name());	
		String all_path_name=userDetail.getAll_path_name();

		String [] names= StringUtils.split(all_path_name, "\\");
		if(names.length>1){
			session.setAttribute("path_name", names[1]);
		}
		session.setAttribute("user_id", userDetail.getUser_guid());
		session.setAttribute("pathName", names[0]+"\\"+names[1]);

		String str=	new getLoginInfo().GetParentInfo(names[0]+"\\"+names[1]);
		String customsCode=str.split("@")[0];
		String orgGuid=str.split("@")[1];
		session.setAttribute("customsCode", customsCode);
		session.setAttribute("orgGuid", orgGuid);
		System.out.println(userDetail.getParent_guid()+":Parent_guid");
		System.out.println(all_path_name+":all_path_name");
		return "forward:/maintenance/list";
	}
		
}
