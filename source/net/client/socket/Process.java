package net.client.socket;

import java.io.*;
import java.net.*;

class Process extends Thread{
	
	private int cProcessOnThread = 10;
	private String hostnameServer = "localhost";
	private int portServer = 2211;
	private Socket sock = null;

	public void run(){
		try{
			for(int i=0; i<cProcessOnThread; i++){
				msgSend();
			}
			System.out.println("Socket close...");
		}catch(IOException e){
			e.printStackTrace();
			if(sock!=null){
				try{sock.close();}catch(IOException ioe){}
				System.out.println("Socket close exeption...");
			}
		}
	}

	private void msgSend() throws IOException{
		String tName = "["+Thread.currentThread().getName()+"]";
		InetSocketAddress isa = new InetSocketAddress(hostnameServer, portServer);
		sock = new Socket();
		sock.connect(isa);

		OutputStream os = sock.getOutputStream();
		InputStreamReader isr = new InputStreamReader(sock.getInputStream());
		BufferedOutputStream out = new BufferedOutputStream(os);
		BufferedReader in = new BufferedReader(isr);

		String request = "message one";
		out.write(request.getBytes());
		out.flush();
		System.out.println(tName + " Client : " + request);

		StringBuilder sb = new StringBuilder();
		char[] buf = new char[1024];
		int r = in.read(buf, 0, buf.length);
		sb.append(buf, 0, r);
		String respon = sb.toString();
		System.out.println(tName + " Server : " + respon);

		sb = new StringBuilder();
		request = "message two";
		out.write(request.getBytes());
		out.flush();
		System.out.println(tName + " Client : " + request);

		buf = new char[1024];
		r = in.read(buf, 0, buf.length);
		sb.append(buf, 0, r);
		respon = sb.toString();
		System.out.println(tName + " Server : " + respon);

		sock.close();
	}
}