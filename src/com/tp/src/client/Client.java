package com.tp.src.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tp.src.Message;

public class Client {
	
	private String address; 
	private int port; 
	private Socket socket; 
	private ObjectInputStream in; 
	private ObjectOutputStream out; 
	
	public Client(String address, int port){
		this.address = address; 
		this.port = port; 
		try {
			this.socket = new Socket(address, port);
			this.out = new ObjectOutputStream(socket.getOutputStream());
			Thread t1 = new Thread(new ClientSend(this.out, this.socket));
			Thread t2 = new Thread(new ClientReceive(this, this.socket));
			
			t1.start();
			t2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnectedServer() throws IOException {
		if(this.in != null) {
			this.in.close();
		}
		
		if(this.out != null) {
			this.out.close();
		}
		
		if(this.socket != null) {
			this.socket.close();
		}
		
		System.exit(0);
	}

	public void messageReceived(Message msg) {
		System.out.println("Message reçu de " + msg.getSender() + " : " + msg.getContent());
	}
}
