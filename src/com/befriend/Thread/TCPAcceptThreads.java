package com.befriend.Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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

			// ��ȡ����
			InputStream in = s.getInputStream();
			// ��װ ������
			DataInputStream dis = new DataInputStream(in);

			// �������� ��Ϣ
			while (true) {
				String st = null;
				System.out.println("TCP���ն˽�������"+s + OpeFunction.getNowTime());
				// ��ȡ��ȡ������

				st = dis.readUTF();
				System.out.println("TCP���յ�:" + st + "   "
						+ OpeFunction.getNowTime());
				try {

					u = OpeFunction.fromJson(st, User.class);


					// ����ģʽ
					if (u != null) {
					
						
						url = "http://127.0.0.1/Befriend/onlineStatus?id="
								+ u.getId()+"";

						WechatKit.sendGet(url);
						
					}
				} catch (Exception e) {
					System.out.println("tcp:"+url);
					System.out.println("TCPAcceptThreads �����û�����״̬�쳣�� "
							+ e.getMessage());
					
				}

			}

		} catch (Exception e) {

			System.out.println("�ͻ�������"+s + e);
			// ����ģʽ
			if (u != null) {
				try {
					System.out.println("TCP������������!");
					url = "http://127.0.0.1/Befriend/onlineStatus?id=" + u.getId()
							+ "&gag=1"+"";
				

				
					WechatKit.sendGet(url);
				} catch (IOException e1) {
					System.out.println("TCPAcceptThreads �����û�����״̬�쳣�� "
							+ e1.getMessage());

				}
			}else{
				System.out.println("TCPû�л�ȡ��User!");
			}

		} finally {
		

			}

			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("socket�ر��쳣" + e);
			}
		}

	

	

}
