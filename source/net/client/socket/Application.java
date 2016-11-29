package net.client.socket;

class Application{
	public static void main (String[]args){
		int cThread = 10;
		for(int i=0; i<cThread; i++)
			new Process().start();
	}
}