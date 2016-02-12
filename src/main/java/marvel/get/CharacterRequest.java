package marvel.get;

import java.util.List;

import marvel.model.CharacterDataWrapper;

import com.google.gson.Gson;

public class CharacterRequest {

	public List<marvel.model.Character> getCharacters(String charactersURIRequest) {

		System.out.println("REQUETE - URL : " + charactersURIRequest);
		
		// Appel HTTP
		StringBuilder sb = new SimpleHTTPRequest().get(charactersURIRequest);
		// Mapping json vers java
		String json = sb.toString();
		Gson gson = new Gson();
		CharacterDataWrapper resultatRequeteObjet = gson.fromJson(json,
				CharacterDataWrapper.class);
		List<marvel.model.Character> maListe = resultatRequeteObjet.getData()
				.getResults();
		
		System.out.println("REPONSE - size : " + maListe.size());

		return maListe;
	}

}
