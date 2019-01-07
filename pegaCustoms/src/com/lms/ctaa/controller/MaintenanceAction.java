package com.lms.ctaa.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.lms.common.RoleDistributionSingleton;
import com.lms.common.getLoginInfo;
import com.lms.ctaa.dao.RolecastcodeDao;
import com.lms.ctaa.pojo.Customs;
import com.lms.ctaa.pojo.Maintenance;
import com.lms.ctaa.pojo.Rolecastcode;
import com.lms.ctaa.service.CustomsService;
import com.lms.ctaa.service.MaintenanceService;
import com.lms.ctaa.util.PageUtil;

/**
 * 服务
 * @author jiaxiaodong
 */
@Controller
@RequestMapping("maintenance")
public class MaintenanceAction {
	@Autowired
	private CustomsService customsservice;

	@Autowired
	private MaintenanceService maintenanceservice;
	
	@Autowired
	private RolecastcodeDao rolecastcodedao;

	@RequestMapping(value = { "/get" })
	public void get(@RequestParam String str, HttpServletResponse spon) {
		Maintenance maintenance = maintenanceservice.selectMaintenanceById(str);
		PrintWriter write = null;
		int i = 3;
		if (i > 0) {
			JSONObject json = new JSONObject();
			JSONArray ajson = new JSONArray();
			try {
				ajson.add(maintenance);
				write = spon.getWriter();
				json.put("list", ajson);
				System.out.println(json.toString());
				write.print(json);
				write.flush();
			} catch (Exception e) {
			} finally {
				write.close();
			}
		}
	}

	@RequestMapping(value = { "/save" })
	public @ResponseBody PageUtil<Maintenance> save(Maintenance bean,@RequestParam String[] role_cast) {
		List<Rolecastcode> rlist=new ArrayList<Rolecastcode>();
		List<Maintenance> mlist=new ArrayList<Maintenance>();
		StringBuffer sbu=new StringBuffer();
		StringBuffer sbuCode=new StringBuffer();
		bean.setId(getOrderByUUID());
		for (int i=0;i<role_cast.length;i++) {
			Rolecastcode  r=new Rolecastcode();
			String code=role_cast[i].substring(0,4);
			String name=role_cast[i].substring(4,role_cast[i].length());
             r.setId(getOrderByUUID());
             r.setRole(bean.getRoleArea());
             r.setCastCode(code);
             r.setCastCodeName(name);
             r.setPost(bean.getPost());
			rlist.add(r);
			sbu.append(role_cast[i]);
			sbuCode.append(code);
			if(i!=role_cast.length-1){
				sbu.append(",");
				sbuCode.append(",");
			}
		}
		bean.setCastCode(sbuCode.toString());
		bean.setRoleRaeaCast(sbu.toString());
		mlist.add(bean);
		int i = maintenanceservice.maintenanceSaveRole(mlist,rlist);
		List<Maintenance> list =null;
		PageUtil<Maintenance> pu = new PageUtil<Maintenance>();	
	     if (i > 0 ){
			pu.setT(bean);
			list=new ArrayList<Maintenance>();	
				int count = maintenanceservice.selectCountCondition(bean);
				pu.setSumRows(count);
				if (count > 0) {
				    list = maintenanceservice.selectMaintenance(pu);   
			        for(Maintenance p: list){
			        	Customs customs=customsservice.getCustomsName(p.getRoleArea());
			        	p.setRoleAreaName(customs.getCustomsName());
				        }
			        pu.setList(list);
			        pu.setSumRows(count);
				}			 
		}
		return pu;
	}

	@RequestMapping(value = { "/delete" })
	public @ResponseBody PageUtil<Maintenance> delete(String id,String post) {
		Maintenance  maintenance=maintenanceservice.selectMaintenanceById(id);
		String code[]=maintenance.getCastCode().split(",");
		List<Rolecastcode> rlist=new ArrayList<Rolecastcode>();
		PageUtil<Maintenance> pu = new PageUtil<Maintenance>();		
		for(String p :code){
			Rolecastcode r=new Rolecastcode();
			r.setRole(maintenance.getRoleArea());
			r.setPost(maintenance.getPost());
			r.setCastCode(p);
			rlist.add(r);
		}
		int i = maintenanceservice.maintenanceDeleteRole(id,rlist);
		List<Maintenance> list =new ArrayList<Maintenance>();
		if (i > 0) {
			Maintenance  m=new Maintenance();
			m.setPost(post);
			m.setOrgGuid(maintenance.getOrgGuid());
			pu.setT(m);
			int count = maintenanceservice.selectCountCondition(m);
			pu.setSumRows(count);
			if (count > 0) {
			    list = maintenanceservice.selectMaintenance(pu);   
		        for(Maintenance p: list){
		        	Customs customs=customsservice.getCustomsName(p.getRoleArea());
		        	p.setRoleAreaName(customs.getCustomsName());
			        }
		        pu.setList(list);
		        pu.setSumRows(count);
			}			 
		}
		return pu;
	}

	@RequestMapping(value = { "/list" })
	public String select(HttpServletRequest request, Integer page,String post,HttpSession session) {
		String customsCode=(String)request.getSession().getAttribute("customsCode");
		String orgGuid=(String)request.getSession().getAttribute("orgGuid");
		List<Customs> list = customsservice.selectByCustomsCode(customsCode.substring(0,2));
		PageUtil<Maintenance> pu = new PageUtil<Maintenance>();
		int  count =0 ;
		Maintenance m=new Maintenance();
		m.setOrgGuid(orgGuid);
		if(!("".equals(post)||null==post)){
			m.setPost(post);
			request.setAttribute("post", post);
			  count = maintenanceservice.selectCountCondition(m);
		}else{	
			  count = maintenanceservice.selectCountCondition(m);
		}
		pu.setT(m);
		Map<String,String> rmap=RoleDistributionSingleton.getOnstance().getRoleDistributionNameMap();
		String userId=(String)session.getAttribute("user_id");
		if (count > 0) {
			pu.setPage(page == null ? 1 : page);
			pu.setSumRows(count);
			List<Maintenance> mlist = maintenanceservice.selectMaintenance(pu);
	        for(Maintenance p: mlist){
        	Customs customs=customsservice.getCustomsName(p.getRoleArea());
        	p.setRoleAreaName(customs.getCustomsName());
	        }  
			request.setAttribute("mlist", mlist);
			request.setAttribute("page", pu);
		}
		    request.setAttribute("rmap", rmap);
		    request.setAttribute("list", list);
		    request.setAttribute("userId", userId);
		    request.setAttribute("orgGuid", orgGuid);
		return "/threeUniformPort/list";
	}

	@RequestMapping(value = { "/selectById" })
	public void selectById(String id) {
		id = "2";
//		Maintenance bean = maintenanceservice.selectMaintenanceById(id);
	}
	
	@RequestMapping(value = { "/saveCustoms" })
	public void saveCustoms(HttpServletRequest request) {
		int i=0;
		int j=0;
		int count=customsservice.deleteAllData();
		 System.out.println("共计清空"+count+"条数据"); 
		getLoginInfo getlogininfo= new getLoginInfo();
           List<Customs> list = getlogininfo.GetAllInfo(getlogininfo.getRootDept().getOrg_guid()); 
//           if(list!=null){
//        	   i=customsservice.saveCustoms(list);
//           }
           for(Customs c:list){
        	   List<Customs> listc= getlogininfo.GetAllInfo(c.getOrg_guid());
        	   j +=customsservice.saveCustoms(listc);
           }                
       System.out.println("共计保存"+i+j+"条数据"); 
        
	}
	
	@RequestMapping(value = { "/isRepetition" })
	public @ResponseBody String  isRepetition(Maintenance bean,@RequestParam String[] role_cast) {
		Rolecastcode rct=new Rolecastcode();
		rct.setPost(bean.getPost());
		rct.setRole(bean.getRoleArea());
		String str=isRepetitionMethod(rct,role_cast); 
           return str;
	}
	
	private String isRepetitionMethod(Rolecastcode bean,@RequestParam String[] role_cast){
		StringBuffer sbu=new StringBuffer();
		String str=null;
		sbu.append("映射关区:");
		int i=0;
		Rolecastcode rct=null;
        for(String r : role_cast){
        	bean.setCastCode(r.substring(0,4));
        	rct=rolecastcodedao.isRolecastcode(bean);
        	if(null!=rct) {
        	if(bean.getRole().equals(rct.getRole())){	
        			sbu.append(rct.getCastCodeName()+",");
        			i++;
        	}else{
        		    i++;
        		    str="所选关区已被映射请选择其他关区！！！";
        		    break;
        	}
        	}
    		
        }
        sbu.append("已存在!!!");
        if(i==0)  return "";
        if(null!=str) return str;
		return sbu.toString();
	}

	public static Integer getOrderByUUID() {
		int a = 1;
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0)
			hashCode = -hashCode;
		return a + hashCode;
	}

	public void getXMLInfo() throws Exception {
		// 解析XML报文信息
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document document = db.parse(new File("D:\\user.xml"));
			NodeList list = document.getElementsByTagName("usa");
			for (int i = 0; i < list.getLength(); i++) {
//				Element element = (Element) list.item(i);
//				String name = element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
//				String pass = element.getElementsByTagName("pass").item(0).getFirstChild().getNodeValue();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = { "/queryMaintenance" })
	@ResponseBody
	public List<Maintenance> queryMaintenance(HttpServletRequest request, Integer page,HttpSession session) {
			
				List<Maintenance> maintenanceList = maintenanceservice.queryMaintenance();
				
				return maintenanceList;
	}

}
