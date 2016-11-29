package net.server.socket;

import java.net.*;
import java.io.*;

class Process extends Thread {

	private Socket sock = null;

	Process(Socket sock){
		this.sock = sock;
	}

	public void run(){
		try{
			String tName = "["+Thread.currentThread().getName()+"]";
			System.out.println(tName+" Processing...");

			OutputStream os = sock.getOutputStream();
			InputStreamReader isr = new InputStreamReader(sock.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(os);
			BufferedReader in = new BufferedReader(isr);

			StringBuilder sb = new StringBuilder();
			char[] buf = new char[1024];
			int r = in.read(buf, 0, buf.length);
			sb.append(buf, 0, r);
			String respon = sb.toString();
			System.out.println(tName+" Client : "+respon);
			String reply = "reply " + respon;
			out.write(reply.getBytes());
			out.flush();
			System.out.println(tName+" Server : "+reply);

			sb = new StringBuilder();
			buf = new char[1024];
			r = in.read(buf, 0, buf.length);
			sb.append(buf, 0, r);
			respon = sb.toString();
			System.out.println(tName+" Client : "+respon);
			reply = "reply " + respon;
			out.write(reply.getBytes());
			out.flush();
			System.out.println(tName+" Server : "+reply);

			sock.close();
			System.out.println(tName+" Close Connection...");
		}catch(IOException e){e.printStackTrace();}
	}
}