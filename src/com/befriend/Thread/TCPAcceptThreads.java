package com.befriend.Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.befriend.dao.GroupDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.ApplicationUtil;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;

public class TCPAcceptThreads implements Runnable {
	
	private  User u = null;
	private  String ip = null;
	private  String url = null;
	private Socket s=null;
	public TCPAcceptThreads(Socket s) {
		super();
		this.s = s;

	}

	@Override
	public void run() {
		try {
			
			//ip = OpeFunction.getLIP();

			// 获取入流
			InputStream in = s.getInputStream();
			// 包装 输入流
			DataInputStream dis = new DataInputStream(in);

			// 阻塞接受 信息
			while (true) {
				String st = null;
				System.out.println("TCP接收端进入阻塞"+s + OpeFunction.getNowTime());
				// 读取获取的内容

				st = dis.readUTF();
				System.out.println("TCP接收到:" + st + "   "
						+ OpeFunction.getNowTime());
				try {
					u = OpeFunction.getJsonKeyUs(st);

					// 上线模式
					if (u != null) {
					
						
						url = "http://127.0.0.1/Befriend/onlineStatus?id="
								+ u.getId()+"";

						WechatKit.sendGet(url);
						
					}
				} catch (Exception e) {
					System.out.println("tcp:"+url);
					System.out.println("TCPAcceptThreads 更改用户上线状态异常！ "
							+ e.getMessage());
					
				}

			}

		} catch (Exception e) {

			System.out.println("客户端下线"+s + e);
			// 下线模式
			if (u != null) {
				try {
					System.out.println("TCP发送下线请求!");
					url = "http://127.0.0.1/Befriend/onlineStatus?id=" + u.getId()
							+ "&gag=1"+"";
				

				
					WechatKit.sendGet(url);
				} catch (IOException e1) {
					System.out.println("TCPAcceptThreads 更改用户下线状态异常！ "
							+ e1.getMessage());

				}
			}else{
				System.out.println("TCP没有获取到User!");
			}

		} finally {
		

			}

			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("socket关闭异常" + e);
			}
		}

	

	

}
