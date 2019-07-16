package com.jdbc;

import java.sql.*;

public class JdbcDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver"); //load the driver
		System.out.println("driver is loaded");
		//establish the connections
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@db.csep.umflint.edu:1521:csep","aendla", "aendla");
		System.out.println("connection established");
		Statement stmt=conn.createStatement();
		//perform operations

		System.out.println("creating a table");
		
		String sql = "create table class2 (id int not NULL, name varchar2(10), price varchar2(10),Primary key(id))";
		
			try {
			    stmt.executeUpdate (sql);
			    System.out.println("table created");// create the table
			} catch (SQLException sqlEx) {
			    System.out.println ("Could not create table:" + 
							sqlEx.toString ());
			}

	/*	
		String s="delete from Class2 where id=571 ";
		int  a= stmt.executeUpdate(s);
		System.out.println("no ofrowsEffected:"+a);
		*/
		//close the connection
		conn.close();
		System.out.println("connection is closed");
	}
}
