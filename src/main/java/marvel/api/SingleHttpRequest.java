package marvel.api;

import java.util.List;

import marvel.util.ConstruitURL;
import marvel.util.RecupereCharacters;


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
	
}
