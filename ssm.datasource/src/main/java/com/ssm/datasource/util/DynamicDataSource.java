package com.ssm.datasource.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author Kitty 实现数据库动态切换数据源功能
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.getDataSource();
	}

}
