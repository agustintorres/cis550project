/** Agustin M. Torres Quintanilla
 ** CIS 550
 ** Homework 2
 ** Question 2 **/


import java.sql.*;
import java.io.*;

class BookAuthors {

  public static void main(String argv[]) {

    try {

        // Load the JDBC driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // url
        String url = "jdbc:oracle:thin:@//fling-l.seas.upenn.edu:1521/cisora";

	// get username and password from command line arguments
	if (argv.length < 2) {
	  System.err.println("Usage: BookAuthors <username> <password>");
	  System.exit(0);
	}
	String user = argv[0];
	String pass = argv[1];
	
        // connect
        Connection conn = DriverManager.getConnection(url, user, pass);

        // create and execute query
        Statement stmt = conn.createStatement();
	String query = "SELECT A.Name, A.Affiliation, COUNT(BA.ISBN) AS num " + 
                       "FROM Authors A LEFT OUTER JOIN BookAuthors BA " +
	               "ON A.Name = BA.Author " +
	               "GROUP BY A.Name, A.Affiliation ";
        ResultSet rs = stmt.executeQuery(query);
	
	// get information about the column names
	ResultSetMetaData rsmd = rs.getMetaData();
	String nameHeading = rsmd.getColumnLabel(1);
	String affiliationHeading = rsmd.getColumnLabel(2);
	String numHeading = rsmd.getColumnLabel(3);

	// Create output file
        FileWriter fstream = new FileWriter("q2.txt");
        BufferedWriter out = new BufferedWriter(fstream);


        // print the heading
	out.write(nameHeading + "\t");
	out.write(affiliationHeading + "\t");
	out.write(numHeading);
	out.write("\n");

	// traverse through the results
	while (rs.next()) {

	    // get current row values
	    String name = rs.getString(1);
	    String affiliation = rs.getString(2);
	    int num = rs.getInt(3);

	    // print values
	    out.write(name + "\t");
	    out.write(affiliation + "\t");
	    out.write(num + "");
	    out.write(" \n");
	}

	// close statement, connection, and output stream
	stmt.close();
	conn.close();
	out.close();
    } catch (java.lang.Exception ex) {

        ex.printStackTrace();
    }

  }

}