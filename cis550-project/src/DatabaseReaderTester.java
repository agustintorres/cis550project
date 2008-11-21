
public class DatabaseReaderTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseReader dbr = new DatabaseReader();
		String user = "amtq";
		String pass = "agustin";
		if ( dbr.isValidAuthentication(user, pass) ) {
			System.out.println("This authentication is valid");
		} else {
			System.out.println("Incorrect username or password.");
		}

	}

}
