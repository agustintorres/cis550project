import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

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
	
	public boolean voteExists(String userid, int storyid) {
		boolean output = false;
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String myQuery = ("SELECT * FROM VOTES WHERE storyid = " + storyid + " AND uid=\"" + userid + "\"");
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

	public ArrayList<CrawlerLink> getLinks(){
		ArrayList<CrawlerLink> book = new ArrayList<CrawlerLink>();
		//select all links and ids from tables
		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String myQuery = "";
			
			myQuery = ("SELECT url, storyid FROM STORIES");

			
			rs = stmt.executeQuery(myQuery);
			
			while(rs.next())
			{
				CrawlerLink tempLink = new CrawlerLink(rs.getString("url"), rs.getInt("storyid"));
				// retrieve and print the values for the current row
				book.add(tempLink);
			}
			stmt.close();
			conn.close();
			
			    } catch (java.lang.Exception ex) {
			
			        ex.printStackTrace();
					return null;
			    }
		//turn into objects, put into arraylist
		
		
		return book;
	}
	
	public ArrayList<Comment> getComments(int storyId){
		ArrayList<Comment> chat = new ArrayList<Comment>();
		try {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String myQuery = "";
			
			myQuery = ("SELECT * FROM COMMENTS WHERE storyid = " + storyId);

			
			rs = stmt.executeQuery(myQuery);
			
			while(rs.next())
			{
				Comment tempComment = new Comment();
				// retrieve and print the values for the current row
				String myText = rs.getString("text");
				String myAuthor = rs.getString("uid");
				String myTime = rs.getString("commenttime");
				
				tempComment.setText(myText);
				tempComment.setAuthor(myAuthor);
				tempComment.setCommenttime(myTime);
				chat.add(tempComment);
			}
			stmt.close();
			conn.close();
			
			    } catch (java.lang.Exception ex) {
			
			        ex.printStackTrace();
					return null;
			    }
		return chat;
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
				int mystoryid = rs.getInt("storyid");
				
				
				tempStory.setTitle(mytitle);
				tempStory.setName(myname);
				tempStory.setDescription(mydescription);
				tempStory.setURL(myurl);
				tempStory.setCategory(mycategory);
				tempStory.setPrivate(myprivate);
				tempStory.setVotes(myvotes);
				tempStory.setStorytime(mystorytime);
				tempStory.setStoryid(mystoryid);
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
	
	public LinkedList<User> getFriendsByName(String name, boolean pending){
		try{
			LinkedList<User> results = new LinkedList<User>();
			LinkedList<String> names = new LinkedList<String>();
			
			Connection conn = getConnection();
			PreparedStatement pstmt;
			
			
			pstmt = conn.prepareStatement("SELECT uid1 FROM FRIENDS WHERE uid2 = ?"
																	+" AND pending = ?");
			
			
			pstmt.setString(1, name);
			
			if(pending == true){
				pstmt.setInt(2, 1);
			}else{
				pstmt.setInt(2, 0);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				names.add(rs.getString(1));
			}
			
			pstmt = conn.prepareStatement("SELECT uid2 FROM FRIENDS WHERE uid1 = ?"
																+" AND pending = ?");
			
			pstmt.setString(1, name);
			
			if(pending == true){
				pstmt.setInt(2, 1);
			}else{
				pstmt.setInt(2, 0);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				names.add(rs.getString(1));
			}
			
			for(int x = 0; x < names.size(); x++){
				
				pstmt = conn.prepareStatement("SELECT * FROM USERS WHERE name = ?");
				pstmt.setString(1, names.get(x));
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String bday = rs.getString("dob");
					String location = rs.getString("location");
					String profession = rs.getString("profession");
					
					User tempUser = new User(names.get(x), "notreal", bday, location, profession);
					results.add(tempUser);
				}
			
			}										


			pstmt.close();
			conn.close();
			return results;

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}


	}

	public LinkedList<String> getDatabaseCategories(){

		try{

			LinkedList<String> results = new LinkedList<String>();

			Connection conn = getConnection();
			PreparedStatement pstmt;


			pstmt = conn.prepareStatement("SELECT Category FROM CATEGORIES");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				results.add(rs.getString(1));
			}

			pstmt.close();
			conn.close();
			return results;
			
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
	}
	
	// returns null if user has not published any story, otherwise:
	// for every piece the user has published, find other users who have voted
	// or commented upon it, and return other stories in the same category
	// that they have either contributed, voted, or commented upon
	public HashSet<Story> userHasPublishedAStory(String username) {
		
		HashSet<Story> myStories = new HashSet<Story>();
		HashSet<String> usernames = new HashSet<String>();
		
		try {
			  
		    // connect to the database
		    Connection conn = getConnection();
		    		    		    
		    // get all stories published by this user
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STORIES WHERE name = ?");
		    pstmt.setString(1, username);
			
		    ResultSet rs = pstmt.executeQuery();
		    
		    //if the result set is empty
		    if (rs.first() == false) {
		    	return null;
		    }
		    
		    rs.beforeFirst();
		    while (rs.next()) {
				int storyid = rs.getInt("storyid");
				String category = rs.getString("category");
				//System.out.println("Story id: " + storyid);
				
				//get users who have commented or voted on this story and add them to usernames
				PreparedStatement pstmt2 = conn.prepareStatement("SELECT DISTINCT uid FROM ( SELECT DISTINCT uid FROM COMMENTS WHERE storyid = ? UNION SELECT DISTINCT uid FROM VOTES WHERE storyid = ? ) Tempo");
				pstmt2.setInt(1, storyid);
				pstmt2.setInt(2, storyid);
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					String user = rs2.getString(1);
					usernames.add(user);
				}
				
				//System.out.println("usernames: " + usernames.toString());
				
				//for each user, get stories in the same category that the user has 
			    //contributed, voted, or commented upon
			    Iterator<String> iter = usernames.iterator();
			    while (iter.hasNext()) {
			    	username = iter.next();
			    	
			    	//get stories in the same category that the user has contributed
					PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM STORIES WHERE name = ? and category = ?");
					pstmt4.setString(1, username);
					pstmt4.setString(2, category);
					ResultSet rs4 = pstmt4.executeQuery();
					while (rs4.next()) {
						Story tempStory = new Story();
						String myurl = rs4.getString("url");
						String mytitle = rs4.getString("title");
						String myname = rs4.getString("name");
						int myprivate = rs4.getInt("private");
						String mydescription = rs4.getString("description");
						String mystorytime = rs4.getString("storytime");
						int myvotes = rs4.getInt("votes");
						String mycategory = rs4.getString("category");
						int mystoryid = rs4.getInt("storyid");
						
						tempStory.setTitle(mytitle);
						tempStory.setName(myname);
						tempStory.setDescription(mydescription);
						tempStory.setURL(myurl);
						tempStory.setCategory(mycategory);
						tempStory.setPrivate(myprivate);
						tempStory.setVotes(myvotes);
						tempStory.setStorytime(mystorytime);
						tempStory.setStoryid(mystoryid);
						
						myStories.add(tempStory);
					}
					
					// get stories in the same category that the user has voted or commented upon
					PreparedStatement pstmt5 = conn.prepareStatement("SELECT DISTINCT storyid FROM (SELECT DISTINCT storyid FROM VOTES WHERE uid = ? UNION SELECT DISTINCT storyid FROM COMMENTS where uid = ?) Temp");
					pstmt5.setString(1, username);
					pstmt5.setString(2, username);
					ResultSet rs5 = pstmt5.executeQuery();
					//System.out.println("myStories:" + myStories.toString());
					while (rs5.next()) {
						storyid = rs5.getInt("storyid");
						
						//get the story with this storyid, should be only 1
						ArrayList<Story> stories = getStories(1, storyid, 1);
						Story story = stories.get(0);
						//add it only if it's in the same category
						if (story.getCategory().equals(category)) {
							myStories.add(stories.get(0));
						}
					}
					
			    }
			}
			  
			// close statement, connection, and output stream
			pstmt.close();
			conn.close();
			return myStories;
		    } catch (java.sql.SQLException ex) {
		    	ex.printStackTrace();
		    	return null;
		    }   
	}
	
	// returns null if user has not voted on any story, otherwise:
	// for every piece the user has voted on, find other users who have voted
	// or commented upon it, and return other stories in the same category
	// that they have either contributed, voted, or commented upon
	public HashSet<Story> userHasVotedOnAStory(String username) {
	
		HashSet<Story> myStories = new HashSet<Story>();
		HashSet<Story> userStories = new HashSet<Story>();
		HashSet<String> usernames = new HashSet<String>();
		
		try {
			  
		    // connect to the database
		    Connection conn = getConnection();
		    		    		    
		    // get all stories voted uponby this user
		    PreparedStatement pstmt = conn.prepareStatement("SELECT storyid FROM VOTES WHERE uid = ?");
		    pstmt.setString(1, username);
			
		    ResultSet rs = pstmt.executeQuery();
		    
		    //if the result set is empty
		    if (rs.first() == false) {
		    	return null;
		    }
		    
		    rs.beforeFirst();
		    
		    // get the entire stories corresponding to these storyid
		    while (rs.next()) {
		    	int storyid = rs.getInt(1);
		    	PreparedStatement pstmt2 = conn.prepareStatement("SELECT DISTINCT * FROM STORIES WHERE storyid = ?");
		    	pstmt2.setInt(1, storyid);
		    	ResultSet rs2 = pstmt2.executeQuery();
		    	//there should be only one story matching this storyid
		    	while (rs2.next()) {
		    		Story tempStory = new Story();
		    		String myurl = rs2.getString("url");
		    		String mytitle = rs2.getString("title");
					String myname = rs2.getString("name");
					int myprivate = rs2.getInt("private");
					String mydescription = rs2.getString("description");
					String mystorytime = rs2.getString("storytime");
					int myvotes = rs2.getInt("votes");
					String mycategory = rs2.getString("category");
					int mystoryid = rs2.getInt("storyid");
					
					tempStory.setTitle(mytitle);
					tempStory.setName(myname);
					tempStory.setDescription(mydescription);
					tempStory.setURL(myurl);
					tempStory.setCategory(mycategory);
					tempStory.setPrivate(myprivate);
					tempStory.setVotes(myvotes);
					tempStory.setStorytime(mystorytime);
					tempStory.setStoryid(mystoryid);
					
					userStories.add(tempStory);
		    	}
			}
		    //System.out.println("myStories:" + myStories.toString());
			
		    Iterator<Story> iter = userStories.iterator();
		    while (iter.hasNext()) {
				Story currstory = iter.next();
		    	int storyid = currstory.getStoryid();
				String category = currstory.getCategory();
				//System.out.println("Story id: " + storyid);
				
				//get users who have commented or voted on this story and add them to usernames
				PreparedStatement pstmt3 = conn.prepareStatement("SELECT DISTINCT uid FROM ( SELECT DISTINCT uid FROM COMMENTS WHERE storyid = ? UNION SELECT DISTINCT uid FROM VOTES WHERE storyid = ? ) Tempo");
				pstmt3.setInt(1, storyid);
				pstmt3.setInt(2, storyid);
				ResultSet rs3 = pstmt3.executeQuery();
				while (rs3.next()) {
					String user = rs3.getString(1);
					usernames.add(user);
				}
				
				//System.out.println("usernames: " + usernames.toString());
				
				//for each user, get stories in the same category that the user has 
			    //contributed, voted, or commented upon
			    Iterator<String> iter2 = usernames.iterator();
			    while (iter2.hasNext()) {
			    	username = iter2.next();
			    	
			    	//get stories in the same category that the user has contributed
					PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM STORIES WHERE name = ? and category = ?");
					pstmt4.setString(1, username);
					pstmt4.setString(2, category);
					ResultSet rs4 = pstmt4.executeQuery();
					while (rs4.next()) {
						Story tempStory = new Story();
						String myurl = rs4.getString("url");
						String mytitle = rs4.getString("title");
						String myname = rs4.getString("name");
						int myprivate = rs4.getInt("private");
						String mydescription = rs4.getString("description");
						String mystorytime = rs4.getString("storytime");
						int myvotes = rs4.getInt("votes");
						String mycategory = rs4.getString("category");
						int mystoryid = rs4.getInt("storyid");
						
						tempStory.setTitle(mytitle);
						tempStory.setName(myname);
						tempStory.setDescription(mydescription);
						tempStory.setURL(myurl);
						tempStory.setCategory(mycategory);
						tempStory.setPrivate(myprivate);
						tempStory.setVotes(myvotes);
						tempStory.setStorytime(mystorytime);
						tempStory.setStoryid(mystoryid);
						
						myStories.add(tempStory);
					}
					
					// get stories in the same category that the user has voted or commented upon
					PreparedStatement pstmt5 = conn.prepareStatement("SELECT DISTINCT storyid FROM (SELECT DISTINCT storyid FROM VOTES WHERE uid = ? UNION SELECT DISTINCT storyid FROM COMMENTS where uid = ?) Temp");
					pstmt5.setString(1, username);
					pstmt5.setString(2, username);
					ResultSet rs5 = pstmt5.executeQuery();
					//System.out.println("myStories:" + myStories.toString());
					while (rs5.next()) {
						storyid = rs5.getInt("storyid");
						
						//get the story with this storyid, should be only 1
						ArrayList<Story> stories = getStories(1, storyid, 1);
						Story story = stories.get(0);
						//add it only if it's in the same category
						if (story.getCategory().equals(category)) {
							myStories.add(stories.get(0));
						}
					}
					
			    }
		    }
			  
			// close statement, connection, and output stream
			pstmt.close();
			conn.close();
			return myStories;
		    } catch (java.sql.SQLException ex) {
		    	ex.printStackTrace();
		    	return null;
		    }   
	}
	
	// returns null if user has not commented on any story, otherwise:
	// for every piece the user has commented on, find other users who have voted
	// or commented upon it, and return other stories in the same category
	// that they have either contributed, voted, or commented upon
	public HashSet<Story> userHasCommentedOnAStory(String username) {
	
		HashSet<Story> userStories = new HashSet<Story>();
		HashSet<Story> myStories = new HashSet<Story>();
		HashSet<String> usernames = new HashSet<String>();
		
		try {
			  
		    // connect to the database
		    Connection conn = getConnection();
		    		    		    
		    // get all stories commented upon by this user
		    PreparedStatement pstmt = conn.prepareStatement("SELECT storyid FROM COMMENTS WHERE uid = ?");
		    pstmt.setString(1, username);
			
		    ResultSet rs = pstmt.executeQuery();
		    
		    //if the result set is empty
		    if (rs.first() == false) {
		    	return null;
		    }
		    
		    rs.beforeFirst();
		    
		    // get the entire stories corresponding to these storyid
		    while (rs.next()) {
		    	int storyid = rs.getInt(1);
		    	PreparedStatement pstmt2 = conn.prepareStatement("SELECT DISTINCT * FROM STORIES WHERE storyid = ?");
		    	pstmt2.setInt(1, storyid);
		    	ResultSet rs2 = pstmt2.executeQuery();
		    	//there should be only one story matching this storyid
		    	while (rs2.next()) {
		    		Story tempStory = new Story();
		    		String myurl = rs2.getString("url");
		    		String mytitle = rs2.getString("title");
					String myname = rs2.getString("name");
					int myprivate = rs2.getInt("private");
					String mydescription = rs2.getString("description");
					String mystorytime = rs2.getString("storytime");
					int myvotes = rs2.getInt("votes");
					String mycategory = rs2.getString("category");
					int mystoryid = rs2.getInt("storyid");
					
					tempStory.setTitle(mytitle);
					tempStory.setName(myname);
					tempStory.setDescription(mydescription);
					tempStory.setURL(myurl);
					tempStory.setCategory(mycategory);
					tempStory.setPrivate(myprivate);
					tempStory.setVotes(myvotes);
					tempStory.setStorytime(mystorytime);
					tempStory.setStoryid(mystoryid);
					
					userStories.add(tempStory);
		    	}
			}
		    //System.out.println("myStories:" + myStories.toString());
			
		    Iterator<Story> iter = userStories.iterator();
		    while (iter.hasNext()) {
				Story currstory = iter.next();
		    	int storyid = currstory.getStoryid();
				String category = currstory.getCategory();
				//System.out.println("Story id: " + storyid);
				
				//get users who have commented or voted on this story and add them to usernames
				PreparedStatement pstmt3 = conn.prepareStatement("SELECT DISTINCT uid FROM ( SELECT DISTINCT uid FROM COMMENTS WHERE storyid = ? UNION SELECT DISTINCT uid FROM VOTES WHERE storyid = ? ) Tempo");
				pstmt3.setInt(1, storyid);
				pstmt3.setInt(2, storyid);
				ResultSet rs3 = pstmt3.executeQuery();
				while (rs3.next()) {
					String user = rs3.getString(1);
					usernames.add(user);
				}
				
				//System.out.println("usernames: " + usernames.toString());
				
				//for each user, get stories in the same category that the user has 
			    //contributed, voted, or commented upon
			    Iterator<String> iter2 = usernames.iterator();
			    while (iter2.hasNext()) {
			    	username = iter2.next();
			    	
			    	//get stories in the same category that the user has contributed
					PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM STORIES WHERE name = ? and category = ?");
					pstmt4.setString(1, username);
					pstmt4.setString(2, category);
					ResultSet rs4 = pstmt4.executeQuery();
					while (rs4.next()) {
						Story tempStory = new Story();
						String myurl = rs4.getString("url");
						String mytitle = rs4.getString("title");
						String myname = rs4.getString("name");
						int myprivate = rs4.getInt("private");
						String mydescription = rs4.getString("description");
						String mystorytime = rs4.getString("storytime");
						int myvotes = rs4.getInt("votes");
						String mycategory = rs4.getString("category");
						int mystoryid = rs4.getInt("storyid");
						
						tempStory.setTitle(mytitle);
						tempStory.setName(myname);
						tempStory.setDescription(mydescription);
						tempStory.setURL(myurl);
						tempStory.setCategory(mycategory);
						tempStory.setPrivate(myprivate);
						tempStory.setVotes(myvotes);
						tempStory.setStorytime(mystorytime);
						tempStory.setStoryid(mystoryid);
						
						myStories.add(tempStory);
					}
					
					// get stories in the same category that the user has voted or commented upon
					PreparedStatement pstmt5 = conn.prepareStatement("SELECT DISTINCT storyid FROM (SELECT DISTINCT storyid FROM VOTES WHERE uid = ? UNION SELECT DISTINCT storyid FROM COMMENTS where uid = ?) Temp");
					pstmt5.setString(1, username);
					pstmt5.setString(2, username);
					ResultSet rs5 = pstmt5.executeQuery();
					//System.out.println("myStories:" + myStories.toString());
					while (rs5.next()) {
						storyid = rs5.getInt("storyid");
						
						//get the story with this storyid, should be only 1
						ArrayList<Story> stories = getStories(1, storyid, 1);
						Story story = stories.get(0);
						//add it only if it's in the same category
						if (story.getCategory().equals(category)) {
							myStories.add(stories.get(0));
						}
					}
					
			    }
		    }
			  
			// close statement, connection, and output stream
			pstmt.close();
			conn.close();
			return myStories;
		    } catch (java.sql.SQLException ex) {
		    	ex.printStackTrace();
		    	return null;
		    }   
	}
	
	public ArrayList<Story> getRecommendations(String username) {
		//use JDBC to insert this user into the database
		try {

			// connect to the database
			Connection conn = getConnection();

			boolean userHasSharedVotedOrCommended = false;

			HashSet<Story> pstories = userHasPublishedAStory(username);
			HashSet<Story> vstories = userHasVotedOnAStory(username);
			HashSet<Story> cstories = userHasCommentedOnAStory(username);

			if ( (pstories == null) && (vstories == null) && (cstories ==null) ) {
				//for each category, get the most popular story and the storie with the highest number of comments
			}

			HashSet<Story> recommendedStories = new HashSet<Story>();
			recommendedStories.addAll(pstories);
			recommendedStories.addAll(vstories);
			recommendedStories.addAll(cstories);

			//display the recommended stories


			// close statement, connection, and output stream
			//pstmt.close();
			conn.close();
		} catch (java.sql.SQLException ex) {
			//ex.printStackTrace();
			//return false;
		}   
		return null;
	}

	public LinkedList<Story> getStoriesByCategory(String category){
		try{

			LinkedList<Story> results = new LinkedList<Story>();

			Connection conn = getConnection();
			PreparedStatement pstmt;


			pstmt = conn.prepareStatement("SELECT * FROM STORIES WHERE category = ?");
			pstmt.setString(1, category);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Story tempStory = new Story();
				// retrieve and print the values for the current row
				String myurl = rs.getString("url");
				String mytitle = rs.getString("title");
				String myname = rs.getString("name");
				int myprivate = rs.getInt("private");
				String mydescription = rs.getString("description");
				String mystorytime = rs.getString("storytime");
				//int myvotes = rs.getInt("votes");
				int myvotes = getVotesByStory(rs.getInt("storyid"));
				String mycategory = rs.getString("category");
				int mystoryid = rs.getInt("storyid");

				tempStory.setTitle(mytitle);
				tempStory.setName(myname);
				tempStory.setDescription(mydescription);
				tempStory.setURL(myurl);
				tempStory.setCategory(mycategory);
				tempStory.setPrivate(myprivate);
				tempStory.setVotes(myvotes);
				tempStory.setStorytime(mystorytime);
				tempStory.setStoryid(mystoryid);
				results.add(tempStory);
			}


			return results;
		}catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}   
	}
	
	public LinkedList<Story> getStoriesByRecent(){
		try{

			LinkedList<Story> results = new LinkedList<Story>();

			Connection conn = getConnection();
			PreparedStatement pstmt;


			pstmt = conn.prepareStatement("SELECT * FROM STORIES ORDER BY storytime desc");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Story tempStory = new Story();
				// retrieve and print the values for the current row
				String myurl = rs.getString("url");
				String mytitle = rs.getString("title");
				String myname = rs.getString("name");
				int myprivate = rs.getInt("private");
				String mydescription = rs.getString("description");
				String mystorytime = rs.getString("storytime");
				//int myvotes = rs.getInt("votes");
				int myvotes = getVotesByStory(rs.getInt("storyid"));
				String mycategory = rs.getString("category");
				int mystoryid = rs.getInt("storyid");

				tempStory.setTitle(mytitle);
				tempStory.setName(myname);
				tempStory.setDescription(mydescription);
				tempStory.setURL(myurl);
				tempStory.setCategory(mycategory);
				tempStory.setPrivate(myprivate);
				tempStory.setVotes(myvotes);
				tempStory.setStorytime(mystorytime);
				tempStory.setStoryid(mystoryid);
				results.add(tempStory);
			}


			return results;
		}catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}   
	}
	
	public LinkedList<Story> getStoriesByPopular(){
		try{
			LinkedList<Story> results = new LinkedList<Story>();
			
			Connection conn = getConnection();
			PreparedStatement pstmt;

			pstmt = conn.prepareStatement("SELECT storyid from VOTES GROUP BY storyid ORDER BY COUNT(uid) DESC");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				results.add(getStoryById(rs.getInt("storyid")));
			}
			
			return results;
		}catch (java.lang.Exception ex) {
			ex.printStackTrace();
			return null;
		}   
	}
	
	public int getVotesByStory(int storyid){
		int result = 0;
		try {

			// connect to the database
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(uid) from VOTES where storyid = " + storyid);
			rs.next();
			// retrieve and print the values for the current row
			result = rs.getInt(1);
			// close statement, connection, and output stream
			stmt.close();
			conn.close();

		} catch (java.lang.Exception ex) {

			ex.printStackTrace();
			return -1;
		}
		return result;
	}
	
	public ArrayList<Comment> getCommentsByUser(String uid){
		ArrayList<Comment> opinions = new ArrayList<Comment>();
		
		try {

			// connect to the database
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COMMENTS WHERE uid=" + uid);
			while(rs.next()){
				
			}
			// retrieve and print the values for the current row
			
			// close statement, connection, and output stream
			stmt.close();
			conn.close();

		} catch (java.lang.Exception ex) {

			ex.printStackTrace();
			return null;
		}
		
		return opinions;
	
	
	}
	
	public ArrayList<Story> getVotesByUser(String uid){
		ArrayList<Story> ballot = new ArrayList<Story>();
		try {

			// connect to the database
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM VOTES WHERE uid=" + uid);
			while(rs.next()){
				
			}
			// retrieve and print the values for the current row
			
			// close statement, connection, and output stream
			stmt.close();
			conn.close();

		} catch (java.lang.Exception ex) {

			ex.printStackTrace();
			return null;
		}
		return ballot;
	}
	
	
	public HashSet<Story> getMostPopularStoryInEachCategory() {
	  try {
		// connect to the database
	    Connection conn = getConnection();
	    Statement stmt = conn.createStatement();
	    
	    ArrayList<String> categories = new ArrayList<String>();
		HashSet<Story> myStories = new HashSet<Story>();
	    
		//get all categories
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CATEGORIES");
		
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
	    	String category = rs.getString(1);
	    	categories.add(category);
	    }

	    //for each category, get the most popular story (most number of votes)   
		Iterator<String> iter = categories.iterator();
		while (iter.hasNext()) {
			String category = iter.next();
			PreparedStatement pstmt2 = conn.prepareStatement("SELECT S.storyid FROM (SELECT * FROM STORIES WHERE category = ?) S, VOTES V WHERE S.storyid = V.storyid GROUP BY S.storyid ORDER BY COUNT(*) DESC");
			pstmt2.setString(1, category);
			ResultSet rs2 = pstmt2.executeQuery();
			//System.out.println("Query executed");
			if ( rs2.first() ){
				rs2.beforeFirst();
				rs2.next();
				int storyid = rs2.getInt(1);
				Story s = getStoryById(storyid);
				//System.out.println("story added");
				myStories.add(s);
			}
		}
	    
		// close statement, connection, and output stream
		stmt.close();
		conn.close();
		return myStories;
	    } catch (java.lang.Exception ex) {
	        ex.printStackTrace();
			return null;
	    }
	}
	
	public HashSet<Story> getMostCommentedStoryInEachCategory() {
		try {
			// connect to the database
		    Connection conn = getConnection();
		    Statement stmt = conn.createStatement();
		    
		    ArrayList<String> categories = new ArrayList<String>();
			HashSet<Story> myStories = new HashSet<Story>();
		    
			//get all categories
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CATEGORIES");
			
		    ResultSet rs = pstmt.executeQuery();
		    while (rs.next()) {
		    	String category = rs.getString(1);
		    	categories.add(category);
		    }

		    //for each category, get the story that has the most number of comments  
			Iterator<String> iter = categories.iterator();
			while (iter.hasNext()) {
				String category = iter.next();
				PreparedStatement pstmt2 = conn.prepareStatement("SELECT S.storyid FROM (SELECT * FROM STORIES WHERE category = ?) S, COMMENTS C WHERE C.storyid = S.storyid GROUP BY S.storyid ORDER BY votes DESC");
				pstmt2.setString(1, category);
				ResultSet rs2 = pstmt2.executeQuery();
				//System.out.println("Query executed");
				if ( rs2.first() ){
					rs2.beforeFirst();
					rs2.next();
					int storyid = rs2.getInt(1);
					Story s = getStoryById(storyid);
					//System.out.println("story added");
					myStories.add(s);
				}
			}
		    
			// close statement, connection, and output stream
			stmt.close();
			conn.close();
			return myStories;
		    } catch (java.lang.Exception ex) {
		        ex.printStackTrace();
				return null;
		    }
	}
	
}
