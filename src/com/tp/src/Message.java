package com.tp.src;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

public class Message implements Serializable{
	
	private String sender; 
	private String content; 
	
	public Message() {
		
	}
	
	public Message(String sender, String content) {
		super();
		this.sender = sender; 
		this.content = content; 
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void setContent(String content) {
		this.content = content; 
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String toString() {
		return content;
	}
}
