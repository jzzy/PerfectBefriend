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
 * UDP��������
 */
public class UDPServer {
	// �����õ�
	public static DatagramSocket ds = null;// �ҵ� �˿ں�

	// ����������
	public static void Startup() throws SocketException {
		if (ds == null) {
			try {
				ds = new DatagramSocket(6500);
				// UDP����
				new Thread(new UDPThread(ds)).start();
			} catch (Exception e) {
				System.out.println("����UDP�����쳣��" + e.getMessage());
			}

		}
	}

	/**
	 * ͨ��UDP����
	 * 
	 * @param info
	 * @throws IOException
	 */
	public static Boolean Send(String info, String ip, int port)
			throws IOException {

		System.out
				.println("UDP���͵�����:"
						+ info + ip + port);
		// ���� byte���� UDP ��� ���� ��СΪ 64kb
		byte[] buf = info.getBytes();// װ��Ϊ byte����
		// ��ȡ�ֽ�
		int ibu = buf.length;
		System.out.println("���͵��ֽڴ�С" + ibu);
		// ���ܴ���65536
		if (ibu > 1472) {
			System.out.println("���ͳ��ȳ���1472b");
			return false;
		}

		DatagramPacket dt = new DatagramPacket(buf, 0, buf.length,
				InetAddress.getByName(ip), port);
		if (ds == null) {
			try {

				ds = new DatagramSocket(6500);
				// UDP����
				new Thread(new UDPThread(ds)).start();

			} catch (Exception e) {
				System.out.println("2������UDP�����쳣��" + e.getMessage());
			}
		}
		ds.send(dt);
		System.out.println("�ҵ�port:" + ds.getLocalPort());
		System.out.println("���͸���ipΪ" + ip + "port:" + port);

		return true;

	}

}
