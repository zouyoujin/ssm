package com.ssm.basedata.rest;

import java.util.Map;

import com.ssm.common.pojo.ResponseService;

/**
 * 字典管理
 * 
 * @author kitty
 *
 */
public interface SysDictServiceRest {

	public ResponseService<Object> query(Map<String, Object> param);
}
