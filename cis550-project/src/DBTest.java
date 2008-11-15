
public class DBTest {

	public static void main(String argv[]) {
		String username = "bla";
		String email = "bla";
		String password = "bla";
		String name = "bla";
		String birthday = "21-jul-87";
		String location = "bla";
		String profession = "bla";
		User user = new User(username, password, birthday, location, profession);
		
		DatabaseRecorder dr = new DatabaseRecorder();
        dr.recordUser(user);
        
        System.out.println("done");
	}
}
