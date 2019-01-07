package com.lms.ctaa.pojo;

public class Customs {
	private Integer id;
	private String customsName;//直属海关名称
	private String suoeriorSustoms;//隶属海关
	private String createTime;//创建时间
	private String org_guid;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomsName() {
		return customsName;
	}
	public void setCustomsName(String customsName) {
		this.customsName = customsName;
	}

	public String getSuoeriorSustoms() {
		return suoeriorSustoms;
	}
	public void setSuoeriorSustoms(String suoeriorSustoms) {
		this.suoeriorSustoms = suoeriorSustoms;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrg_guid() {
		return org_guid;
	}
	public void setOrg_guid(String org_guid) {
		this.org_guid = org_guid;
	}
	
}
