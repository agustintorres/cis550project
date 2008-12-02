
public class Comment {


		private String text;

		
		public Comment(String text) {
			this.text = text;

		}
		
		public Comment(){
			
		}
		
		public String toHtml(){
			String outHtml = "";
			
			outHtml += ("Said: " + getText() + ". <br>");
			
			return outHtml;
		}
		
		public String getText() { return text; }

		
		public void setText(String t) {  text = t; }

}
