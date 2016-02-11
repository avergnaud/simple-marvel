package marvel.get;

import java.net.URLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.io.BufferedReader;

import marvel.props.MyProperties;

public class SingleRequest {
	
	MyProperties props = new MyProperties();
	int tries = 0;
	
	public StringBuilder get(String url) {
		if(tries > 1) return null;
	
		InputStream is = null;
		BufferedReader br = null;
		String line;
		StringBuilder sb = new StringBuilder();
		
		try {
			URLConnection connection = new URL(url).openConnection();
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (UnknownHostException e) {
			System.out.println("retrying with proxy");
			System.setProperty("http.proxyHost", props.get("maafproxy.host"));
			System.setProperty("http.proxyPort", props.get("maafproxy.port"));
			tries++;
			sb = get(url);
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}
		return sb;
	}
}