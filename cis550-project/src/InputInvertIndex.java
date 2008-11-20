
import java.util.StringTokenizer;
import java.util.LinkedList;


public class InputInvertIndex {
	
	private static Story s;
	private static int sid;
	public static LinkedList<String> words;
	
	public InputInvertIndex(Story s, int sid){
		this.s = s;
		this.sid = sid;
	}
	
	public void recordStuff()
	{
		System.out.println("here");
		words = new LinkedList();
		String parseMe = s.getTitle() +" "+ s.getDescription();
		
		StringTokenizer st = new StringTokenizer(parseMe);
	     while (st.hasMoreTokens()) {
	         words.add(st.nextToken());
	     }
	     
	     System.out.println(parseMe);

	     DatabaseRecorder dr = new DatabaseRecorder();
	     dr.recordIndex(words, sid);
		
	}


}
