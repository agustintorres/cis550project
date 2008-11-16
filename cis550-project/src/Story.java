
public class Story {


		private String title;
		private String description;
		private String url;
		private String category;
		private String name;
		private int isPrivate;
		
		public Story(String title, String description, 
	                String url, String category, int isPrivate, String name) {
			this.title = title;
			this.description = description;
			this.url = url;
			this.category = category;
			this.isPrivate = isPrivate;		
			this.name = name;
		}
		
		public String getTitle() { return title; }
		public String getName(){return name;}
		public String getDescription() { return description; }
		public String getURL() { return url; }
		public String getCategory() { return category; }
		public int getPrivate() { return isPrivate; }
			
}
