package com.befriend.wechat;

import java.io.IOException;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;




import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;




import com.befriend.util.OpeFunction;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
@SuppressWarnings("deprecation")
public class WechatKit {

	private static CloseableHttpClient Client = null;
	private static CloseableHttpResponse resp = null;
	private static HttpClient client=null;

	/**
	 * 适合多线程的HttpClient,用httpClient4.2.1实现
	 * 
	 * @return DefaultHttpClient
	 */
	public static DefaultHttpClient getHttpClient() {
		// 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
		HttpParams params = new BasicHttpParams();

		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		// 设置连接超时时间
		final int REQUEST_TIMEOUT = 2 * 1000; // 设置请求超时2秒钟
		final int SO_TIMEOUT = 2 * 1000; // 设置等待数据超时时间2秒钟
//		final Long CONN_MANAGER_TIMEOUT = 500L; // 该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大
												// 

		// HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
		// HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				REQUEST_TIMEOUT);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
	//	params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
		params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK,
				true);// 在提交请求之前 测试连接是否可用
		// 设置访问协议
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schreg.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));

		// 多连接的线程安全的管理器
		PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(
				schreg);
		pccm.setMaxTotal(200); // 客户端总并行链接最大数 MaxtTotal是整个池子的大小；
		pccm.setDefaultMaxPerRoute(200); // 每个主机的最大并行链接数

		DefaultHttpClient httpClient = new DefaultHttpClient(pccm, params);
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						0, false));// 另外设置http
									// client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）
		return httpClient;
	}
	public static String sendGet(String url) throws IOException {
		try {
			String cont = null;

			if (Client == null) {
				
				Client = getHttpClient();
			}
			
			HttpGet get = new HttpGet(url);
			try {

				
				resp = Client.execute(get);
			} catch (Exception e) {
				
			}
		
			int code = resp.getStatusLine().getStatusCode();
			System.out.println(OpeFunction.getNowTime()+",122 row WechatKit.sendGet code:" + code+",URL "+url);
			if (code >= 200 && code < 300) {
				
				HttpEntity entity = resp.getEntity();
				if (entity != null) {
					cont = EntityUtils.toString(entity);
					System.out.println(cont);
					return cont;
				}

			}
		} catch (Exception e) {

			return null;
		} finally {
			if (resp != null) {
				resp.close();
			}
		}
		return null;
	}
	
	public static String post(String url, JSONObject json,String token) {
		
		client = getClient(true);
		HttpPost post = new HttpPost(url);
		int code =0;
		try {
			StringEntity s = new StringEntity(json==null?"":json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");

			if(token!=null){
			post.addHeader("Authorization", "Bearer "+token);
			}
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			code = res.getStatusLine().getStatusCode();
			System.out.println("postcode:"+code);
			if (code >= 200 && code < 300) {
				HttpEntity entity = res.getEntity();
				String charset = EntityUtils.toString(entity);
				return charset;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String delete(String url,String token)
	{
		String response = null;
		int code =0;
		client = getClient(true);
		HttpDelete delete = new HttpDelete(url);
		try {
			if(token!=null)
			{
				delete.addHeader("Authorization", "Bearer "+token);
			}
			HttpResponse res = client.execute(delete);
			code = res.getStatusLine().getStatusCode();
			if (code >= 200 && code < 300) 
			{
				HttpEntity entity = res.getEntity();
				response = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
		
	}
	/**
	 * HTTP Method : GET
	 * @param url
	 * @param token
	 * @return
	 */
	public static String get(String url,String token)
	{
		String response = null;
		int code =0;
		client = getClient(true);
		HttpGet get = new HttpGet(url);
		try {
			if(token!=null)
			{
				get.addHeader("Authorization", "Bearer "+token);
			}
			HttpResponse res = client.execute(get);
			code = res.getStatusLine().getStatusCode();
			if (code >= 200 && code < 300) 
			{
				HttpEntity entity = res.getEntity();
				response = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
		
	}

	

	
	public static HttpClient getClient(boolean isSSL) {

		HttpClient httpClient = new DefaultHttpClient();
		if (isSSL) {
			X509TrustManager xtm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			try {
				SSLContext ctx = SSLContext.getInstance("TLS");

				ctx.init(null, new TrustManager[] { xtm }, null);

				SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

				httpClient.getConnectionManager().getSchemeRegistry()
						.register(new Scheme("https", 443, socketFactory));

			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return httpClient;
	}

}
