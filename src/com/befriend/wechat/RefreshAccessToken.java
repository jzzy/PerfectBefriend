package com.befriend.wechat;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import com.befriend.util.OpeFunction;

public  class  RefreshAccessToken {
	public static final String BASE_URL = "https://a1.easemob.com/topLong/parentsfriend";
	public static String access_token ="YWMt23HYsksEEeWzSD2dC05ofAAAAVCZAQrl6L7JhDOMGnOUwL9gJ_-AtaRK60E";
	public static int Second=0;
	public RefreshAccessToken(int second) {
		Second=second;
		Timer timer = new Timer();
		timer.schedule(new RefreshAccessTokenTask(), 0, Second * 1000);

	}

	public class RefreshAccessTokenTask extends TimerTask {

		@SuppressWarnings("unused")
		@Override
		public synchronized void run() {

			String url = "https://a1.easemob.com/topLong/parentsfriend/token";
			try {

				JSONObject json = new JSONObject();
				json.put("client_id", "YXA6MzEwcALGEeWNyInfcoYGUQ");
				json.put("grant_type", "client_credentials");
				json.put("client_secret", "YXA6nk6uU_DPp-jP3NSYQzwYS4FQhx4");
				json.put("username", "topLong");
				json.put("password", "toplong!");
				System.out.println("json:" + json.toString());
				String jn = null;
				//jn = WechatKit.post(url, json, null);
				System.out.println("jn:" + jn);
				if (jn != null) {
					access_token = OpeFunction.getJsonKey(jn, "access_token");
				}
				System.out.println("access_token:" + access_token);

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}

}
