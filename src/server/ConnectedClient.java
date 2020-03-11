/*
 * Auteur : Jules BOURDAIS
 */
package server;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Classe représentant un client connecté au serveur
 */
public class ConnectedClient implements Runnable {
    private static int idCounter = 0;
    private int id;
    private Server server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    /**
     * Constructeur de la classe ConnectedClient
     * @param server Le serveur
     * @param socket Le socket du serveur
     * @throws IOException 
     */
    public ConnectedClient(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.id = idCounter;
        idCounter++;
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(" Une Nouvelle personne arrive : id = " + id);
    }
    
    /**
     * Méthode servant à envoyer un message au client
     * @param mess Le message à envoyer
     * @throws IOException 
     */
    public void sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
    }
    
    /**
     * Méthode servant à fermer la connexion du client
     * @throws IOException 
     */
    public void closeClient() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
    
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            boolean isActive = true;
            while(isActive) {
                Message mess = (Message) in.readObject();
                if ( mess != null ) {
                    mess.setSender(String.valueOf(id));
                    server.broadcastMessage(mess, id);
                } else {
                    server.disconnectedClient(this);
                    isActive = false;
                }   
            }
        } catch (IOException e) {
            if ( e instanceof SocketException ) {
                try {
                    server.disconnectedClient(this);
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
    
    /**
     * Accesseur de l'id du client
     * @return int l'id du client
     */
    public int getId() {
        return this.id;
    }
}