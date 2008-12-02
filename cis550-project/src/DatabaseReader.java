import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

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
	
	public boolean voteExists(int userid, int storyid) {
		boolean output = false;
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String myQuery = ("SELECT * FROM VOTES WHERE storyid = " + storyid + " AND uid = " + userid);
			rs = stmt.executeQuery(myQuery);
			
			while(rs.next()){
				return true;
			}
		}
		
		catch(java.lang.Exception ex) {
			
	        ex.printStackTrace();
			return false;
	    }
		
		return output;
	}
	
	public ArrayList<Story> getStories() {
		return getStories(0, -1, 0);
	}
	
	public ArrayList<Story> getStories(int count) {
		return getStories(count, -1, 0);
	}
	
	public Story getStoryById(int id){
		ArrayList<Story> myStory = getStories(0, id, 0);
		Story outStory = myStory.get(0);
		return outStory;
	}
	
	public ArrayList<Story> getStories(int i, int id, int order) {
		//default is 10 stories
		//default is ascending(=0) descending (=1)
		
		ArrayList<Story> myStories = new ArrayList<Story>();
		
		int count;
		if (i > 0)
			count = i;
		else count = 10;
		
		int myOrder = 0;
		if (order == 1)
			myOrder = 1;
		
		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String myQuery = "";
			if (id > 0){
				myQuery = ("SELECT * FROM STORIES WHERE storyid = " + id);
			}
			else {
				myQuery = ("SELECT * FROM STORIES");
			}
			
			if (myOrder == 1)
			{
				myQuery += " ORDER BY storytime DESC";
			}
			else {
				myQuery += " ORDER BY storytime";
			}
			
			rs = stmt.executeQuery(myQuery);
			
			while(rs.next() && (count > 0))
			{
				Story tempStory = new Story();
				// retrieve and print the values for the current row
				String myurl = rs.getString("url");
				String mytitle = rs.getString("title");
				String myname = rs.getString("name");
				int myprivate = rs.getInt("private");
				String mydescription = rs.getString("description");
				String mystorytime = rs.getString("storytime");
				int myvotes = rs.getInt("votes");
				String mycategory = rs.getString("category");
				
				tempStory.setTitle(mytitle);
				tempStory.setName(myname);
				tempStory.setDescription(mydescription);
				tempStory.setURL(myurl);
				tempStory.setCategory(mycategory);
				tempStory.setPrivate(myprivate);
				
				myStories.add(tempStory);
				count--;
			}
			stmt.close();
			conn.close();
			return myStories;
			
			    } catch (java.lang.Exception ex) {
			
			        ex.printStackTrace();
					return null;
			    }
			}
	
	public Story getStory() {
		//use JDBC to insert this user into the database
	  try {
		  
	    // connect to the database
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM STORIES");
		rs.next();
		Story tempStory = new Story();
				// retrieve and print the values for the current row
				String myurl = rs.getString("url");
				String mytitle = rs.getString("title");
				String myname = rs.getString("name");
				int myprivate = rs.getInt("private");
				String mydescription = rs.getString("description");
				String mystorytime = rs.getString("storytime");
				int myvotes = rs.getInt("votes");
				String mycategory = rs.getString("category");
				
				tempStory.setTitle(mytitle);
				tempStory.setName(myname);
				tempStory.setDescription(mydescription);
				tempStory.setURL(myurl);
				tempStory.setCategory(mycategory);
				tempStory.setPrivate(myprivate);

		System.out.println("query was executed successfully!");
	    
		// close statement, connection, and output stream
		stmt.close();
		conn.close();
		return tempStory;
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
	    	     
		//System.out.println(pstmt);
		
	    ResultSet rs = pstmt.executeQuery();
	    
	    String pass = "";
	    
	    while (rs.next()) {
		    // get current row values
		    pass = rs.getString(1);
		    
		    if ( pass.equals(password) ) {
				return true;
			}
		}
		
		// close statement, connection, and output stream
		pstmt.close();
		conn.close();
	    } catch (java.sql.SQLException ex) {
	    	//ex.printStackTrace();
	    	return false;
	    }   
	    return false;
	}
	
	public LinkedList<LinkedList<Integer>> getStoriesWithTerm(LinkedList<String> words){
		
		try{
			
			LinkedList<Integer> results;
			LinkedList<LinkedList<Integer>> listOfResults = new LinkedList<LinkedList<Integer>>();
			
			// connect to the database
		    Connection conn = getConnection();
		    
		    // create and execute query
		    
		    PreparedStatement pstmt = conn.prepareStatement("SELECT docid FROM INVINDEX WHERE word = ?");
		    
		    for(int x = 0; x < words.size(); x++){
		    	
			    results = new LinkedList<Integer>();
	
			    pstmt.setString(1, words.get(x));
			    	     			
			    ResultSet rs = pstmt.executeQuery();
			    
			    int id;
		    
		    while (rs.next()) {
			    // get current row values
			    id = rs.getInt(1);
			    
			    results.add(id);
			}
		    
		    listOfResults.add(results);
		    
		    }
		    
		    pstmt.close();
			conn.close();
		
		return listOfResults;
		
		
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int getIdByStory(Story s){
		try{
			int result = 0;
			
			Connection conn = getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT storyid FROM STORIES WHERE url = ? AND"
													+" name = ? AND title = ? AND private = ? AND description = ?"
													+" AND category = ?");
			
			pstmt.setString(1, s.getURL());
			pstmt.setString(2, s.getName());
			pstmt.setString(3, s.getTitle());
			pstmt.setInt(4, s.getPrivate());
			pstmt.setString(5, s.getDescription());
			pstmt.setString(6, s.getCategory());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {			
				result = rs.getInt(1);
			}
			
			pstmt.close();
			conn.close();
			
			return result;	
			
			
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<Friend> getFriendsByName(String name){
		try{
			LinkedList<Friend> results = new LinkedList<Friend>();
			
			Connection conn = getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT storyid FROM STORIES WHERE url = ? AND"
											+" name = ? AND title = ? AND private = ? AND description = ?"
																	+" AND category = ?");
			
			
			return results;
			
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
	}

	
}
