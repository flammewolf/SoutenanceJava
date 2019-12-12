package com.tp.src.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.tp.src.Message;

public class ClientSend implements Runnable{

	private ObjectOutputStream out; 
	private Socket socket; 
	
	public ClientSend(ObjectOutputStream out, Socket socket) {
		this.socket = socket; 
		this.out = out;
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Votre message : ");
			String str = sc.nextLine();
			Message msg = new Message("client", str);

			try {
				out.writeObject(msg);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
