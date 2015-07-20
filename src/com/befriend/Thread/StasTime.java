package com.befriend.Thread;

import java.io.IOException;

import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;

public class StasTime implements Runnable {

	@Override
	public void run() {
		
		
		
		while (true) {
			String url = "http://127.0.0.1/PerfectBefriend/aStas";
			try {
				WechatKit.sendGet(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(OpeFunction.getNowTime());
				System.out.println("StasTime定时任务执行了");
				try {
					
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		
		
		
	}

}
