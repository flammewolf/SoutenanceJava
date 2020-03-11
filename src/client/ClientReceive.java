/*
 * Auteur : Jules BOURDAIS
 */
package client;

import common.Message;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketException;
import javafx.application.Platform;

/**
 * Classe encapsulant le thread qui tourne en permanence pour écouter les messages entrants
 */
public class ClientReceive implements Runnable {
    private Client client;
    private Socket socket;
    private ObjectInputStream in;
    
    /**
     * 
     * @param client Le client
     * @param socket Le Socket sur lequel on écoute
     */
    public ClientReceive(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
            boolean isActive = true;
            while(isActive) {
                Message mess = (Message) in.readObject();
                if (mess != null) {
                    Platform.runLater(() -> ClientReceive.this.client.messageReceived(mess));
                } else {
                    isActive = false;
                }
            }
            client.disconnectedServer();
        } catch (IOException e) {
            if ( e instanceof SocketException ) {
                try {
                    this.client.disconnectedServer();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}