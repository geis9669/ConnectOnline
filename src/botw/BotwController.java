package botw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BotwController {
	private String inputHtml = "C:/Users/grego/Desktop/hyruleMapCopy.html";
	private Document htmlDoc;
	
	
	public BotwController() throws IOException
	{
//		this.inputHtml = htmlDocPathName;
		File inputFile = new File(inputHtml);
		this.htmlDoc = Jsoup.parse(inputFile, "UTF-8", "https://www.ign.com/maps/the-legend-of-zelda-breath-of-the-wild/hyrule");

	}
	
	public void start()
	{
		String inputHtml = "C:/Users/grego/Desktop/hyruleMapCopy.html";
		File inputFile = new File(inputHtml);
		
		try (Scanner reader = new Scanner(inputFile);){
			Document doc = Jsoup.parse(inputFile, "UTF-8", "https://www.ign.com/maps/the-legend-of-zelda-breath-of-the-wild/hyrule");
			Elements content = doc.children().get(0).children().get(1).children().get(3).children().get(0).children().get(1).children().get(0).children().get(0).children().get(0).children().get(0).children();// doc.getAllElements();
			
			Element c = content.get(3);
			String list = c.html();
			String[] listItems = list.split("<div");
			
			
			// go through the list and remove what I don't want
			
			// go through the original document to where the list starts, 
			// then add what is in the listItems
			// then add the last part of the text.
			
			while (reader.hasNext()) {
				reader.next();
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//List<String> other thing to return.
	public String[] gettingMapMarkers()
	{
		Elements content = htmlDoc.children().get(0).children().get(1).children().get(3).children().get(0).children().get(1).children().get(0).children().get(0).children().get(0).children().get(0).children();// doc.getAllElements();
		Element c = content.get(3);
		String spilter = ("<div");
		String list = c.html();
		list = list.substring(spilter.length());
		String[] listItems = list.split(spilter);
		for(int i = 0; i< listItems.length; i++) {
			listItems[i] = spilter + listItems[i];
		}
		return listItems;//Arrays.asList(listItems);
	}
	
	public static void moveIfContains(List<String> a, List<String> b, String condition)
	{
		for(int i = a.size()-1; i>-1; i--)
		{
			if(a.get(i) != null && a.get(i).contains(condition))
			{
				String move = a.remove(i);
				b.add(move);
			}
		}
	}
	/*
	 * a.size()
	 * 
	 * a.get(i).contains(condition) biggest
	 * 
	 * String move = a.remove(i);
	 * b.add(move);
	 */
	
	/*
	 * Ask user for the file to open and the webpage
	 * read in the web page
	 * 
	 * find the list 
	 * separate it out
	 * allow the user to remove the items from the list
	 * 
	 * ask for the file name to save
	 * add back the list to the original document.
	 */

}
