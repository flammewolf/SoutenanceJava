package com.tp.src.server;

import java.io.IOException;
import java.util.ArrayList;

import com.tp.src.Message;

public class Server {
	
	private int port; 
	private ArrayList<ConnectedClient> listClients;
	
	public Server(int port) {
		this.port = port; 
		this.listClients = new ArrayList<ConnectedClient>();
		Thread thread = new Thread(new Connection(this));
		thread.start();
		
	}
	
	public int getPort() {
		return this.port; 
	}
	
	public ArrayList<ConnectedClient> getListClients(){
		return this.listClients;
	}
	
	public void addClient(ConnectedClient newClient) throws IOException {
		for(ConnectedClient client : listClients) {
			client.sendMessage(new Message("server", "Le client " + newClient.getId() + " vient de se connecter.\n"));
		}
		this.listClients.add(newClient);
	}
	
	public void broadcastMessage(Message msg, int id) throws IOException {
		for(ConnectedClient client : listClients) {
			client.sendMessage(msg);
		}
	}

	public void disconnectedClient(ConnectedClient connectedClient) throws IOException {
		for(ConnectedClient client : listClients) {
			client.sendMessage(new Message("server", "Le client " + connectedClient.getId() + " s'est déconnecté. \n"));
		}
		connectedClient.closeClient();
	}
}
