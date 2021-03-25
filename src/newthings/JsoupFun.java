package newthings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupFun {

	public static void main(String[] args) {

		JsoupFun test = new JsoupFun();
		test.loadFromTextDoc();
	}

	public void loadFromTextDoc() {
		String txtDoc = "C:/Users/grego/Desktop/hyruleMapCopy.html";
		File inputFile = new File(txtDoc);
		try {
			Document doc = Jsoup.parse(inputFile, "UTF-8", "https://www.ign.com/maps/the-legend-of-zelda-breath-of-the-wild/hyrule");
			
			Elements content = doc.children().get(0).children().get(1).children().get(3).children().get(0).children().get(1).children().get(0).children().get(0).children().get(0).children().get(0).children();// doc.getAllElements();
			// doc.children().get(0).children().get(1).children().get(3).children().get(0).children().get(1).children().get(0).children().get(0).children().get(0).children().get(0).children().get(3).children();
			
			String output = content.size()+ "size\n";
//			for(Element e : content)
//			{
//				String current = e.html() +"\n---\n";// .data .html .className .
//				if(e.className().equals("leaflet-marker-icon leaflet-zoom-animated leaflet-interactive")) {
//					
//				}
//				output += e.className()+ "\n"+ e.html() + "\n---\n";
//				System.out.println(e.data());
//				System.out.println("----");
//			}
			
//			for(int i = content.size()-1; i>-1; i--){
//				Element e = content.get(i);
//				if(e.className().equals("leaflet-marker-icon leaflet-zoom-animated leaflet-interactive")) {
//					content.remove(i);
//					output += e.className()+ "\n"+ e.html() + "\n---\n";
//				}
//				String current = e.html() +"\n---\n";// .data .html .className .
//				System.out.println(e.data());
//				System.out.println("----");
//			}
			
			Element c = content.get(3);
			String list = c.html();
			c.html("");
			c.remove();
			
			String[] listItems = list.split("<div");// . or *
			
			int numOfKorok = 0; String korokSeeds = "";
			int numOfShrine = 0; String shrines = "";
			int numlynel = 0; String lynel = "";
			int numOther = 0; String other = "";
			for(String s : listItems) {
//				System.out.println(s + "\n----\n");
				String sOut = "<div" + s+ "\n----\n";
				
				if(s.contains("alt=\"Korok") | s.contains("alt=\"korok")) {
					numOfKorok ++;// gets korok forest
					korokSeeds+=sOut;
				}else if(s.contains("Shrine")) {
					numOfShrine ++;
					shrines+=sOut;
				}else if(s.contains("Lynel")) {
					numlynel ++;
					lynel +=sOut;
				}else {
					numOther++;
					other += sOut;
				}
			}
			output = "";
			output += listItems.length+" total number of items\n";
			output += numOfKorok + " numer of korok seeds\n";
			output += korokSeeds + "\n";
			output += numOfShrine + " numer of Shrines\n";
			output += shrines + "\n";
			output += numlynel + " numer of lynel\n";
			output += lynel + "\n";
			output += numOther + " numer of otheritems\n";
			output += other + "\n";
			
			System.out.println(numOfKorok+ " number of korok seeds");
			System.out.println(numOfShrine+ " number of Shrines");
//			Elements content2 = doc.getAllElements();
//			output += content2.size() + "sizeOfModified\n";
//			for(Element e : content2){
//				output += e.className() + "\n---\n";
//			}
			
			saveTextFile(output, "created/hyruleItems.txt");
			
			
			String saveDoc = "created/hyruleMapModRemoved.html";
			String newhtmlinfo = doc.html();
//			saveTextFile(newhtmlinfo, saveDoc);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		

	}
	
	

	private String loadtextFile(String filename) {
		File inputFile = new File(filename);
		String html = "";
		try (Scanner reader = new Scanner(inputFile);) {
			while (reader.hasNext()) {
				html += reader.nextLine() + "\n";
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
	
	private void saveTextFile(String textToSave, String filename){
		try (PrintWriter writer = new PrintWriter(filename);
				Scanner output = new Scanner(textToSave)){
			
			while (output.hasNext()) {
				String currentLine = output.nextLine();
				writer.println(currentLine);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadDirectlyFromWebpage() {
		String webPage = "https://www.ign.com/maps/the-legend-of-zelda-breath-of-the-wild/hyrule";
		try {
			Document htmlDoc = Jsoup.connect(webPage).get();
			String html = htmlDoc.html();

			System.out.println("" + "\ndone");
//			System.out.println(html);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
