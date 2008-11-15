/*
 * $Id: HelloWorld.java 471756 2006-11-06 15:01:43Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package example;
import java.sql.*;
/**
 * <code>Set welcome message.</code>
 */
public class HelloWorld extends ExampleSupport {

    public String execute() throws Exception {
		String myout = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}

		catch (Exception E) {
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}

		try {
			java.sql.Connection conn;
	 
			conn = DriverManager.getConnection("jdbc:mysql://fling-l.seas.upenn.edu:3306/andrewcc?user=andrewcc&password=databases");
			
			Statement stmt = conn.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * from <tablename>");
			ResultSet rs = stmt.executeQuery("SHOW TABLES");
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				myout += (rs.getString(1) + "\n");
			}

			rs.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException E) {
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState:     " + E.getSQLState());
			System.out.println("VendorError:  " + E.getErrorCode());
		}	
        //setMessage(getText(MESSAGE));
		setMessage(myout);
        return SUCCESS;
    }

    /**
     * Provide default valuie for Message property.
     */
    public static final String MESSAGE = "HelloWorld.message";

    /**
     * Field for Message property.
     */
    private String message;

    /**
     * Return Message property.
     *
     * @return Message property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Message property.
     *
     * @param message Text to display on HelloWorld page.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}



