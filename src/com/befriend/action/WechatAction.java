package com.befriend.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
@SuppressWarnings("all")
public class WechatAction {

	public OpeFunction util;
	
	public WechatKit kit;
	public final static String token ="abcdef123456";	
	public final static String APPSECRET ="aec63976cbb9f4d7903c68b9859f091e";	
	public final static String APPID ="wx21be87feca736895";	
	public HttpServletRequest req=ServletActionContext.getRequest();//����������û�г�ʼ��
	public HttpServletResponse resp=ServletActionContext.getResponse();
	private String url;
	
	public void wechattest() throws IOException{
		url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatAction.APPID+"&secret="+WechatAction.APPSECRET;
		String jsonstr=kit.sendGet(url);
		System.out.println(jsonstr);
	}
	
	public void wechatVerification() throws NoSuchAlgorithmException, IOException {
	
		
		
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String[] arr = {WechatAction.token,timestamp,nonce};
	
		Arrays.sort(arr);
		StringBuffer sb=new StringBuffer();
		for(String a:arr){
			sb.append(a);
		}
	
		String sha1Msg=util.sha1(sb.toString());
		
		Boolean b=signature.equals(sha1Msg);
	
		if(b){
		
			util.Out().print(echostr);
		}else{
		
			util.Out().print(false);
		}
		
	
		
		   
	
		

		
	}
	
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	
	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}

}
