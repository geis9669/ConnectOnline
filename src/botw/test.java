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
			Set<KorokSeed> korokSeedsM = new HashSet<>();
			String[] webSites = {"Akkala", "Central_Hyrule", "Dueling_Peaks", 
								 "Eldin", "Faron", "Gerudo_Wasteland", 
								 "Gerudo_Highland", "Woodland", "Great_Plateau", 
								 "Hateno", "Hebra", "Lake",
								 "Lanayru", "Ridgeland", "Tabantha"};
			
			for(String s : webSites)
			{
				// create the URL class object
				URL url = new URL("https://www.ign.com/wikis/the-legend-of-zelda-breath-of-the-wild/"+s+"_Korok_Seeds");
				Set<KorokSeed> tempKorokSeeds = getKorokSeedSet(url);
				for(KorokSeed seed : tempKorokSeeds) {
//					if(seed.containsString("_Korok_Seed_"))/*"id=\\"+s+*/ {
						korokSeedsM.add(seed);
//					}
				}
			}
			
			System.out.println(korokSeedsM.size());

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

	private static Set<KorokSeed> getKorokSeedSet(URL url) throws IOException {
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

		HashSet<KorokSeed> korokSeeds = new HashSet<>();
		int nexti;
		String separator = "\\n\\u003c/p\\u003e\",\"tag\":\"p\",\"header\":false,\"block\":true}},{\"__typename\":\"WikiPageElement\"";// "\nid=\\"; //"\n-\n"

		for (int i = result.indexOf(separator); i > 0; i = nexti) {
			nexti = result.indexOf(separator, i + 1);
			String info;
			if (nexti >= 0) {
				info = result.substring(i, nexti);
				korokSeeds.add(new KorokSeed(info));
			}
		}
		return korokSeeds;

	}

	private static void saveFile(String info) {
		String filename = "KorokSeeds.txt";

		try (FileOutputStream saveStream = new FileOutputStream(filename);
				ObjectOutputStream output = new ObjectOutputStream(saveStream)) {

			output.writeObject(info);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

}
