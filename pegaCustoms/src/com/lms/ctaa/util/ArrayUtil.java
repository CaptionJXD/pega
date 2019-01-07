package com.lms.ctaa.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 操作集合
 * @author zhengjiajia
 * @date 2017年4月17日
 * @version 1.0
 */
public class ArrayUtil {
	
	//去掉集合中相同的元素
	@SuppressWarnings("unchecked")
	public static List  removeDuplicateWithOrder(List list){
		Set set = new HashSet(list.size());
		set.addAll(list);
		List newList = new ArrayList(set.size());
		newList.addAll(set);
		return new ArrayList(new LinkedHashSet(list));
	}
	
	/**
	 * @Description:拆分集合
	 * @param resList 要拆分的集合
	 * @param count   每个集合的元素个数
	 * @return        返回拆分后的各个集合
	 */
	public static <T> List<List<T>> split(List<T> resList,int count){
		if(resList== null || count<1)
			return null;
		List<List<T>> ret = new ArrayList<List<T>>();
		int size = resList.size();
		if(size<count){//数量不足count制定的大小
			ret.add(resList);
		}else{
			int pre = size/count;
			int last = size/count;
			//前面pre个集合，每个大小都是count个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<T>();
				for(int j = 0;j<count;j++){
					itemList.add(resList.get(i*count+j));
				}
				ret.add(itemList);
			}
			//对last的进行处理
			if(last>0){
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i < last; i++) {
					itemList.add(resList.get(pre*count+i));
				}
				ret.add(itemList);
			}
		}
		return ret;
	}
}

 