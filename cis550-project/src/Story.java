
public class Story {


		private String title;
		private String description;
		private String url;
		private String category;
		private String name;
		private int isPrivate;
		private int storyid;
		private int votes;
		private String storytime;
		
		public Story(String title, String description, 
	                String url, String category, int isPrivate, String name) {
			this.title = title;
			this.description = description;
			this.url = url;
			this.category = category;
			this.isPrivate = isPrivate;		
			this.name = name;
		}
		
		public Story(){
			
		}
		
		public String getTitle() { return title; }
		public String getName(){return name;}
		public String getDescription() { return description; }
		public String getURL() { return url; }
		public String getCategory() { return category; }
		public int getPrivate() { return isPrivate; }
		public int getStoryid() {return storyid;}
		public int getVotes() { return votes;}
		public String getStorytime() {return storytime;}
		
		public void setTitle(String t) {  title = t; }
		public void setName(String n){ name = n;}
		public void setDescription(String d) {  description= d; }
		public void setURL(String u) {  url = u; }
		public void setCategory(String c) {  category = c; }
		public void setPrivate(int p) {  isPrivate = p; }
		public void setStoryid(int id) {storyid = id;}
		public void setVotes(int v) {votes = v;}
		public void setStorytime (String t) {storytime = t;}
}
