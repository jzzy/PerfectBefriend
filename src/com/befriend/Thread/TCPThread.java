package com.befriend.Thread;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPThread implements Runnable {

	private ServerSocket ss;

	public TCPThread(ServerSocket ss) {
		super();
		this.ss = ss;
	}

	@Override
	public void run() {
		try {

			// ���� ����
			while (true) {
				try {

					System.out.println("TCP�ȴ��ͻ���...");

					// �����µĿͻ���
					Socket s = ss.accept();
					System.out.println("TCP�ͻ���������...");
					
					// �����߳�
					new Thread(new TCPAcceptThreads(s)).start();

					System.out.println("������1���ո��߳�...");
				} catch (Exception e) {
					System.out.println("TCPThread ���ܿͻ��������쳣��"+e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println("TCP�߳��쳣" + e);

		}

	}
	

}
