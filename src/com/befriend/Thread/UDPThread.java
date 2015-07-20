package com.befriend.Thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.OpeFunction;

public class UDPThread implements Runnable {
	private User u = null;


	public DatagramSocket server = null;

	public UDPThread(DatagramSocket server) {
		super();
		this.server = server;
	}

	@Override
	public void run() {
		try {

			// 声明 byte数组
			byte[] buf = new byte[4096];

			// 创建接收数据包，存储在buf中
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			System.out.println("UDP服务器监听开始..." + OpeFunction.getNowTime());

			try {

				while (true) {

					System.out.println("UDP服务器等待接收..."
							+ OpeFunction.getNowTime());
					// 接收操作 会阻塞
					server.receive(packet);
					String uip = new String(packet.getAddress().toString());
					String a[] = uip.split("/");
					System.out.println("UDP服务器接受了来自ip为:" + a[1] + "_时间:"
							+ OpeFunction.getNowTime());
					System.out.println("UDP服务器接受了来自端口号为:" + packet.getPort());
					String info = new String(packet.getData(), 0,
							packet.getLength());
					System.out.println("UDP服务器接收到的信息:" + info);
					try {

						u = OpeFunction.getJsonKeyUs(info);

						if (u != null) {
							new Thread(new ModifyThreads(u, a[1],
									packet.getPort())).start();
						}
					} catch (Exception e) {
						System.out.println("接受到的数据不正规 或者解析错误 或者启动修改线程失败！"+e.getMessage());
						info = "接受到的数据不正规 或者解析错误 或者启动修改线程失败！"+e.getMessage();
						// 发送
						UDPServer.Send(info, a[1], packet.getPort());
						continue;
					}
					info = "true";

					// 发送
					UDPServer.Send(info, a[1], packet.getPort());
				}

			} catch (Exception e) {
				System.out.println("UDP监听异常内" + e);
			} finally {
				if (server != null) {
					server.close();
				}
				try {

					// 再次启动 UDP监听
					UDPServer.Startup();
				} catch (Exception e2) {
					System.out.println("二次启动失败！请重启服务器！" + e2.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.println("UDP监听异常外");
		}

	}
}
