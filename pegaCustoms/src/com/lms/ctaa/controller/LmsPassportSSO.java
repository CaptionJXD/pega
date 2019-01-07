package com.lms.ctaa.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.lms.common.getLoginInfo;
import com.lms.ctaa.util.StringUtil;

import cn.gov.customs.casp.sdk.h4a.entity.ObjectsDetail;
import cn.gov.customs.casp.sdk.h4a.sso.IPassportSSO;
import cn.gov.customs.casp.sdk.h4a.sso.passport.Ticket;

@Controller
public class LmsPassportSSO implements IPassportSSO {

	public void LogOut(HttpServletRequest request, HttpServletResponse response) {

		// 完善登出功能

		HttpSession session = request.getSession();

		Enumeration<String> enumeration = session.getAttributeNames();
		while(enumeration.hasMoreElements()){
			session.removeAttribute(enumeration.nextElement());		
		}
		
	}

	public void Login(Ticket ticket, HttpServletRequest request, HttpServletResponse response, String arg3) {

		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username") ;
		if (StringUtil.isNotEmpty(username)) {

			ObjectsDetail userDetail = getLoginInfo.getLoginInformation(request);
			//session.setAttribute("user", userDetail);
			session.setAttribute("username", userDetail.getDisplay_name());
	/*		SysLogin sysLogin = new SysLogin();
			//SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date loginDate=new Date();
			try {
				//loginDate = sdf.parse(ticket.getSit());
				String logindt=DateUtil.getDateFormatStr(new Date(), "yyyy/MM/dd HH:mm:ss");
				loginDate=DateUtil.stringToDate(logindt,"yyyy/MM/dd HH:mm:ss");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sysLogin.setLoginTime(loginDate.toString());
			sysLogin.setGuid(userDetail.getUser_guid().toString());

			SysLoginService service = SpringContextHolder.getBean(SysLoginService.class);

			boolean isLoginLog = service.existsSysLogin(sysLogin);
			if (isLoginLog) {
				sysLogin.setId(UUID.randomUUID().toString());
				service.saveSysLogin(sysLogin);
			}*/

		}

	}
}
