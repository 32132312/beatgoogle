import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {
	public ArrayList<String>urllst=new ArrayList<String>();
	public ArrayList<String>namelst=new ArrayList<String>();
	public String searchKeyword;
	public String url;
	public String content;
	
	private int httpOK = HttpURLConnection.HTTP_OK;
	private final int error504=HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
	private final int error500=HttpURLConnection.HTTP_INTERNAL_ERROR;
	private final int error404=HttpURLConnection.HTTP_NOT_FOUND;
	private final int error403=HttpURLConnection.HTTP_FORBIDDEN;
	private final int error402=HttpURLConnection.HTTP_UNAUTHORIZED;
	
	
	public GoogleQuery(String searchKeyword) {
		this.searchKeyword = searchKeyword;
		this.url="https://www.google.com.tw/search?q="+searchKeyword+"&oe=utf8&num=100";
	}
	private String fetchContent() throws IOException {
		String retVal="";
		URL urlStr=new URL(this.url);
		URLConnection connection = urlStr.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) " 
                + "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
		connection.connect();
		InputStream inputStream =connection.getInputStream();
		InputStreamReader inReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bf =new BufferedReader(inReader);
		
		String line =null;
		while((line=bf.readLine())!=null) {
			retVal+=line;
		}
		return retVal;
	}
	public HashMap<String,String>query() throws IOException{
		if(this.content==null) {
			this.content=fetchContent();
		}
		HashMap<String,String>retVal=new HashMap<String,String>();
		Document document=Jsoup.parse(this.content);
		Elements lis=document.select("div.g");
		
		for(Element li: lis) {
			try {
				Element h3=li.select("h3.r").get(0);			
				String title =h3.text();
				
				Element cite =li.select("a").get(0);
				String citeUrl=cite.attr("href");
				citeUrl = URLDecoder.decode(citeUrl.substring(citeUrl.indexOf('=')+1 , citeUrl.indexOf('&')),"UTF-8");
				
				if(!citeUrl.startsWith("http")) {
					continue;
				}
				
				URL url=new URL(citeUrl);
				HttpURLConnection uc = (HttpURLConnection)url.openConnection();
				uc.connect();
				int status = uc.getResponseCode();
				if(status!= httpOK) {
					switch(status) {
					case error504:
						System.out.println("Gateway Timeout");
						break;
					case error500:
						System.out.println("Internal Server Error");
						break;
					case error404:
						System.out.println("Not found");
						break;
					case error403:
						System.out.println("forbidden");
						break;
					case error402:
						System.out.println("unauthorized");
						break;
					default:
						System.out.println("unknown error");
					}
					continue;
				}
			    urllst.add(citeUrl);
				namelst.add(title);

				System.out.println(title+" "+citeUrl);
				retVal.put(title, citeUrl);
				
				}catch(Exception e) {
					//DO NOTHING
				}
		}
	
		return retVal;
	}
}
