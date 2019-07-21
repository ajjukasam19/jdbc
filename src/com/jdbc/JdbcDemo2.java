package com.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.jdbc.Constants.INSERT_QUERY;
import static com.jdbc.utility.DbUtility.getConnection;
public class JdbcDemo2 {
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
	
		try(Connection conn=getConnection()){
			
			PreparedStatement pstmt=conn.prepareStatement(INSERT_QUERY);
			System.out.println("inserting multiple row values into the table class");
		 	String ins1 = "INSERT INTO class2 values (1,'pencil'\t, 12)";
			String ins2 = "INSERT INTO class2 values (2, 'book'\t,10)";
			String ins3 = "INSERT INTO class2 values (3, 'note'\t,  11)";
			String ins4 = "INSERT INTO class2 values (4,'horse'\t, 45)";
				try {
					 //Assume a valid connection object conn
				conn.setAutoCommit(false);
					pstmt.executeUpdate (ins1); // insert the values
				    pstmt.executeUpdate (ins2);
				    pstmt.executeUpdate (ins3);
				    pstmt.executeUpdate (ins4);
				   // String ins5 = "INSERT INTO class2 values (5,'table'\t,  34)";
					
				    String ins5 = "INSERT INTO class2 values (4,'table'\t,  34)";   
					    
				    if(INSERT_QUERY.contains("id")){
				    	System.out.println("adding faled!! it already conatins same id value");
				    	//conn.close();
				    }
				    else{
				  conn.setAutoCommit(false);
				  pstmt.executeUpdate (ins5);
				  conn.commit();
				  System.out.println("commited and Added Successfully!");
				  //conn.close(); 	 
				    }
				} catch (SQLException sqlEx) {
			            
			         // If there is any error,rollback to previous state.
					System.out.println("rolled back and shown previous values");
			           conn.rollback(); 
			          // conn.close();
			        }
			
			String sql = "SELECT * from class2";
				try {
					 //STEP 5: Extract data from resultset
				    ResultSet rs = pstmt.executeQuery (sql); // execute the query
				    while (rs.next ()) {
				    	 //retrieve the multiple records from database table      
				    	 int id  = rs.getInt("id");        
				         String name = rs.getString("name");
				         Double price = rs.getDouble("price");
				         
				         //Display the all records from table
				         System.out.println(id + "\t" + name +"\t" + price );
			
				    }
				} catch (SQLException sqlEx) {
				    System.out.println ("Could not get results:" + 
									sqlEx.toString ());
				}
		
						try {
					    pstmt.close (); // Close the statement
					    conn.close (); // Close the connection
						} catch (SQLException sqlEx) {
					    System.out.println ("Could not close connection:" + 
										sqlEx.toString ());
						}
						}
	
		
	}  
}
