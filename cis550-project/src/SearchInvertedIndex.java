import java.sql.*;
import java.util.LinkedList;

public class SearchInvertedIndex {
	
	private LinkedList<String> words;
	private LinkedList<LinkedList<Integer>> results;
	
	public SearchInvertedIndex(LinkedList<String> words){
		this.words = words;
	}
	
	public void createRanking(){
		
		DatabaseReader dr = new DatabaseReader();
		
		results = dr.getStoriesWithTerm(words);

	}


}

