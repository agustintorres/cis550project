import java.sql.*;
import java.util.LinkedList;

public class DatabaseRecorder {
	
	public Connection getConnection() {
		//use JDBC to insert this user into the database
		try {
			// Load the JDBC driver
		    //Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			  
		    // connect
		    Connection conn;
		    conn = DriverManager.getConnection("jdbc:mysql://fling-l.seas.upenn.edu:3306/andrewcc?user=andrewcc&password=databases");
		    
		    return conn;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

	public void recordUser(User u) {
		//use JDBC to insert this user into the database
	  try {
		  
	    // connect to the database
	    Connection conn = getConnection();
	    
	    // create and execute query
	    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USERS (name, password, " + 
	    		                                                            "dob, location, profession) " + 
	    		                                                     "VALUES (?, ?, ?, ?, ?)");
	    pstmt.setString(1, u.getUsername());
	    pstmt.setString(2, u.getPassword());
	    pstmt.setString(3, u.getBirthday());
	    pstmt.setString(4, u.getLocation());
	    pstmt.setString(5, u.getProfession());
	    	     
		System.out.println(pstmt);
	    pstmt.executeUpdate();
		System.out.println("query was executed successfully!");
	    
		// close statement, connection, and output stream
		pstmt.close();
		conn.close();
	    } catch (java.lang.Exception ex) {

	        ex.printStackTrace();
	    }
	}

	public void vote(String uid, int sid) {
		//use JDBC to insert this user into the database
	  try {
		  
	    // connect to the database
	    Connection conn = getConnection();
	    
	    // create and execute query
	    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO VOTES (uid, storyid) " + 
	    		                                                     "VALUES (?, ?)");
	    pstmt.setString(1, uid);
	    pstmt.setString(2, Integer.toString(sid));

		System.out.println(pstmt);
	    pstmt.executeUpdate();
		System.out.println("query was executed successfully!");
	    
		// close statement, connection, and output stream
		pstmt.close();
		conn.close();
	    } catch (java.lang.Exception ex) {

	        ex.printStackTrace();
	    }
	}
	
	public void recordStory(Story s) {
		
		try {

			// connect to the database
		    Connection conn = getConnection();
		    
		    // create and execute query
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STORIES (url, title, " + 
		    		                                                            "private, description, votes, " +
		    		                                                            "category, name) " +
		    		                                                     "VALUES (?, ?, ?, ?, ?, ?, ?)");
		    pstmt.setString(1, s.getURL());
		    pstmt.setString(2, s.getTitle());
		    pstmt.setInt(3, s.getPrivate());
		    pstmt.setString(4, s.getDescription() );
		    pstmt.setInt(5, 0);
		    pstmt.setString(6, s.getCategory());
		    pstmt.setString(7, s.getName());
		    	     
			System.out.println(pstmt);
		    pstmt.executeUpdate();
			System.out.println("query was executed successfully!");
		    
			// close statement, connection, and output stream
			pstmt.close();
			conn.close();
		    } catch (java.lang.Exception ex) {
		        ex.printStackTrace();
		    }
	}
	
	public void recordFriend(Friend f) {
		//use JDBC to insert this user into the database
	  try {
		  
	    // connect to the database
	    Connection conn = getConnection();
	    
	    // create and execute query
	    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FRIENDS (pending, uid1, uid2) " +  
	    		                                                     "VALUES (?, ?, ?)");
	    
	    pstmt.setInt(1, 1);
	    pstmt.setString(2, f.getuid1());
	    pstmt.setString(3, f.getuid2());
	    	     
		System.out.println(pstmt);
	    pstmt.executeUpdate();
		System.out.println("query was executed successfully!");
	    
		// close statement, connection, and output stream
		pstmt.close();
		conn.close();
	    } catch (java.lang.Exception ex) {

	        ex.printStackTrace();
	    }
	}
	
	public void recordIndex(LinkedList<String> words, int sid){
		try {
			
		Connection conn = getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO INVINDEX (word, docid) " +  
        																			"VALUES (?, ?)");
		for(int x = 0; x < words.size(); x++){
		
	    pstmt.setString(1, words.get(x));
	    pstmt.setInt(2, sid);
		
		System.out.println(pstmt);
	    pstmt.executeUpdate();
		System.out.println("query was executed successfully!");
		
		}
		
		pstmt.close();
		conn.close();
	    } catch (java.lang.Exception ex) {

	        ex.printStackTrace();
	    }
	}
	
	
}
