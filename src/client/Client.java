/*
 * Auteur : Jules BOURDAIS
 */
package client;

import common.Message;
import gui.ClientPanel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe encapsulant les interactions avec le serveur
 */
public class Client {
    private String address;
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public ClientPanel clientPanel;
    
    /**
     * Constructeur de la classe Client
     * @param address L'addresse du serveur distant
     * @param port Le port sur lequel le serveur distant écoute
     * @throws UnknownHostException
     * @throws IOException 
     */
    public Client(String address, int port) throws UnknownHostException, IOException {
        this.address = address;
        this.port = port;
        InetAddress ipAddress = InetAddress.getByName(address);
        this.socket = new Socket(ipAddress, port);
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
        Thread receiverThread = new Thread(new ClientReceive(this, this.socket));
        receiverThread.start();
    }
    
    /**
     * Méthode appelée quand le serveur met fin à la connexion
     * @throws IOException 
     */
    public void disconnectedServer() throws IOException {
        System.out.println("Oups, il semblerait que le serveur ait mit fin à la connexion !");
        if (this.in != null) {
            this.in.close();
        }
        if ( this.out != null) {
            this.out.close();
        }
        if ( this.socket != null) {
            this.socket.close();
        }
        System.out.println("Extinction du programme.");
        System.exit(0);
    }
    
    /**
     * Méthode servant à envoyer un message
     * Cette méthode créer un nouveau thread
     * @param message Le message à envoyer
     */
    public void sendMessage(String message) {
        Thread senderThread = new Thread(new ClientSend(this.socket, this.out, message));
        senderThread.start();
    }
    
    /**
     * Méthode appelée quand un message est reçue
     * @param message Le message reçue
     */
    public void messageReceived(Message message) {
        this.clientPanel.showNewMessage(message);   // Mise à jour de l'interface graphique
    }
}