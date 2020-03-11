/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;

/**
 *
 * @author p1623040
 */
public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                printUsage();
            } else {
                int port = Integer.parseInt(args[0]);
                Server server = new Server(port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printUsage() {
        System.out.println("java server.Server <port>");
        System.out.println("\t<port>: servers' port");
    }   
}
