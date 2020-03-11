/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author Quentin
 */
public class Message implements Serializable {
    private String sender;
    private String content;
    
    public Message(String sender, String content)
    {
        this.sender = sender;
        this.content = content;
    }
    
    public String toString() {
         return "Message de : " + this.sender + "\nContenu du message : " + this.content;
    }
 
    public void setSender(String id) {
        this.sender = id;
    }
    
    public String getSender() {
        return this.sender;
    }
}
