
public class WebScore {
	public String name;
	public String url;
	public double score;
	
	public WebScore(String name, String url, double score) {
		this.name = name;
		this.url = url;
		this.score = score;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
