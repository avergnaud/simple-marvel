package marvel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import marvel.api.CharactersAPI;
import marvel.exception.Erreur500Exception;
import marvel.model.CharacterDataWrapper;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class RecupereCharacters {

	/**
	 * Appelle charactersURIRequest et returne les persos MARVEL
	 * @param URL
	 * @return persos marvel.model.Character
	 * @throws Erreur500Exception 
	 */
	public static List<marvel.model.Character> depuisURL(CharactersAPI api,
			String charactersURIRequest) throws Erreur500Exception {

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
			System.out.println(charactersURIRequest);
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
			throw new Erreur500Exception();
		} finally {
			IOUtils.closeQuietly(br);
		}

		String json = sb.toString();
		
		// Mapping json vers java
		if("".equals(json))
			throw new Erreur500Exception();
		
		Gson gson = new Gson();
		CharacterDataWrapper resultatRequeteObjet = gson.fromJson(json,
				CharacterDataWrapper.class);
		List<marvel.model.Character> maListe = resultatRequeteObjet.getData()
				.getResults();

		return maListe;
	}
}