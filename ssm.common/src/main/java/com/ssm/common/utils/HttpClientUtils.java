package com.ssm.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP客户端工具 
 * 
 * @author zouyoujin
 *
 */
public final class HttpClientUtils {
	
	private static HttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager());

	private static String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	private static String CONTENT_TYPE_APPLICATION_JSON = "application/json";

	/**
	 * 发送http get请求
	 * 
	 * @param url
	 *            url地址
	 * @return 响应数据
	 */
	public static String httpForGet(String url) {
		HttpGet request = null;
		try {
			request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				return content;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			request.abort();
		}
		return null;
	}

	/**
	 * 发送Post请求, 传递params参数
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String httpForPostParameters(String url, Map<String, String> paramsMap) {
		Iterator<Entry<String, String>> iter = paramsMap.entrySet().iterator();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			String keyStr = (String) entry.getKey();
			String valueStr = (String) entry.getValue();
			params.add(new BasicNameValuePair(keyStr, valueStr));
		}
		HttpPost post = null;
		try {
			post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				return content;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			post.abort();
		}
		return null;
	}

	/**
	 * 发送http post请求
	 * @param url  url地址
	 * @param requestBody 发送的请求体字符串
	 * @param contentType 发送的请求数据类型
	 * @return 响应数据
	 */
	public static String httpForPostString(String url, String requestBody,String contentType) {
		HttpPost post = null;
		try {
			post = new HttpPost(url);
			StringEntity s = new StringEntity(requestBody, "utf-8");
			s.setContentEncoding("utf-8");
			s.setContentType(contentType);
			
			post.setEntity(s);

			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				return content;
			}
			else {
				return response.getStatusLine().toString();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			post.abort();
		}
	}

	/**
	 * 发送http post请求
	 * @param url  url地址
	 * @param requestBody 发送的请求体字符串
	 * @param contentType 发送的请求数据类型
	 * @return 响应数据
	 */
	public static String httpForPostFile(String url, Object obj,File file) {
		HttpPost post = null;
		try {
			post = new HttpPost(url);
			String requestBody = JsonUtils.toJson(obj);
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody comment = new StringBody(requestBody,Charsets.UTF_8);
			reqEntity.addPart("params",comment);
			if(null != file){
				FileBody fileBody = new FileBody(file);  
				reqEntity.addPart("file",fileBody);	
			}
			post.setEntity(reqEntity);
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				return content;
			}
			else {
				return response.getStatusLine().toString();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			post.abort();
		}
	}
	
	/**
	 * 发送http post请求, 请求结构体为json格式数据
	 * @param url url地址
	 * @param requestBody 发送的请求体
	 * @return 响应数据
	 */
	public static String httpForPostJsonContent(String url, String requestBody) {
		return httpForPostString(url, requestBody, CONTENT_TYPE_APPLICATION_JSON);
	}

	/**
	 * 发送http post请求, 请求结构体为普通字符串数据
	 * @param url url地址
	 * @param requestBody 发送的请求体
	 * @return 响应数据
	 */
	public static String httpForPostPlainText(String url, String requestBody) {
		return httpForPostString(url, requestBody, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	/**
	 * 发送http post请求, 传入对象
	 * @param url url地址
	 * @param obj 对象
	 * @return 响应数据
	 */
	public static String httpForPostJsonObject(String url, Object obj) {
		String requestBody = JsonUtils.toJson(obj);		
		return httpForPostJsonContent(url, requestBody);
	}
	
}
