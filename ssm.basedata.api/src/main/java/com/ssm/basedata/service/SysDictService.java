package com.ssm.basedata.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.ssm.common.exception.ServiceException;
import com.ssm.common.model.SysDict;

/**
 * 
 * 数据字典接口服务
 * @author Kitty
 *
 */
public interface SysDictService {
	
	/**
	 * 获取所有的数字字典
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Map<String, String>> getAllDict() throws ServiceException;
	
	/**
	 * 查询数字字典分页数据
	 * @return
	 * @throws BusinessException
	 */
	public Page<SysDict> queryByPage(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 查询数字字典列表数据
	 * @return
	 * @throws BusinessException
	 */
	public List<SysDict> queryList(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 查询数据详情
	 * @param params
	 * @return
	 */
	public SysDict queryDetail(Long id) throws ServiceException;
	
	/**
	 * 修改数字字典值
	 * @param sysDict
	 * @return
	 */
	public Boolean update(SysDict sysDict) throws ServiceException;
	
	/**
	 * 删除数据字典数据
	 * @param sysDict
	 * @return
	 */
	public Boolean delete(SysDict sysDict) throws ServiceException;
}
