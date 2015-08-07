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

			// ���� byte����
			byte[] buf = new byte[4096];

			// �����������ݰ����洢��buf��
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			System.out.println("UDP������������ʼ..." + OpeFunction.getNowTime());

			try {

				while (true) {

					System.out.println("UDP�������ȴ�����..."
							+ OpeFunction.getNowTime());
					// ���ղ��� ������
					server.receive(packet);
					String uip = new String(packet.getAddress().toString());
					String a[] = uip.split("/");
					System.out.println("UDP����������������ipΪ:" + a[1] + "_ʱ��:"
							+ OpeFunction.getNowTime());
					System.out.println("UDP���������������Զ˿ں�Ϊ:" + packet.getPort());
					String info = new String(packet.getData(), 0,
							packet.getLength());
					System.out.println("UDP���������յ�����Ϣ:" + info);
					try {

						u = OpeFunction.fromJson(info, User.class);

						if (u != null) {
							new Thread(new ModifyThreads(u, a[1],
									packet.getPort())).start();
						}
					} catch (Exception e) {
						System.out.println("���ܵ������ݲ����� ���߽������� ���������޸��߳�ʧ�ܣ�"+e.getMessage());
						info = "���ܵ������ݲ����� ���߽������� ���������޸��߳�ʧ�ܣ�"+e.getMessage();
						// ����
						UDPServer.Send(info, a[1], packet.getPort());
						continue;
					}
					info = "true";

					// ����
					UDPServer.Send(info, a[1], packet.getPort());
				}

			} catch (Exception e) {
				System.out.println("UDP�����쳣��" + e);
			} finally {
				if (server != null) {
					server.close();
				}
				try {

					// �ٴ����� UDP����
					UDPServer.Startup();
				} catch (Exception e2) {
					System.out.println("��������ʧ�ܣ���������������" + e2.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.println("UDP�����쳣��");
		}

	}
}
