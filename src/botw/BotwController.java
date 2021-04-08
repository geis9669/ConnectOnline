package botw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BotwController {
	
	public void start()
	{
		String inputHtml = "C:/Users/grego/Desktop/hyruleMapCopy.html";
		File inputFile = new File(inputHtml);
		
		List<String> htmlfile = new ArrayList<>();
		Stack<String> lastToken = new Stack<>();
		
		try(Scanner reader = new Scanner(inputFile);) {
			while (reader.hasNext("<")) {
				String temp = reader.next(">");
				
				
				
				htmlfile.add(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
