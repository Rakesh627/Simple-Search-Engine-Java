package webcrawler.project;
import java.awt.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{
	static java.util.List<String> urls = new ArrayList<String>(); 
	static int count =0;
	static String searchWord;
	 public static void main( String[] args ) throws java.net.MalformedURLException
	    {
		 //give the seed link and the search word
		 engine("http://www.wikipedia.org","the");
	    }
	 public static void engine(String url, String Word) throws MalformedURLException
	 {
		 searchWord = Word;
		 crawl(url);
	 }
    public static void crawl( String url ) throws java.net.MalformedURLException
    {
    	count++;
        Document doc;
		try {
			
			doc = Jsoup.connect(url).timeout(0).get() ;
			
			//check if url already exists
			if(urls.contains((Object)url))
			{
				//skip printing it
			}
			
			//if url is not in list
			else
				{
				urls.add(url);
				//check if url contains the word
				if(doc.text().contains(searchWord))
					{
					System.out.println(count);
						System.out.println(url);
					}
				 if(count==1000)
			        {
					 //System.exit(0);
			         throw new RuntimeException("Crawled through 1000 pages, crashing on purpose :) !");

			        }
			    //check the href links
				Elements newHeadlines = doc.select("a[href]");
		        for (Element e: newHeadlines)
		        {
		        	//if(e.attr("href").contains("mit.edu"))
		        	  crawl(e.attr("abs:href"));
		        }
				}
	       
		
		}
		catch(RuntimeException e)
		{
			throw new RuntimeException("Help!  Somebody debug me!  I'm crashing!");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
				
			}
			
		}
    }

