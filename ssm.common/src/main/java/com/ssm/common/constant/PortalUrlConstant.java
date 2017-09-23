package com.ssm.common.constant;

/**
 * portalWeb Url地址信息
 * 
 * @author zouyoujin
 *
 */

public class PortalUrlConstant {

	/**
	 * common公共接口模块
	 */
	public static final String API_COMMON = "/common";

	/**
	 * common公共接口模块系统主页面
	 */
	public static final String API_COMMON_INDEX = "/index";

	/**
	 * common公共接口模块系统主页面
	 */
	public static final String API_COMMON_MAIN = "/main";

	/**
	 * common公共接口模块系统国际化语言切换
	 */
	public static final String API_COMMON_CHANGELANG = "/changeLang";

	/**
	 * common公共接口模块错误页面
	 */
	public static final String API_COMMON_ERROR = "/error";

	/**
	 * common公共接口模块页面找不到
	 */
	public static final String API_COMMON_PAGENOTFOUND = "/pagenotfound";

	/**
	 * 公共页面动态页面一级映射入口
	 */
	public static final String API_DYNAMIC_ONE_LEVEL_PAGE = "/*.html";

	/**
	 * 公共页面动态页面二级映射入口
	 */
	public static final String API_DYNAMIC_MORE_LEVEL_PAGE = "/**/*.html";
}
