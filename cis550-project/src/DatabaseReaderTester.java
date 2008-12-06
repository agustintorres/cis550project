import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;


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
		
		System.out.println("Published stories:\n");
		if ( dbr.userHasPublishedAStory("agustin") != null ) {
			System.out.println("user has published at least one story");
			HashSet<Story> stories = dbr.userHasPublishedAStory("agustin");
			Iterator<Story> iter = stories.iterator();
			System.out.println(stories.size());
			while (iter.hasNext()) {
				System.out.println("inside while");
				Story story = iter.next();
				System.out.println("Story id: " + story.getStoryid());
			}
			
		}
		
		System.out.println("Commented on stories:\n");
		if ( dbr.userHasCommentedOnAStory("agustin") != null ) {
			System.out.println("user has commented on at least one story");
			HashSet<Story> stories = dbr.userHasPublishedAStory("agustin");
			Iterator<Story> iter = stories.iterator();
			System.out.println(stories.size());
			while (iter.hasNext()) {
				System.out.println("inside while");
				Story story = iter.next();
				System.out.println("Story id: " + story.getStoryid());
			}
		}
		
		System.out.println("Voted on stories:\n");
		if ( dbr.userHasVotedOnAStory("agustin") != null ) {
			System.out.println("user has voted on at least one story");
			HashSet<Story> stories = dbr.userHasPublishedAStory("agustin");
			Iterator<Story> iter = stories.iterator();
			System.out.println(stories.size());
			while (iter.hasNext()) {
				System.out.println("inside while");
				Story story = iter.next();
				System.out.println("Story id: " + story.getStoryid());
			}
		}

	}

}
