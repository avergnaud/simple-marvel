package marvel.api.request;

import java.util.List;

import marvel.util.ConstruitURL;
import marvel.util.RecupereCharacters;

/*
 * Récupère des persos en une seule requête
 */
class SingleHttpRequest extends CharactersRequest {
	SingleHttpRequest(Builder builder) {
		super(builder);
	}

	public List<marvel.model.Character> get() {

		List<marvel.model.Character> characters = null;

		String request = ConstruitURL.avecLesParametres(api, 0, limite, name,
				nameStartsWith, modifiedSince);
		characters = RecupereCharacters.depuisURL(api, request);

		return characters;

	}

	public void alimente(List<marvel.model.Character> characters) {

		String request = ConstruitURL.avecLesParametres(api, 0, limite, name,
				nameStartsWith, modifiedSince);
		characters = RecupereCharacters.depuisURL(api, request);

	}

}
