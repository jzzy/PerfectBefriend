package com.befriend.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;

public class WechatAction {
	//������
	public OpeFunction util;
	//wechat���񹤾���
	public WechatKit kit;
	public final static String token ="abcdef123456";	
	public final static String APPSECRET ="aec63976cbb9f4d7903c68b9859f091e";	
	public final static String APPID ="wx21be87feca736895";	
	public HttpServletRequest req=ServletActionContext.getRequest();//����������û�г�ʼ��
	public HttpServletResponse resp=ServletActionContext.getResponse();
	private String url;
	/**
	 * ΢�ſ����õ�
	 * @throws IOException 
	 */
	public void wechattest() throws IOException{
		url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatAction.APPID+"&secret="+WechatAction.APPSECRET;
		String jsonstr=kit.sendGet(url);
		System.out.println(jsonstr);
	}
	
	/**
	 * ΢�ſ����õ�
	 */
	public void wechatVerification() throws NoSuchAlgorithmException, IOException {
		// signature ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
		// timestamp ʱ���
		// nonce �����
		// echostr����ַ���
		
		System.out.println("����΢����֤"+util.getNowTime());	
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String[] arr = {WechatAction.token,timestamp,nonce};
		//�ֵ�����
		Arrays.sort(arr);
		StringBuffer sb=new StringBuffer();
		for(String a:arr){
			sb.append(a);
		}
		//����sha1����
		String sha1Msg=util.sha1(sb.toString());
		//�ȶ�
		Boolean b=signature.equals(sha1Msg);
		System.out.println("��֤�Ƿ�ɹ�"+b);
		if(b){
			System.out.println("��֤�ɹ���"+util.getNowTime());
			util.Out().print(echostr);
		}else{
			System.out.println("��֤ʧ�ܣ�"+util.getNowTime());
			util.Out().print(false);
		}
		
		/**
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet Get = new HttpGet("http://targethost/homepage");
		CloseableHttpResponse cresp=client.execute();
		int sen=resp.getStatus()
		*/
		
		   
	
		

		
	}
	
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	
	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}

}
