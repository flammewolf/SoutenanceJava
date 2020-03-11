/*
 * Auteur : Quentin
 */
package gui;

import client.Client;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale du programme côté l'interface graphique
 */
public class MainGui extends Application {

    @Override
    public void start(Stage stage) {
        try {
            List<String> arguments = this.getParameters().getRaw();
            if (arguments.size() != 2) {
                printUsage();
            } else {
                String address = arguments.get(0);
                int port = Integer.parseInt(arguments.get(1));
                Client client = new Client(address, port);
                
            }

            Parent root = FXMLLoader.load(getClass().getResource("seConnecter.fxml")); //lancement du FMXL 
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Affichage des informations à connaître afin de lancer l'interface en
     * ligne de commande
     */
    private static void printUsage() {
        System.out.println("java gui.MainGui <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
    }
}
