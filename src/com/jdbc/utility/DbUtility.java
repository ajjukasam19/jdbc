package com.jdbc.utility;
import static com.jdbc.Constants.*;

/*
modified by karthik
import static com.jdbc.Constants.DRIVER;
import static com.jdbc.Constants.PASSWORD;
import static com.jdbc.Constants.URL;
import static com.jdbc.Constants.USERNAME;
*/


import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtility {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		
		
Properties prop= new Properties();
		
		try(FileInputStream fis= new FileInputStream("db.properties")){
			prop.load(fis);
			System.out.println("connection is established:"+prop.getProperty("db.url"));
			driver=prop.getProperty(DRIVER);
			url=prop.getProperty(URL);
			username=prop.getProperty(USERNAME);
			password=prop.getProperty(PASSWORD);
		
		
		} catch (IOException e) {
				System.err.println("unable to read the connection");
				return null;
		}
		Class.forName(driver);
		Connection conn= DriverManager.getConnection(url,username,password);
		return conn;
		
	}
}
