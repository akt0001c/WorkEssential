package com.essential.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class MyServerStarter {
   private final static String URL;
   private final static String USERNAME;
   private final static String PASSWORD;
   
   
   static {
	   try {
		     Class.forName("com.mysql.cj.jdbc.Driver");
	   }catch(ClassNotFoundException ex) {
		   
	   }
	   
	   ResourceBundle rs= ResourceBundle.getBundle("dbdetails");
	   URL= rs.getString("url");
	   USERNAME= rs.getString("user");
	   PASSWORD= rs.getString("password");
   }
   
   public static Connection connectToDatabase() throws SQLException {
	 return  DriverManager.getConnection(URL, USERNAME, PASSWORD);
   }
   
   public static void closeConnection(Connection conn) throws SQLException
   {
	   conn.close();
   }
   
}
