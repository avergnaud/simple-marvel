package marvel.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import marvel.props.MyProperties;

import org.apache.commons.io.IOUtils;

/**
 * Utilise une HttpURLConnection pour faire une requête unique
 */
public class SimpleHTTPRequest {
	
	MyProperties props = MyProperties.getInstance();
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
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
		return sb;
	}
}