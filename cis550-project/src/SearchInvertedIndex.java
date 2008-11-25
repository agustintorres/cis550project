import java.sql.*;
import java.util.LinkedList;

public class SearchInvertedIndex {
	
	private LinkedList<String> words;
	private LinkedList<LinkedList<Integer>> results;
	private LinkedList<Integer> realResults;
	
	public SearchInvertedIndex(LinkedList<String> words){
		this.words = words;
	}
	
	public void createRanking(){
		
		DatabaseReader dr = new DatabaseReader();
		
		results = dr.getStoriesWithTerm(words);
		
		for(int x = 0;x < results.get(0).size(); x++){
			boolean flag = true;
			for(int y = 1; y < results.size(); y++){
				if(!results.get(y).contains(results.get(0).get(x))){
					flag = false;
					break;
				}
			}
			
			if(flag == true){
				realResults.add(results.get(0).get(x));
			}
			
		}
		

	}


}

