
public class CrawlerLink {
	private String url;
	private int linkid;
	public CrawlerLink(String url, int linkid) {
		super();
		this.url = url;
		this.linkid = linkid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLinkid() {
		return linkid;
	}
	public void setLinkid(int linkid) {
		this.linkid = linkid;
	}

}
