package com.ttpai.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static final String CHARSET = "UTF-8";

	static RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
	static HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	static HttpClient httpsClient = createHttpsClient();
	
	public static String post(String url,Map<String, String> params){
		return post(url, params, null);
	}
	public static String post(String url){
		return post(url, null, null);
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
			HttpClient client;
			if(url.startsWith("http://")){
				client = httpClient;
			}else if(url.startsWith("https://")){
				client = httpsClient;
			}else{
				throw new RuntimeException();
			}
			HttpResponse response = client.execute(httpPost);
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
			HttpResponse response = httpClient.execute(httpGet);
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
	        System.out.println("result:"+result);
	        return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String get(String url,Map<String, String> params) {
		if(params!=null){
			url += "?";
			for(String key:params.keySet()){
				url += key+"="+params.get(key)+"&";
			}
			System.out.println("url:"+url);
		}
		return get(url);
	}
	
	public static String postInfoHeader(String url,String header,String info,String host) {
		Map<String, String> h = new HashMap<String, String>();
		h.put("ttp", header);
		h.put("host", host);
		Map<String, String> p = new HashMap<String, String>();
		p.put("info", info);
		return post(url, p, h);
	}
	
	public static HttpClient createHttpsClient() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLSv1.2");
			ctx.init(null, new TrustManager[] { new TrustAnyTrustManager() },null);
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLSocketFactory(socketFactory).build();
			return httpClient;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[] {};}
	}

}
