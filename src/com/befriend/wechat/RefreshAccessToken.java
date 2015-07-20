package com.befriend.wechat;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import com.befriend.util.OpeFunction;

public class RefreshAccessToken {
	public static String access_token =null;
	public static int Second=0;
	public RefreshAccessToken(int second) {
		Second=second;
		//
		Timer timer = new Timer();
		// 执行类 延迟时间(毫秒) 每隔多久 秒*1000(毫秒)
		timer.schedule(new RefreshAccessTokenTask(), 0, second * 1000);

	}

	public class RefreshAccessTokenTask extends TimerTask {

		@Override
		public void run() {

			// 微信
			// url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatAction.APPID+"&secret="+WechatAction.APPSECRET;
			// 环信
			String url = "https://a1.easemob.com/topLong/wcfriend/token";
			try {
				/**
				 * NameValuePair[] param = { new NameValuePair("client_id",
				 * "YXA6MHPlMALGEeWyhu9dNjM0XA"), new
				 * NameValuePair("grant_type", "toplong#parentsfriend"), new
				 * NameValuePair("client_secret",
				 * "YXA6nk6uU_DPp-jP3NSYQzwYS4FQhx4")};
				 */
				// 封装个json  获取token的
				JSONObject json = new JSONObject();
				json.put("client_id", "YXA6MHPlMALGEeWyhu9dNjM0XA");
				json.put("grant_type", "client_credentials");
				json.put("client_secret", "YXA6OhW1URHjHtAbX_R0ocltSfiWNLQ");
				 //json.put("username", "topLong");
				 //json.put("password", "toplong!");

				// 获取到json access_token 微信的
				// access_token=OpeFunction.getJsonKey(WechatKit.sendGet(url),
				// "access_token");
			 System.out.println("access_token:"+access_token);
				System.out.println("封装的json:"+json.toString());

				String jn = null;
				
				//jn=WechatKit.post(url, json,null);
				 System.out.println("jn:"+jn);
				
				if (jn != null) {
					// 获取access_token
					access_token = OpeFunction.getJsonKey(jn, "access_token");
				}
				System.out.println("access_token:"+access_token);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("异常:" + e.getMessage());
			}
			System.out.println("每个"+Second+"s执行" + OpeFunction.getNowTime());

		}

	}

}
