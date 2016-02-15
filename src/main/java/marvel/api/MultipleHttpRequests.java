package marvel.api;

import java.util.ArrayList;
import java.util.List;

import marvel.util.ConstruitURL;
import marvel.util.RecupereCharacters;

class MultipleHttpRequests extends CharactersRequest {
	MultipleHttpRequests(Builder builder) {
		super(builder);
	}

	public List<marvel.model.Character> get() {

		List<marvel.model.Character> characters = null;

		int quotient = limite / api.limiteMarvel;
		int remainder = limite % api.limiteMarvel;
		characters = new ArrayList<marvel.model.Character>();
		boolean resteDesPersoARecuperer = true;
		int i = 0;
		for (; i < quotient && resteDesPersoARecuperer; i++) {
			String request = ConstruitURL.avecLesParametres(api, i * api.limiteMarvel,
					api.limiteMarvel, name, nameStartsWith, modifiedSince);
			List<marvel.model.Character> lot = RecupereCharacters.depuisURL(api,
					request);
			if (lot.size() == 0) {
				resteDesPersoARecuperer = false;
			}
			characters.addAll(lot);
		}
		if (remainder != 0 && resteDesPersoARecuperer) {
			String request = ConstruitURL.avecLesParametres(api, i * api.limiteMarvel,
					remainder, name, nameStartsWith, modifiedSince);
			characters.addAll(RecupereCharacters.depuisURL(api, request));
		}

		return characters;

	}

}
