package com.ssm.basedata.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssm.basedata.business.dict.SysDictBusiness;
import com.ssm.basedata.service.SysDictService;
import com.ssm.common.exception.BusinessException;

@Service("sysDictServiceImpl")
public class SysDictServiceImpl implements SysDictService{

	private static final Logger logger = LoggerFactory.getLogger(SysDictServiceImpl.class);
		
	@Resource(name="sysDictBusinessImpl")
	private SysDictBusiness sysDictBusiness;
	
	@Override
	public Map<String, Map<String, String>> getAllDic() throws BusinessException {
		logger.info("begin getalldict.");
		sysDictBusiness.queryList(null);
		return null;
	}

}
