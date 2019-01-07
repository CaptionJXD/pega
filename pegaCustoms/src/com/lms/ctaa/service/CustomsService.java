package com.lms.ctaa.service;

import java.util.List;

import com.lms.ctaa.pojo.Customs;

public interface CustomsService extends BaseService<Customs> {

	/**
	 * 查询
	 * @param t
	 * @return
	 */
	public  List<Customs> select();
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Customs selectById(String id);
	/**
	 * 插入数据
	 * @param list
	 * @return
	 */
	public int saveCustoms(List<Customs> list);
	
	/**
	 * 依据关区代码查询关区名称
	 * @param list
	 * @return
	 */
	public Customs getCustomsName(String CustomsCode);
	/**
	 * 模糊查询依据报关单前两位
	 * @param list
	 * @return
	 */
	public List<Customs> selectByCustomsCode(String CustomsCode);
	
	/**
	 * 清空表中数据
	 * @param int
	 * @return
	 */
	public int deleteAllData();
}
