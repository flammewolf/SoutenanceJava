/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp.src.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

/**
 *
 * @author p1925548
 */
public class ClientPanel extends Parent{
    
    private TextArea textToSend;
    private ScrollPane scrollReceivedText;
    private TextFlow receivedText;
    private Button sendBtn;
    private Button clearBtn;

    public ClientPanel() {
        
        this.textToSend = new TextArea();
        textToSend.setLayoutX(80);
        textToSend.setLayoutY(550);
        textToSend.setPrefWidth(650);
        textToSend.setPrefHeight(100);
        
        this.scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefWidth(575);
        scrollReceivedText.setPrefHeight(300);
        
        this.receivedText = new TextFlow();
        
        
        this.sendBtn = new Button();
        sendBtn.setText("Envoyer");
        sendBtn.setLayoutX(750); // position longueur
        sendBtn.setLayoutY(575);
        
        this.clearBtn = new Button();
        clearBtn.setText("Effacer");
        clearBtn.setLayoutX(750);
        clearBtn.setLayoutY(650);
        
     
        
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);
        this.getChildren().add(clearBtn);        
        this.getChildren().add(sendBtn);
    }

    public TextArea getTextToSend() {
        return textToSend;
    }

    public void setTextToSend(TextArea textToSend) {
        this.textToSend = textToSend;
    }

    public ScrollPane getScrollReceivedText() {
        return scrollReceivedText;
    }

    public void setScrollReceivedText(ScrollPane scrollReceivedText) {
        this.scrollReceivedText = scrollReceivedText;
    }

    public TextFlow getReceivedText() {
        return receivedText;
    }

    public void setReceivedText(TextFlow receivedText) {
        this.receivedText = receivedText;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(Button sendBtn) {
        this.sendBtn = sendBtn;
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(Button clearBtn) {
        this.clearBtn = clearBtn;
    }

    
    
}
