import java.sql.*;

public class DatabaseRecorder {

	public void recordUser(User u) {
		//use JDBC to insert this user into the database
	  try {

	    // Load the JDBC driver
	    //Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		  
	    // url
	    String url = "jdbc:oracle:thin:@//fling-l.seas.upenn.edu:1521/cisora";

	    String user = "agustinm";
	    String pass = "trustno1";
		
	    // connect
	    //Connection conn = DriverManager.getConnection(url, user, pass);
	    java.sql.Connection conn;
	    conn = DriverManager.getConnection("jdbc:mysql://fling-l.seas.upenn.edu:3306/andrewcc?user=andrewcc&password=databases");
	    
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

	public void recordStory(Story s) {
		
		try {

		    // Load the JDBC driver
		    
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			  
		    // connect
		    
		    java.sql.Connection conn;
		    conn = DriverManager.getConnection("jdbc:mysql://fling-l.seas.upenn.edu:3306/andrewcc?user=andrewcc&password=databases");
		    
		    // create and execute query
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STORIES (url, title, " + 
		    		                                                            "private, description, votes, " +
		    		                                                            "category) " +
		    		                                                     "VALUES (?, ?, ?, ?, ?, ?)");
		    pstmt.setString(1, s.getURL());
		    pstmt.setString(2, s.getTitle());
		    pstmt.setInt(3, s.getPrivate());
		    pstmt.setString(4, s.getDescription() );
		    pstmt.setInt(5, 0);
		    pstmt.setString(6, s.getCategory());
		    	     
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
}
