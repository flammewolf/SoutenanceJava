package com.tp.src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable{
	
	private Server server; 
	private ServerSocket serverSocket;
	
	public Connection(Server server) {
		this.server = server; 
		try {
			this.serverSocket = new ServerSocket(server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Socket socketNewClient = serverSocket.accept();
				ConnectedClient newClient = new ConnectedClient(server, socketNewClient);
				server.addClient(newClient);
				
				Thread thread = new Thread(newClient);
				thread.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
