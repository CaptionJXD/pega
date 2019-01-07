package com.lms.common;

import java.util.ArrayList;
import java.util.List;


import cn.gov.customs.casp.config.ConfigReader;
import cn.gov.customs.casp.sdk.h4a.BeanReaderHelper;
import cn.gov.customs.casp.sdk.h4a.accredit.ws.OrganizationCategory;
import cn.gov.customs.casp.sdk.h4a.accredit.ws.UserCategory;
import cn.gov.customs.casp.sdk.h4a.entity.Roles;
import cn.gov.customs.casp.sdk.h4a.entity.RolesOfUser;
import cn.gov.customs.casp.sdk.h4a.enumdefines.AccreditCategory;
import cn.gov.customs.casp.sdk.h4a.enumdefines.DelegationCategories;
import cn.gov.customs.casp.sdk.h4a.enumdefines.RoleCategories;
import cn.gov.customs.casp.sdk.h4a.passport.IAccreditBeanReader;

public class H4AHelper {

	private static String viewCode = ConfigReader.getConfigValue("h4a-config.xml", "h4a_default", "wcf_default",
			"default_BaseView");

	private static String appCode = ConfigReader.getConfigValue("h4a-config.xml", "h4a_default", "wcf_default",
			"default_Application_name");

	public static List<Roles> GetAppRoles() {

		List<Roles> list = new ArrayList<Roles>();

		try {

			IAccreditBeanReader iAccreditBeanReader = BeanReaderHelper.getIAccreditBeanReader();

			Roles[] roles = iAccreditBeanReader.getBeanRoles("", AccreditCategory.None, appCode, AccreditCategory.Code,
					viewCode, AccreditCategory.Code, RoleCategories.All, "");

			if (roles.length > 0) {
				for (Roles role : roles) {
					list.add(role);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public static List<String> GetUserRoles(String userGuid) {

		List<String> roleCodes = new ArrayList<String>();

		try {

			IAccreditBeanReader iAccreditBeanReader = BeanReaderHelper.getIAccreditBeanReader();

			RolesOfUser[] result = iAccreditBeanReader.getBeanRolesOfUser(userGuid, UserCategory.USER_GUID, "",
					OrganizationCategory.NONE, appCode, AccreditCategory.Code, viewCode, AccreditCategory.Code,
					RoleCategories.All, DelegationCategories.All, "");

			if (result.length > 0) {
				for (RolesOfUser rolesOfUser : result) {
					roleCodes.add(rolesOfUser.getCode_name());
				}
			}

		} catch (Exception e) {
			String mString = e.getMessage();
			System.out.println(mString);
			// TODO: handle exception
		}

		return roleCodes;
	}
	
	public static List<RolesOfUser> GetUserRolesName(String userGuid) {

		List<RolesOfUser> roleCodes = new ArrayList<RolesOfUser>();

		try {

			IAccreditBeanReader iAccreditBeanReader = BeanReaderHelper.getIAccreditBeanReader();

			RolesOfUser[] result = iAccreditBeanReader.getBeanRolesOfUser(userGuid, UserCategory.USER_GUID, "",
					OrganizationCategory.NONE, appCode, AccreditCategory.Code, viewCode, AccreditCategory.Code,
					RoleCategories.All, DelegationCategories.All, "");

			if (result.length > 0) {
				for (RolesOfUser rolesOfUser : result) {
					roleCodes.add(rolesOfUser);
				}
			}

		} catch (Exception e) {
			String mString = e.getMessage();
			System.out.println(mString);
			// TODO: handle exception
		}

		return roleCodes;
	}

}
