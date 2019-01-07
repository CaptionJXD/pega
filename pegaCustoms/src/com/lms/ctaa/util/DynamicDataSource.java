package com.lms.ctaa.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		//  从自定义的位置获取数据源标示
		return DynamicDataSourceHolder.getDataSource();
	}
	
	

}
