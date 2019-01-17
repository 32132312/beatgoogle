import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Main() {
        super();}
    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			if(request.getParameter("keyword")== null) {
				String requestUri = request.getRequestURI();
				request.setAttribute("requestUri", requestUri);
				request.getRequestDispatcher("Search.jsp").forward(request, response);
				return;
			}
			
				GoogleQuery googleQuery=new GoogleQuery(request.getParameter("keyword"));
				googleQuery.query();
				ArrayList<WebScore>score=new ArrayList<>();
				for(int i=0;i<googleQuery.urllst.size();i++) {
					
					WebPage rootPage=new WebPage(googleQuery.urllst.get(i),googleQuery.namelst.get(i)) ;
					WebTree tree = new WebTree(rootPage);
					
					
					ArrayList<Keyword> keywords = new ArrayList<>();
					//keywords.add(new Keyword(searchWord, 3));
					
				    keywords.add(new Keyword("心得", 10.5));
				    keywords.add(new Keyword("考試", 0.5));
				    keywords.add(new Keyword("經驗",9.5));
				    keywords.add(new Keyword("分享",2.5));
				    keywords.add(new Keyword("請問",2.5));
				    keywords.add(new Keyword("比較",2.2));
				    keywords.add(new Keyword("vs", 3.7));
				    
				    tree.setPostOrderScore(keywords);
				    tree.printTree();
				    
				    score.add(new WebScore(googleQuery.namelst.get(i),googleQuery.urllst.get(i),tree.root.nodeScore));
				    Collections.sort(score,new Comparator<WebScore>() {
					@Override
					public int compare(WebScore a,WebScore b) {
						if(a.getScore()>b.getScore())return -1;
						if(a.getScore()<b.getScore())return 1;
						return 0;
					}
				});
				 }
				System.out.println(score.toString());
			String[][] s = new String[score.size()][2];
			request.setAttribute("query", s);
			int num = 0;
			for(int i = 0;i<score.size();i++) {
			    String key = score.get(i).getName();
			    String value = score.get(i).getUrl();
			    s[num][0] = key;
			    s[num][1] = value;
			    num++;
			}
			request.getRequestDispatcher("googleitem.jsp")
			 .forward(request, response); 
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
	}


