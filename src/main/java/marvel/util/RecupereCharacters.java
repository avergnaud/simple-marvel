package marvel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import marvel.api.CharactersAPI;
import marvel.model.CharacterDataWrapper;

import com.google.gson.Gson;

public class RecupereCharacters {

	/**
	 * Appelle charactersURIRequest et returne les persos MARVEL
	 * @param URL
	 * @return persos marvel.model.Character
	 */
	public static List<marvel.model.Character> depuisURL(CharactersAPI api,
			String charactersURIRequest) {

		// Proxy éventuel
		String proxyHost = api.proxyHost;
		String proxyPort = api.proxyPort;
		if (proxyHost != null && proxyPort != null) {
			System.setProperty("http.proxyHost", proxyHost);
			System.setProperty("http.proxyPort", proxyPort);
		}

		//Appel HTTP
		InputStream is = null;
		BufferedReader br = null;
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			URLConnection connection = new URL(charactersURIRequest)
					.openConnection();
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (UnknownHostException e) {
			System.out.println("SET PROXY ?");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}

		// Mapping json vers java
		String json = sb.toString();
		Gson gson = new Gson();
		CharacterDataWrapper resultatRequeteObjet = gson.fromJson(json,
				CharacterDataWrapper.class);
		List<marvel.model.Character> maListe = resultatRequeteObjet.getData()
				.getResults();

		return maListe;
	}
}