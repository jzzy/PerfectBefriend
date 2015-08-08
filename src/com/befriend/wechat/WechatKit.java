package com.befriend.wechat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;

import com.befriend.util.OpeFunction;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class WechatKit {

	private static CloseableHttpClient Client = null;
	private static CloseableHttpResponse resp = null;
	private static HttpClient client=null;

	/**
	 * ����get����
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String sendGet(String url) throws IOException {
		try {
			String cont = null;

			if (Client == null) {
				// �õ� Client
				Client = HttpClients.createDefault();
			}
			
			HttpGet get = new HttpGet(url);
			try {

				
				resp = Client.execute(get);
			} catch (Exception e) {
				
			}
		
			int code = resp.getStatusLine().getStatusCode();
			System.out.println("WechatKit.sendGet code:" + code+",URL "+url);
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
	/**
	 * ͨ��post����!
	 * @param url
	 * @param json
	 * @return
	 */
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
	/**
	 * @describe HTTP Method : DELETE
	 * @param url
	 * @param json
	 * @param token
	 * @return
	 */
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

	/**
	 * ���� ��װ��HttpClient
	 * 
	 * @param isSSL
	 * @return
	 */

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
