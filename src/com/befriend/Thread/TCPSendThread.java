package com.befriend.Thread;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.befriend.util.OpeFunction;

public class TCPSendThread  implements Runnable {

	public Socket s;
	public String text="";
	public TCPSendThread(Socket s,String text) {
		super();
		this.s = s;
		this.text=text;

	}
	
	
	@Override
	public void run() {
		
		try {
			//获取输出流
			OutputStream out = s.getOutputStream();
			// 包装 输出流
			DataOutputStream dos = new DataOutputStream(out);
			
		
				
				//发送内容
				try {
					if(text!=null){
					dos.writeUTF(text+"   时间是:"+OpeFunction.getNowTime());	
					System.out.println("我发送的是:"+text); 
					dos.close();
					out.close();
					}
				} catch (Exception e) {
					
					System.out.println("发送方法异常"+e.getMessage());
					
				}
				text=null;
			
			
	
		} catch (Exception e) {
			System.out.println("发送端异常"+e);
			
		}
		finally{
			
			
		}

	}
	
	

}


