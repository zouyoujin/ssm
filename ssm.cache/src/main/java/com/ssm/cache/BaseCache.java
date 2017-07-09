package com.ssm.cache;

import org.springframework.cache.annotation.CacheEvict;

/**
 * 缓存操作基础类
 * 
 * @author Kitty
 *
 */
public abstract class BaseCache {

	@CacheEvict(allEntries = true)
	public abstract void clearCaches(Class<?> clazz);
}
