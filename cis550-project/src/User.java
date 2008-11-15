
public class User {

	private String username;
	private String password;
	private String birthday;
	private String location;
	private String profession;
	
	public User(String username, String password, 
                String birthday, String location, String profession) {
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.location = location;
		this.profession = profession;		
	}
	
	
	public String getUsername() { return username; }
		
	public String getPassword() { return password; }
		
	public String getBirthday() { return birthday; }
	
	public String getLocation() { return location; }
	
	public String getProfession() { return profession; }
	
}
