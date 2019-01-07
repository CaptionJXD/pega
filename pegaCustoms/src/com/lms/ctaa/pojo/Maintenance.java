package com.lms.ctaa.pojo;

public class Maintenance {
	private Integer id;
	private String post;//岗位
	private String roleArea;//角色所在关区
	private String roleRaeaCast;//角色映射关区
	private String remark;//备注
	private String createTime;//创建时间
	private String roleAreaName;
	private String userId;//登陆的用户id
	private String castCode;//映射关区代码（关区号）
	private String orgGuid;

	public Maintenance(){
		post="";
		roleArea="";
		roleRaeaCast="";
		remark="";
		roleAreaName="";
		userId="";
		orgGuid="";
		castCode="";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getRoleArea() {
		return roleArea;
	}
	public void setRoleArea(String roleArea) {
		this.roleArea = roleArea;
	}
	public String getRoleRaeaCast() {
		return roleRaeaCast;
	}
	public void setRoleRaeaCast(String roleRaeaCast) {
		this.roleRaeaCast = roleRaeaCast;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRoleAreaName() {
		return roleAreaName;
	}
	public void setRoleAreaName(String roleAreaName) {
		this.roleAreaName = roleAreaName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCastCode() {
		return castCode;
	}
	public void setCastCode(String castCode) {
		this.castCode = castCode;
	}
	public String getOrgGuid() {
		return orgGuid;
	}
	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	
}
