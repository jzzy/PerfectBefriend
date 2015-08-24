package com.befriend.udp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import com.befriend.Thread.UDPThread;

/**
 * UDP
 */
public class UDPServer {
	
	public static DatagramSocket ds = null;

	
	public static void Startup() throws SocketException {
		if (ds == null) {
			try {
				ds = new DatagramSocket(6500);
				new Thread(new UDPThread(ds)).start();
			} catch (Exception e) {
			e.printStackTrace();
			}

		}
	}

	public static Boolean Send(String info, String ip, int port)
			throws IOException {

		System.out
				.println("UDP���͵�����:"
						+ info + ip + port);
		// 64kb
		byte[] buf = info.getBytes();
		int ibu = buf.length;
		if (ibu > 1472) {
			System.out.println("1472b");
			return false;
		}

		DatagramPacket dt = new DatagramPacket(buf, 0, buf.length,
				InetAddress.getByName(ip), port);
		if (ds == null) {
			try {

				ds = new DatagramSocket(6500);
				new Thread(new UDPThread(ds)).start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ds.send(dt);
		System.out.println("port:" + ds.getLocalPort());
		System.out.println("ip" + ip + "port:" + port);

		return true;

	}

}
