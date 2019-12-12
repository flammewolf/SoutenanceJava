package com.tp.src.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.tp.src.Message;

public class ConnectedClient implements Runnable{

	private static int idCounter = 0; 
	private int id; 
	private Socket socket; 
	private ObjectOutputStream out; 
	private ObjectInputStream in; 
	Server server; 
	
	public ConnectedClient(Server server, Socket socket) {
		this.id = ++idCounter;
		this.server = server; 
		this.socket = socket;
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Nouvelle connexion du client : " + id);
	}

	public int getId() {
		return this.id; 
	}
	
	@Override
	public void run() {
		boolean isActive = true; 
		try {			
			while(isActive) {
				Message msg = new Message();
				msg.setContent(in.readObject().toString());
				if(msg != null) {
					msg.setSender(String.valueOf(id));
					server.broadcastMessage(msg, id);
				}else {
					server.disconnectedClient(this);
					isActive = false; 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Message sendMessage(Message msg)  {
		
		try {
			this.out.writeObject(msg.getContent());
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msg;  
	}
	
	public void closeClient() throws IOException {
		this.in.close();
		this.out.close();
		this.socket.close();
	}
	
	

}
