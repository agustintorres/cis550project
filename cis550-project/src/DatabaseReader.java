import java.sql.*;

public class DatabaseReader {
	
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
	
	public boolean isValidAuthentication(String username, String password) {
		//use JDBC to insert this user into the database
	  try {
		  
	    // connect to the database
	    Connection conn = getConnection();
	    
	    // create and execute query
	    PreparedStatement pstmt = conn.prepareStatement("SELECT password FROM USERS WHERE name = ?");
	    pstmt.setString(1, username);
	    	     
		System.out.println(pstmt);
	    System.out.println("already printed preparedStatement");
		
	    ResultSet rs = pstmt.executeQuery();
	    
	    String pass = "";
	    
	    while (rs.next()) {
		    // get current row values
		    pass = rs.getString(1);
		    
		    if ( pass.equals(password) ) {
				return true;
			}
	
		    // print values
		    System.out.println(pass + "\t");
		}
		
		// close statement, connection, and output stream
		pstmt.close();
		conn.close();
	    } catch (java.sql.SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	    
	    return false;
	}
		
}
