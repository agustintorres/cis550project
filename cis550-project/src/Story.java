
public class Story {


		private String title;
		private String description;
		private String url;
		private String category;
		private int isPrivate;
		
		public Story(String title, String description, 
	                String url, String category, int isPrivate) {
			this.title = title;
			this.description = description;
			this.url = url;
			this.category = category;
			this.isPrivate = isPrivate;		
		}
		
		public String getTitle() { return title; }
		public String getDescription() { return description; }
		public String getURL() { return url; }
		public String getCategory() { return category; }
		public int getPrivate() { return isPrivate; }
			
}
