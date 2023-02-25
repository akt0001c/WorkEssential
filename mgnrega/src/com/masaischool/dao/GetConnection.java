package com.masaischool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetConnection {
     final static String url;
     final static String user;
     final static String pass;
     
     static {
    	 try {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
    	 }catch(ClassNotFoundException ex) {
    		 System.out.println("Unable to access server");
    	 }
    	 ResourceBundle rs = ResourceBundle.getBundle("dbdetails");
    	 url= rs.getString("url");
    	 user= rs.getString("user");
    	 pass= rs.getString("password");
    	 
     }
     
     public static Connection connectToDatabase() throws SQLException {
    	 return DriverManager.getConnection(url, user, pass);
     }
     
     public static void closeConnection(Connection conn) throws SQLException {
    	 conn.close();
     }
}
