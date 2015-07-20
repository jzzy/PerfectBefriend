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
			//��ȡ�����
			OutputStream out = s.getOutputStream();
			// ��װ �����
			DataOutputStream dos = new DataOutputStream(out);
			
		
				
				//��������
				try {
					if(text!=null){
					dos.writeUTF(text+"   ʱ����:"+OpeFunction.getNowTime());	
					System.out.println("�ҷ��͵���:"+text); 
					dos.close();
					out.close();
					}
				} catch (Exception e) {
					
					System.out.println("���ͷ����쳣"+e.getMessage());
					
				}
				text=null;
			
			
	
		} catch (Exception e) {
			System.out.println("���Ͷ��쳣"+e);
			
		}
		finally{
			
			
		}

	}
	
	

}


