package marvel.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import marvel.api.APIConfiguration;

import org.apache.commons.io.IOUtils;

/**
 * Utilise une HttpURLConnection pour faire une requête unique
 */
public class SimpleHTTPRequest {
	
	public StringBuilder get(String url) {
		
		String proxyHost = APIConfiguration.getInstance().getProxyHost();
		String proxyPort = APIConfiguration.getInstance().getProxyPort();
		if(proxyHost != null && proxyPort != null) {
			System.setProperty("http.proxyHost", proxyHost);
			System.setProperty("http.proxyPort", proxyPort);
		}
		
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
			System.out.println("SET PROXY ?");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
		return sb;
	}
}