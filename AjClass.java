package Ajay;

import java.sql.*;
public class AjClass { 

	public static void main (String args []) throws SQLException  {
	Connection conn = null;
	Statement stmt = null;
		try {
		     //STEP 2: Register JDBC driver
			//STEP 3: Open a connection
		    conn = DriverManager.getConnection 
			("jdbc:oracle:thin:@db.csep.umflint.edu:1521:csep",
					"akasam", "akasam"); // Connect to DB
		    stmt = conn.createStatement (); // Create statement
		} catch (SQLException ex) {
		    System.out.println ("Could not create Statement:" + 
						ex.toString ());
	        }
	//STEP 4: Execute a query
	System.out.println("creating table a class");
	String sql = "create table class (courseid int, cname varchar2(10),majors varchar2(5), departments varchar2(20))";
		try {
		    stmt.executeUpdate (sql); // create the table
		} catch (SQLException sqlEx) {
		    System.out.println ("Could not create table:" + 
						sqlEx.toString ());
		}

	System.out.println("inserting multiple rowvalues into the table class");
 	String ins1 = "INSERT INTO class values (571,'quant'\t,    'cis','computer science')";
	String ins2 = "INSERT INTO class values (370, 'database','csc','computer science')";
	String ins3 = "INSERT INTO class values (580, 'adc'\t,     'csc','computer science')";
	
		try {
			 //Assume a valid connection object conn
		conn.setAutoCommit(false);
			stmt.executeUpdate (ins1); // insert the values
		    stmt.executeUpdate (ins2);
		    stmt.executeUpdate (ins3);
		   String ins4 = "INSERT INTO class values (271,'his'\t,    'cis','computer science')";
		   String ins5 = "INSERT INTO class values (571,'his'\t,    'cis','computer science')";
		   
		   if (ins1.contains("271")||ins2.contains("271")||ins3.contains("271")) {
		    	System.out.println("Add failed!already exiting the course id value");
		    	conn.close();
		          }
		      else {
		    	  conn.setAutoCommit(false);
		          //String ins4 = "INSERT INTO class values (271,'his'\t,    'cis','computer science')";
		          stmt.executeUpdate (ins4);
		          stmt.executeUpdate (ins5);
				  		    
				    conn.commit();
		          System.out.println("Added Successfully!");
		    	  
		    	  //System.out.println("Add failed!already exiting the course id value");
		      }
		
		} catch (SQLException sqlEx) {
	            System.out.println ("Could not create table:" 
						+ sqlEx.toString ());
	         // If there is any error,rollback to previous state.
	
	           conn.rollback(); 
	        }
	
	sql = "SELECT * from class";
		try {
			 //STEP 5: Extract data from resultset
		    ResultSet rs = stmt.executeQuery (sql); // execute the query
		    while (rs.next ()) {
		    	 //retrieve the multiple records from database table      
		    	 int coruseid  = rs.getInt("courseid");        
		         String classname = rs.getString("cname");
		         String major = rs.getString("majors");
		         String department = rs.getString("departments");
	
		         //Display the all records from table
		         System.out.println(coruseid + "\t" + classname +"\t" + major + "\t" + department);
	
		    }
		} catch (SQLException sqlEx) {
		    System.out.println ("Could not get results:" + 
							sqlEx.toString ());
		}
		
				try {
			    stmt.close (); // Close the statement
			    conn.close (); // Close the connection
				} catch (SQLException sqlEx) {
			    System.out.println ("Could not close connection:" + 
								sqlEx.toString ());
				}
	    }
	
	}
