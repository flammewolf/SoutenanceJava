package com.tp.src.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.tp.src.Message;

public class ClientReceive implements Runnable{

	private Client client; 
	private ObjectInputStream in; 
	private Socket socket;
	
	public ClientReceive(Client client, Socket socket) {
		this.client = client; 
		this.socket = socket; 
	}
	
	@Override
	public void run() {
		boolean isActive = true; 
		try {
			in = new ObjectInputStream(socket.getInputStream());
			while(isActive) {
				Message msg = new Message(); 
				msg.setSender("user");
				msg.setContent(in.readObject().toString());
				if(msg != null) {
					this.client.messageReceived(msg);
				}else {
					this.client.disconnectedServer();
					isActive = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
