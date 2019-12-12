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
        
        this.scrollReceivedText = new ScrollPane();
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefWidth(400);
        scrollReceivedText.setPrefHeight(350);
        
        this.receivedText = new TextFlow();
        
        this.sendBtn = new Button();
        
        this.clearBtn = new Button();
     
        
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
