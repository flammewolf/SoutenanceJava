/*
 * Auteur : Quentin
 */
package gui;

import client.Client;
import common.Message;
import java.io.IOException;
import java.net.UnknownHostException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * Classe de l'interface graphique client
 */
public class ClientPanel extends Parent {
    private TextArea textToSend;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;
    private Client client;
    
    /**
     * Constructeur de la classe ClientPanel
     * @param client  L'objet client qui encapsule les interactions avec le serveur
     * @throws UnknownHostException
     * @throws IOException 
     */
    public ClientPanel(Client client) throws UnknownHostException, IOException {
        this.client = client;
        this.textToSend = new TextArea();
        textToSend.setLayoutX(50);
        textToSend.setLayoutY(440);
        textToSend.setPrefHeight(70);
        textToSend.setPrefWidth(440);
        
        this.scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(20);
        scrollReceivedText.setPrefHeight(400);
        scrollReceivedText.setPrefWidth(550);
        
        this.receivedText = new TextFlow();
        receivedText.setLayoutX(50);
        receivedText.setLayoutY(20);
        receivedText.setPrefHeight(20);
        receivedText.setPrefWidth(40);
        
        this.sendBtn = new Button();
        sendBtn.setLayoutX(500);
        sendBtn.setLayoutY(430);
        sendBtn.setPrefHeight(20);
        sendBtn.setPrefWidth(100);
        sendBtn.setText("Envoyer");
        
        this.clearBtn = new Button();
        clearBtn.setLayoutX(500);
        clearBtn.setLayoutY(470);
        clearBtn.setPrefHeight(20);
        clearBtn.setPrefWidth(100);
        clearBtn.setText("Effacer");
        
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
        
        setListener();
        
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);
        this.getChildren().add(sendBtn);
    }
    
    /**
     * Méthode servant à placer les écouteurs d'évènements sur les boutons clear et send
     * Déclenche les actions appropriées
     */
    private void setListener() {
        this.sendBtn.setOnAction( event -> ClientPanel.this.client.sendMessage(ClientPanel.this.textToSend.getText()));
        this.clearBtn.setOnAction( event -> ClientPanel.this.textToSend.clear());
    }
    
    /**
     * Méthode servant à afficher un message reçu à l'écran
     * @param message  Le message a afficher à l'écran 
     */
    public void showNewMessage(Message message) {
        
        Label label = new Label(message.toString());
        label.setMinWidth(300);
        this.receivedText.getChildren().add(label);
    }
 
}
