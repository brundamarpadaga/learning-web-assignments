package com.learning.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoticesController {
	

private PreparedStatement insert = null;
private PreparedStatement update = null;
private Statement stmt = null;
private Connection cnx = null;
private ResultSet rs = null;
    public void addEntry(int id, String name, String phone,int noticeId, String title, String content, int contactId) {
    	
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
          cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
              "brunda",
              "lpkoji156");
    	String sql1 = "INSERT INTO contacts (id, name, phone) VALUES (?, ?, ?)";
        String sql2  = "INSERT INTO notices (id, title, content, contact_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(sql1);
            PreparedStatement statement2 = cnx.prepareStatement(sql2);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, phone);
            statement2.setInt(1, noticeId);
            statement2.setString(2, title);
            statement2.setString(3, content);
            statement2.setInt(4, contactId);
            statement.executeUpdate();
            statement2.executeUpdate();
            statement.close();
            statement2.close();
            cnx.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    
}
