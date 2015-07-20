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
	//工具类
	public OpeFunction util;
	//wechat服务工具类
	public WechatKit kit;
	public final static String token ="abcdef123456";	
	public final static String APPSECRET ="aec63976cbb9f4d7903c68b9859f091e";	
	public final static String APPID ="wx21be87feca736895";	
	public HttpServletRequest req=ServletActionContext.getRequest();//你这两个都没有初始化
	public HttpServletResponse resp=ServletActionContext.getResponse();
	private String url;
	/**
	 * 微信开发用的
	 * @throws IOException 
	 */
	public void wechattest() throws IOException{
		url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatAction.APPID+"&secret="+WechatAction.APPSECRET;
		String jsonstr=kit.sendGet(url);
		System.out.println(jsonstr);
	}
	
	/**
	 * 微信开发用的
	 */
	public void wechatVerification() throws NoSuchAlgorithmException, IOException {
		// signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		// timestamp 时间戳
		// nonce 随机数
		// echostr随机字符串
		
		System.out.println("进入微信验证"+util.getNowTime());	
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String[] arr = {WechatAction.token,timestamp,nonce};
		//字典排序
		Arrays.sort(arr);
		StringBuffer sb=new StringBuffer();
		for(String a:arr){
			sb.append(a);
		}
		//进行sha1加密
		String sha1Msg=util.sha1(sb.toString());
		//比对
		Boolean b=signature.equals(sha1Msg);
		System.out.println("验证是否成功"+b);
		if(b){
			System.out.println("验证成功！"+util.getNowTime());
			util.Out().print(echostr);
		}else{
			System.out.println("验证失败！"+util.getNowTime());
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
