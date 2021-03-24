package botw;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class test {
	public static void main(String[] args) {
		try {
			Set<String> korokSeedsM = new HashSet<>();
			String[] webSites = {"Akkala", "Central_Hyrule", "Dueling_Peaks", 
								 "Eldin", "Faron", "Gerudo_Wasteland", 
								 "Gerudo_Highland", "Woodland", "Great_Plateau", 
								  "Hebra", "Lake",
								 "Lanayru", "Ridgeland", "Tabantha"};//Hateno
			
			for(String s : webSites)
			{
				// create the URL class object
				URL url = new URL("https://www.ign.com/wikis/the-legend-of-zelda-breath-of-the-wild/"+s+"_Korok_Seeds");
				String separator = "\\n\\u003c/p\\u003e\",\"tag\":\"p\",\"header\":false,\"block\":true}},{\"__typename\":\"WikiPageElement\"";// "\nid=\\"; //"\n-\n"

				Set<String> tempKorokSeeds = getKorokSeedSet(url, separator);
				
				int howmanyadded = 0;
				for(String seed : tempKorokSeeds) {
					if(seed.contains("id=\\\""+s+"_Korok_Seed_"))/* id=\"Akkala_Korok_Seed_ */ {
						korokSeedsM.add(seed);
						howmanyadded ++;
					}
				}
				
				System.out.println(tempKorokSeeds.size()+" added: "+howmanyadded);
			}
			
			//need to process Hateno separately Because it saves differently. also got some not right answers
			URL url = new URL("https://www.ign.com/wikis/the-legend-of-zelda-breath-of-the-wild/Hateno_Korok_Seeds");
			String separator = ".";
			Set<String> tempKorokSeeds = getKorokSeedSet(url, separator);
			int howmanyadded = 0;
			for(String seed : tempKorokSeeds) {
				if(seed.contains("Korok")){
					korokSeedsM.add(seed);
					howmanyadded ++;
				}
			}
			System.out.println(tempKorokSeeds.size()+" added: "+howmanyadded);
			
			System.out.println(korokSeedsM.size());

			String[] keyWords = {"arrow", "ballon"};
			
			String toSave = "";
			for(String item : korokSeedsM){
				
				boolean additem = false;
				int i = 0;
				while(!additem && i < keyWords.length) {
					if(item.contains(keyWords[i])) {
						toSave += "\n:::\n"+ item+"\n:::\n";
						//have it save only the id. so I will need to figure something else out for Hateno
						additem = true;
					}
					i++;
				}
			}
			
			saveFile(toSave,"arrow_ballon");
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("number of items: " + korokSeeds.size());
		// print the info I want to know.
	}
	
	private static void loadhyruleMap()
	{
		try {
			URL url = new URL("https://www.ign.com/maps/the-legend-of-zelda-breath-of-the-wild/hyrule");
			String separator = "\n-\n";
			getKorokSeedSet(url, separator);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	private static Set<String> getKorokSeedSet(URL url,  String separator) throws IOException {
		// retrieve the contents from the url
		Scanner sc = new Scanner(url.openStream());
		// Instantiate StringBuffer to hold the results
		StringBuffer sb = new StringBuffer();
		while (sc.hasNext()) {
			sb.append(sc.next() + "\n");
		}

		String result = sb.toString();
//		System.out.println(result);
		result = result.replaceAll("<[^>]*>", "");// <[^>]*>
//		System.out.println("Contents of the web page: " + result);

		HashSet<String> korokSeeds = new HashSet<>();
		int nexti;
		
		for (int i = result.indexOf(separator); i > 0; i = nexti) {
			nexti = result.indexOf(separator, i + 1);
			String info;
			if (nexti >= 0) {
				info = result.substring(i, nexti);
				korokSeeds.add((info));
			}
		}
		return korokSeeds;

	}

	private static void saveFile(String info, String name) {
		String filename = "KorokSeeds"+name+".txt";

		try (FileOutputStream saveStream = new FileOutputStream(filename);
				ObjectOutputStream output = new ObjectOutputStream(saveStream)) {

			output.writeObject(info);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

}
