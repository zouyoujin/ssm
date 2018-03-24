package com.ssm.basedata.business.dict.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.business.dict.SysDictBusiness;
import com.ssm.common.constant.Constants;
import com.ssm.common.model.SysDict;
import com.ssm.common.utils.InstanceUtil;
import com.ssm.datasource.common.BaseBusinessImpl;

@Service("sysDictBusinessImpl")
@Transactional(rollbackFor = { Exception.class })
@CacheConfig(cacheNames = "sysDict")
public class SysDictBusinessImpl extends BaseBusinessImpl<SysDict> implements SysDictBusiness{

	@Cacheable(value = Constants.CACHE_NAMESPACE + "sysDics")
	public Map<String, Map<String, String>> getAllDic() {
		Map<String, Object> params = InstanceUtil.newHashMap();
		params.put("orderBy", "type_,sort_no");
		List<SysDict> list = this.queryList(params);
		Map<String, Map<String, String>> resultMap = InstanceUtil.newHashMap();
		for (SysDict sysDic : list) {
			if (sysDic != null) {
				String key = sysDic.getType();
				if (resultMap.get(key) == null) {
					Map<String, String> dicMap = InstanceUtil.newHashMap();
					resultMap.put(key, dicMap);
				}
				if (StringUtils.isNotBlank(sysDic.getParentCode())) {
					resultMap.get(key).put(sysDic.getParentCode() + sysDic.getCode(), sysDic.getCodeText());
				} else {
					resultMap.get(key).put(sysDic.getCode(), sysDic.getCodeText());
				}
			}
		}
		return resultMap;
	}
}
