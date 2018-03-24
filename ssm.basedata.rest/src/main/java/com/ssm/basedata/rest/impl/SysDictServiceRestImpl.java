package com.ssm.basedata.rest.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ssm.basedata.rest.SysDictServiceRest;
import com.ssm.basedata.service.SysDictService;
import com.ssm.common.pojo.ResponseService;

/**
 * 用户Rest接口
 * 
 * @author Kitty
 *
 */
@Path("dict")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Consumes({ MediaType.APPLICATION_JSON })
@Named
public class SysDictServiceRestImpl implements SysDictServiceRest {

	private static final Logger logger = LoggerFactory.getLogger(SysDictServiceRestImpl.class);

	@Inject
	private SysDictService sysDictService;

	/**
	 * 查询字典项
	 * 
	 * @param modelMap
	 * @param param
	 * @return
	 */
	@GET
	@Path("page")
	public ResponseService<Object> query(Map<String, Object> param) {
		param.put("orderBy", "type_,sort_no");
		
		sysDictService.getAllDic();
		return null;
	}
	//
	// @ApiOperation(value = "查询字典项")
	// @RequiresPermissions("sys.base.dic.read")
	// @PutMapping(value = "/read/list")
	// public Object queryList(ModelMap modelMap, @RequestBody Map<String,
	// Object> param) {
	// param.put("orderBy", "type_,sort_no");
	// return super.queryList(modelMap, param);
	// }
	//
	// @ApiOperation(value = "字典项详情")
	// @RequiresPermissions("sys.base.dic.read")
	// @PutMapping(value = "/read/detail")
	// public Object get(ModelMap modelMap, @RequestBody SysDic param) {
	// return super.get(modelMap, param);
	// }
	//
	// @PostMapping
	// @ApiOperation(value = "修改字典项")
	// @RequiresPermissions("sys.base.dic.update")
	// public Object update(ModelMap modelMap, @RequestBody SysDic param) {
	// return super.update(modelMap, param);
	// }
	//
	// @DeleteMapping
	// @ApiOperation(value = "删除字典项")
	// @RequiresPermissions("sys.base.dic.delete")
	// public Object delete(ModelMap modelMap, @RequestBody SysDic param) {
	// return super.delete(modelMap, param);
	// }
}
