package net.server.socket;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Application{
	public static void main(String[]args){
		ServerSocket sockServer = null;
		Socket sock = null;
		int cThread = 10;
		int port = 2211;

		ExecutorService executor = Executors.newFixedThreadPool(cThread);

		try{
			sockServer = new ServerSocket(port);
			System.out.println("Connection open on "+port+"...");
			while(true){
				sock = sockServer.accept();
				System.out.println("Accept Connection...");
				Runnable worker = new Process(sock);
				executor.execute(worker);
			}
		}catch(IOException e){
			e.printStackTrace();
			if(sock!=null){
				try{
					sock.close();
					sockServer.close();
					executor.shutdown();
				}catch(IOException IEO){}
			}
		}finally{
			if(sock!=null){
				try{
					sock.close();
					sockServer.close();
					System.out.println("Close All Socket...");
				}catch(IOException e){}
			}

			executor.shutdown();
		}
	}
}