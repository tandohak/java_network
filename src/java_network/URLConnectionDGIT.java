package java_network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionDGIT {

	public static void main(String[] args) {
		try {
			URL dgit = new URL("http://dgit.or.kr/login.php");
			URLConnection urlConnection = dgit.openConnection();
			urlConnection.setDoOutput(true);
			
			OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
			osw.write("user_id=ypyo09&userpasswd=");
			//http://dgit.or.kr/login.php?user_id=ypyo09&userpasswd=
			osw.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while((line=br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
