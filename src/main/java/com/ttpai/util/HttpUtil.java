package com.ttpai.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static final String CHARSET = "UTF-8";

	static RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
	static CloseableHttpClient hc = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	
	public static String post(String url,Map<String, String> params){
		return post(url, params, null);
	}
	
	public static String post(String url,Map<String, String> params,Map<String, String> header) {
		try{
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			if(params!=null){
				for(String key:params.keySet()){
					NameValuePair nv = new BasicNameValuePair(key, params.get(key));
					parameters.add(nv);
				}
			}
			HttpEntity he = new UrlEncodedFormEntity(parameters,CHARSET);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(he);
			if(header!=null){
				for(String key:header.keySet()){
					httpPost.addHeader(key, header.get(key));
				}
			}
			CloseableHttpResponse response = hc.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
	            httpPost.abort();
	            throw new RuntimeException("HttpClient,error status code :" + statusCode);
	        }
			HttpEntity entity = response.getEntity();
	        String result = null;
	        if (entity != null) {
	            result = EntityUtils.toString(entity, CHARSET);
	        }
	        EntityUtils.consume(entity);
	        response.close();
	        System.out.println("result:"+result);
	        return result;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String get(String url) {
		try{
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = hc.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
	            throw new RuntimeException("HttpClient,error status code :" + statusCode);
	        }
			HttpEntity entity = response.getEntity();
	        String result = null;
	        if (entity != null) {
	            result = EntityUtils.toString(entity, CHARSET);
	        }
	        EntityUtils.consume(entity);
	        response.close();
	        System.out.println("result:"+result);
	        return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String get(String url,Map<String, String> params) {
		try{
			if(params!=null){
				url += "?";
				for(String key:params.keySet()){
					url += key+"="+params.get(key)+"&";
				}
				System.out.println("url:"+url);
			}
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = hc.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
	            throw new RuntimeException("HttpClient,error status code :" + statusCode);
	        }
			HttpEntity entity = response.getEntity();
	        String result = null;
	        if (entity != null) {
	            result = EntityUtils.toString(entity, CHARSET);
	        }
	        EntityUtils.consume(entity);
	        response.close();
	        System.out.println("result:"+result);
	        return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String postInfoHeader(String url,String header,String info,String host) {
		Map<String, String> h = new HashMap<String, String>();
		h.put("ttp", header);
//		h.put("host", host);
		Map<String, String> p = new HashMap<String, String>();
		p.put("info", info);
		return post(url, p, h);
	}
	

}
