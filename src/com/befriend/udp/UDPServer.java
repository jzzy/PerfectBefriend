package com.befriend.udp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.befriend.Thread.UDPThread;

/**
 * UDP建立服务
 */
public class UDPServer {
	// 发送用的
	public static DatagramSocket ds = null;// 我的 端口号

	// 启动服务器
	public static void Startup() throws SocketException {
		if (ds == null) {
			try {
				ds = new DatagramSocket(6500);
				// UDP监听
				new Thread(new UDPThread(ds)).start();
			} catch (Exception e) {
				System.out.println("启动UDP监听异常！" + e.getMessage());
			}

		}
	}

	/**
	 * 通过UDP发送
	 * 
	 * @param info
	 * @throws IOException
	 */
	public static Boolean Send(String info, String ip, int port)
			throws IOException {

		System.out
				.println("UDP发送的内容:"
						+ info + ip + port);
		// 声明 byte数组 UDP 最大 发送 大小为 64kb
		byte[] buf = info.getBytes();// 装换为 byte类型
		// 获取字节
		int ibu = buf.length;
		System.out.println("发送的字节大小" + ibu);
		// 不能大于65536
		if (ibu > 1472) {
			System.out.println("发送长度超过1472b");
			return false;
		}

		DatagramPacket dt = new DatagramPacket(buf, 0, buf.length,
				InetAddress.getByName(ip), port);
		if (ds == null) {
			try {

				ds = new DatagramSocket(6500);
				// UDP监听
				new Thread(new UDPThread(ds)).start();

			} catch (Exception e) {
				System.out.println("2次启动UDP监听异常！" + e.getMessage());
			}
		}
		ds.send(dt);
		System.out.println("我的port:" + ds.getLocalPort());
		System.out.println("发送给了ip为" + ip + "port:" + port);

		return true;

	}

}
