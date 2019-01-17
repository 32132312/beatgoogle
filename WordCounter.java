import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	public String urlStr;
	public String content;
	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}
	
	private String fetchContent() throws IOException {
		//in hw3
		URL url=new URL(this.urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) " 
                + "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
		conn.connect();
		InputStream in = conn.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
		String retVal="";
		String line = null;
		
		while((line=br.readLine())!=null){
			retVal = retVal + line +"\n";
		}
		return retVal;
		
	}
	
	public int countKeyword(String k) throws IOException{
		if(content==null) {
			content=fetchContent();
		}
		content=content.toUpperCase();
		k=k.toUpperCase();
		
		// to do : indexof()
		
		int index=0;
		int count=0;
		while(index!=-1) {
			index=content.indexOf(k);
			if(index!=-1) {
				count++;
				content = content.substring(index + k.length());
			}
		}
		return count;
	}
	

}
