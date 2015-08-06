package com.befriend.wechat;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONObject;

import com.befriend.util.OpeFunction;

public class RefreshAccessToken {
	public static String access_token ="YWMt1h3C2DwgEeWAqSkS5NjStgAAAVA3apU8AkJ4pRa53rACTmIPH4-YUrnibqs";
	public static int Second=0;
	public RefreshAccessToken(int second) {
		Second=second;
		Timer timer = new Timer();
		timer.schedule(new RefreshAccessTokenTask(), 0, second * 1000);

	}

	public class RefreshAccessTokenTask extends TimerTask {

		@Override
		public void run() {

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
