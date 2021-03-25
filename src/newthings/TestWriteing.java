package newthings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TestWriteing {
	public static void main(String[] args) {
		
		try {
			String stringToReverse = URLEncoder.encode("\"something\"", "UTF-8");
			
			try {
				URL url = new URL("");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write("string=" + stringToReverse);
				out.close();
				
				BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
				
				String decodedString;
				while((decodedString = in.readLine()) != null) {
					System.out.println(decodedString);
				}
				in.close();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
