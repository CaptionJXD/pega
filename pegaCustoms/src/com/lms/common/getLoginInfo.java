package com.lms.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.gov.customs.casp.sdk.h4a.BeanReaderHelper;
import cn.gov.customs.casp.sdk.h4a.OguBeanReaderHelper;
import cn.gov.customs.casp.sdk.h4a.RegisterBeanAppReaderHelper;
import cn.gov.customs.casp.sdk.h4a.entity.DeptInfo;
import cn.gov.customs.casp.sdk.h4a.entity.ObjectParentOrganizations;
import cn.gov.customs.casp.sdk.h4a.entity.ObjectsDetail;
import cn.gov.customs.casp.sdk.h4a.entity.OrganizationChildren;
import cn.gov.customs.casp.sdk.h4a.entity.RootDepartment;
import cn.gov.customs.casp.sdk.h4a.enumdefines.ListObjectCategories;
import cn.gov.customs.casp.sdk.h4a.enumdefines.ObjectStatusCategories;
import cn.gov.customs.casp.sdk.h4a.enumdefines.ViewCategory;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.IOguReaderGetObjectParentOrganizationsCupaaFaultArgsFaultFaultMessage;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.IOguReaderGetObjectsDetailCupaaFaultArgsFaultFaultMessage;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.IOguReaderGetOrganizationChildrenCupaaFaultArgsFaultFaultMessage;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.IOguReaderGetRootDepartmentCupaaFaultArgsFaultFaultMessage;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.ObjectCategory;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.OrganizationCategory;
import cn.gov.customs.casp.sdk.h4a.passport.IOguBeanReader;
import cn.gov.customs.casp.sdk.h4a.register.ws.OrgCategory;
import cn.gov.customs.casp.sdk.h4a.sso.passport.Ticket;

import com.lms.ctaa.pojo.Customs;
import com.lms.ctaa.util.PropertiesUtil;

/**
 * 获取用户信息
 * 
 * @author
 *
 */
public class getLoginInfo {
	public static String CUSTOMSCODE = PropertiesUtil.getInstance()
			.getPropertyValue("startworkMessage.properties", "customsCode");
	public static String viewCode = PropertiesUtil.getInstance()
			.getPropertyValue("startworkMessage.properties", "viewCode");

	/**
	 * 获取登录用户信息
	 */
	public static ObjectsDetail getLoginInformation(HttpServletRequest req) {
		HttpSession session = req.getSession();
		cn.gov.customs.casp.sdk.h4a.sso.passport.Ticket ticket = (Ticket) session
				.getAttribute("TICKET");
		String loginName = ticket.getLn();// 用户名
		String authentionMode = ticket.getAbm();// 验证类型
		IOguBeanReader iOguBeanReader = BeanReaderHelper.getIOguBeanReader();
		ObjectsDetail userDetail = null;
		ObjectsDetail[] objectsDetail = null;
		try {
			objectsDetail = iOguBeanReader
					.getBeanObjectsDetail(
							"BASE_VIEW",
							ViewCategory.ViewCode,
							loginName + "&" + authentionMode,
							cn.gov.customs.casp.sdk.h4a.ogu.ws.ObjectCategory.USER_IDENTITY,
							"",
							cn.gov.customs.casp.sdk.h4a.ogu.ws.OrganizationCategory.NONE,
							"");

			if (objectsDetail != null) {// 拿到用户信息最短的那一个
				int num = 20;
				for (int i = 0; i < objectsDetail.length; i++) {
					if (getNum(objectsDetail[i].getAll_path_name(), "\\") < num) {
						userDetail = objectsDetail[i];
						num = getNum(objectsDetail[i].getAll_path_name(), "\\");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetail;
	}

	/**
	 * 获取登录用户口岸信息
	 * 
	 * @throws IOguReaderGetObjectsDetailCupaaFaultArgsFaultFaultMessage
	 */
	public static ObjectsDetail getLoginOrg(String parent_guid)
			throws IOguReaderGetObjectsDetailCupaaFaultArgsFaultFaultMessage {
		ObjectsDetail deptDetal = null;
		ObjectsDetail[] deptDetails = null;
		IOguBeanReader iOguBeanReader = BeanReaderHelper.getIOguBeanReader();
		deptDetails = iOguBeanReader.getBeanObjectsDetail(viewCode,
				ViewCategory.ViewCode, parent_guid,
				cn.gov.customs.casp.sdk.h4a.ogu.ws.ObjectCategory.ORG_GUID, "",
				cn.gov.customs.casp.sdk.h4a.ogu.ws.OrganizationCategory.NONE,
				"");
		if (deptDetails != null) {
			deptDetal = deptDetails[0];
		}
		return deptDetal;
	}

	/**
	 * 以下为辅助方法，非业务方法
	 */
	/**
	 * 计算字符串中包含特定字符的数量,用于计算用户全路径中最短的那个
	 * 
	 * @param str
	 * @param sChar
	 * @return num
	 */
	public static int getNum(String str, String sChar) {
		char[] arr = str.toCharArray();
		int num = 0;
		for (int i = 0; i < arr.length; i++) {
			if (sChar.equals(String.valueOf(arr[i]))) {
				num++;
			}
		}
		return num;
	}

	/**
	 * 通过部门ID获取部门信息
	 * 
	 * @param org_id
	 */
	public static DeptInfo[] getBeanDeptInfo(String org_id) {
		DeptInfo[] deptArray = null;
		try {
			RegisterBeanAppReaderHelper helper = new RegisterBeanAppReaderHelper();
			deptArray = helper.getBeanDeptInfo(OrgCategory.ORG_ID, org_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptArray;
	}

	/**
	 * 获取指定对象的父机构对象
	 * 
	 * @param pathname
	 * @return
	 */
	public static ObjectParentOrganizations[] getBeanObjectParentOrganizations(
			String pathname) {
		OguBeanReaderHelper helper = new OguBeanReaderHelper();
		ObjectParentOrganizations[] parentOrg = null;
		try {
			parentOrg = helper
					.getBeanObjectParentOrganizations(viewCode,
							ViewCategory.ViewCode, pathname,
							ObjectCategory.ORG_ALL_PATH_NAME, "",
							OrganizationCategory.ORG_GLOBAL_SORT, false, false,
							"",
							"ALL_PATH_NAME,ORG_CLASS,ORG_TYPE,CUSTOMS_CODE,ORG_GUID,PARENT_GUID");
		} catch (IOguReaderGetObjectParentOrganizationsCupaaFaultArgsFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parentOrg;

	}

	
	//
	
	// 获取关区数据
	public List<Customs> GetAllInfo(String orgGuid) {
		List<Customs> list = new ArrayList<Customs>();
		try {
			OrganizationChildren[] p = new OguBeanReaderHelper()
					.getBeanOrganizationChildren(
							"BASE_VIEW",
							ViewCategory.ViewCode,
							orgGuid,// 海关总署\\总署机关
							OrganizationCategory.ORG_GUID,
							ListObjectCategories.Organizations,
							ObjectStatusCategories.Common, 1, "", "", "", 0,
							"OBJ_NAME,GLOBLA_SORT,CUSTOMS_CODE,RANK_NAME");
			for (OrganizationChildren a : p) {	
				if (null != a.getCustoms_code()&&"海关".equals(a.getObj_name().substring(a.getObj_name().length()-2, a.getObj_name().length()))) {
					Customs c = new Customs();
					c.setId(getOrderByUUID());
					c.setCustomsName(a.getObj_name());
					c.setSuoeriorSustoms(a.getCustoms_code());
					c.setOrg_guid(a.getOrg_guid());
					list.add(c);
				}

			}
		} catch (IOguReaderGetOrganizationChildrenCupaaFaultArgsFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

		// ----------以下代码不要乱删！！！ -----------
		// QueryOguByCondition[] p1=iOguBeanReader.getBeanQueryOguByCondition(
		// "BASE_VIEW",ViewCategory.ViewCode, "海关总署",
		// OrganizationCategory.ORG_ALL_PATH_NAME, "admin", false,
		// false, "", "", 0, ListObjectCategories.All,
		// "OBJ_NAME,GLOBLA_SORT,CUSTOMS_CODE,RANK_NAME");
		// System.out.println(p1.length+"lenght");
		// for(QueryOguByCondition a:p1){
		// System.out.println(a.getAll_path_name());
		// System.out.println(a.getCustoms_code()+"getCustoms_code");
		// System.out.println(a.getObj_name()+"getObj_name");
		//
		// }

		// DirectCustoms[] xml=iOguBeanReader.getBeanDirectCustoms("BASE_VIEW",
		// ViewCategory.ViewCode, "海关总署\\南京海关\\办公室\\秘书科",
		// ObjectCategory.ORG_ALL_PATH_NAME, "",
		// OrganizationCategory.NONE, "CUSTOMS_CODE");
		// System.out.println(xml+"**************************8");
	}
	//获取根节点数据
	public RootDepartment  getRootDept(){
		IOguBeanReader iOguBeanReader = BeanReaderHelper.getIOguBeanReader();
		RootDepartment[] rp=null;
		try {
			 rp= iOguBeanReader.getBeanRootDepartment("BASE_VIEW", ViewCategory.ViewCode, "OBJ_NAME,GLOBLA_SORT,CUSTOMS_CODE,RANK_NAME");
			
		} catch (IOguReaderGetRootDepartmentCupaaFaultArgsFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rp[0];
	}

	// 获取关区代码依据路径
	public String GetParentInfo(String pathName) {
		OrganizationChildren[] p = null;
		try {
			p = new OguBeanReaderHelper().getBeanOrganizationChildren(
					"BASE_VIEW",
					ViewCategory.ViewCode,
					pathName,// 海关总署\\总署机关
					OrganizationCategory.ORG_ALL_PATH_NAME,
					ListObjectCategories.All, ObjectStatusCategories.Common, 0,
					"", "", "", 0, "OBJ_NAME,GLOBLA_SORT,CUSTOMS_CODE");
		} catch (IOguReaderGetOrganizationChildrenCupaaFaultArgsFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=p[0].getCustoms_code()+"@"+p[0].getOrg_guid();
		return str;
	}

	public Integer getOrderByUUID() {
		int a = 1;
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0)
			hashCode = -hashCode;
		return a + hashCode;
	}

}
