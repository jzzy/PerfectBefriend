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

			// 接受 链接
			while (true) {
				try {

					System.out.println("TCP等待客户端...");

					// 接受新的客户端
					Socket s = ss.accept();
					System.out.println("TCP客户端链接了...");
					
					// 接收线程
					new Thread(new TCPAcceptThreads(s)).start();

					System.out.println("启动了1接收个线程...");
				} catch (Exception e) {
					System.out.println("TCPThread 接受客户端连接异常！"+e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println("TCP线程异常" + e);

		}

	}
	

}
