    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quentin
 */
public class Server {
    private int port;
    private List<ConnectedClient> clients;
    
    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<ConnectedClient>();
        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }
    
    public void addClient(ConnectedClient newClient) throws IOException {
        for (ConnectedClient client : clients) {
            client.sendMessage(new Message("Serveur", "Le client " + newClient.getId() + " vient de se connecter"));
        }
        this.clients.add(newClient);
    }
    
    public void broadcastMessage(Message mess, int id) throws IOException {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }
    }
    
    public void disconnectedClient(ConnectedClient discClient) throws IOException {
        discClient.closeClient();
        clients.remove(discClient);
        System.out.println("Connexion fermée : Le Client " + discClient.getId() + " nous a quitté.");
        for (ConnectedClient client : clients) {
            client.sendMessage(new Message("Serveur", "Le client " + discClient.getId() + " nous a quitté"));
        }
    }
    
    public int getPort() {
        return this.port;
    }
}
