package com.lms.ctaa.util;

import java.util.List;

public class PageUtil<T> {
	private List<T> list;
	private T t;
	private int start;
	private int end;
	private int rows;
	private int page;
	private int sumRows;
    private int endPage;
	public PageUtil(){
		start=0;
		end=0;
		page=1;
		rows=3;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return rows*page-rows;
	}
	public int getEnd() {
		return rows*page;
	}
	public void setPage(int page) {
		this.page = page==0?1:page;
	}

	public int getPage() {
		return page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getSumRows() {
		return sumRows;
	}
	public void setSumRows(int sumRows) {
		this.sumRows = sumRows;
	}
	public int getEndPage() {
		return new Double(Math.ceil(((double)sumRows/rows))).intValue();
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}

	
	
	

}
