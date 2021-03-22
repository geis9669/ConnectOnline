package botw;


public class test {
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
