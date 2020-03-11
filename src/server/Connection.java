/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 *
 * @author p1623040
 */
public class Connection implements Runnable {
    private Server server;
    private ServerSocket serverSocket;
    
    public Connection(Server server) throws IOException {
        this.server = server;
        this.serverSocket = new ServerSocket(server.getPort());
    }
    
    public void run() {
        try {
            while(true) {
                Socket sockNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
                server.addClient(newClient);
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
