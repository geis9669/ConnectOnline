package botw;


public class test {

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
