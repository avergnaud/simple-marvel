package marvel.api.request;

import java.util.ArrayList;
import java.util.List;

import marvel.exception.Erreur500Exception;
import marvel.util.ConstruitURL;
import marvel.util.RecupereCharacters;

/*
 * Récupère des persos en plusieurs requetes
 */
class MultipleHttpRequests extends CharactersRequest {
	MultipleHttpRequests(Builder builder) {
		super(builder);
	}

	public List<marvel.model.Character> get() {
		
		List<marvel.model.Character> characters = new ArrayList<marvel.model.Character>();
		alimente(characters);
		return characters;
	}

	public void alimente(List<marvel.model.Character> characters) {

		int quotient = limite / api.limiteMarvel;
		int remainder = limite % api.limiteMarvel;
		boolean resteDesPersoARecuperer = true;
		int i = 0;
		for (; i < quotient && resteDesPersoARecuperer; i++) {
			String request = ConstruitURL.avecLesParametres(api, i
					* api.limiteMarvel, api.limiteMarvel, name, nameStartsWith,
					modifiedSince);
			List<marvel.model.Character> lot = new ArrayList<marvel.model.Character>();
					
			try {
				lot = RecupereCharacters.depuisURL(
						api, request);
				if (lot.size() == 0) {
					resteDesPersoARecuperer = false;
				}
			} catch (Erreur500Exception e) {
				System.out.println("MARVEL 500");
			}
			characters.addAll(lot);
		}
		if (remainder != 0 && resteDesPersoARecuperer) {
			String request = ConstruitURL.avecLesParametres(api, i
					* api.limiteMarvel, remainder, name, nameStartsWith,
					modifiedSince);
			characters.addAll(RecupereCharacters.depuisURL(api, request));
		}


	}

}
