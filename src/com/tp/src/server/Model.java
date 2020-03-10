package com.tp.src.server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tp.src.bdd.UserCon;
import com.tp.src.bdd.UserNew;


public class Model {

	public ResultSet  bddConnection(String query) {
		ResultSet rs = null;
		try {
			java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/tpjava","root","");  
			Statement stmt=con.createStatement();  
			 rs=stmt.executeQuery(query);  
			 
			}catch(Exception e){ System.out.println(e);}
			
		return rs;
		
	}
	
	
	public void AddUser(UserNew user, String secretKey) 
	{
		ResultSet rs;
		String password = AES.encrypt(user.password, secretKey);
		String query = "INSERT INTO user (username, password) VALUES ('"+user.login+"', '"+password+"')";
		rs = bddConnection(query);
	}
	
	
	}