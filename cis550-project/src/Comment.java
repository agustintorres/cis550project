
public class Comment {


		private String text;
		private String author;
		private int story;
		private String commenttime;
		
		public String getCommenttime() {
			return commenttime;
		}

		public void setCommenttime(String commenttime) {
			this.commenttime = commenttime;
		}

		public Comment(String text, String author, int story) {
			this.text = text;
			this.author = author;
			this.story = story;

		}
		
		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public int getStory() {
			return story;
		}

		public void setStory(int story) {
			this.story = story;
		}

		public Comment(){
			
		}
		
		public String toHtml(){
			String outHtml = "<br>";
			
			outHtml += (getAuthor() + " at " + getCommenttime() + ": " + getText() + ". <br>");
			
			return outHtml;
		}
		
		public String getText() { return text; }

		public void setText(String t) {  text = t; }

}
