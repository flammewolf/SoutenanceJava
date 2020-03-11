/*
 * Auteur : Jules BOURDAIS
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
        textToSend.setLayoutY(430);
        textToSend.setPrefHeight(60);
        textToSend.setPrefWidth(290);
        
        this.scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(20);
        scrollReceivedText.setPrefHeight(400);
        scrollReceivedText.setPrefWidth(350);
        
        this.receivedText = new TextFlow();
        receivedText.setLayoutX(50);
        receivedText.setLayoutY(20);
        receivedText.setPrefHeight(20);
        receivedText.setPrefWidth(40);
        
        this.sendBtn = new Button();
        sendBtn.setLayoutX(350);
        sendBtn.setLayoutY(430);
        sendBtn.setPrefHeight(20);
        sendBtn.setPrefWidth(50);
        sendBtn.setText("Send");
        
        this.clearBtn = new Button();
        clearBtn.setLayoutX(350);
        clearBtn.setLayoutY(460);
        clearBtn.setPrefHeight(20);
        clearBtn.setPrefWidth(50);
        clearBtn.setText("Clear");
        
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
