package marvel.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import marvel.get.CharacterRequest;
import marvel.get.CharactersURI;

/**
 * Builder
 */
public class CharactersService {

	private final int limite;
	private final String name;
	private final String nameStartsWith;
	private final LocalDate modifiedSince;

	public static class Builder {

		// Optionnal parameters
		private int limiteCat;
		private String name;
		private String nameStartsWith;
		private LocalDate modifiedSince;

		public Builder limiteCat(int val) {
			limiteCat = val;
			return this;
		}
		public Builder name(String val) {
			name = val;
			return this;
		}
		public Builder nameStartsWith(String val) {
			nameStartsWith = val;
			return this;
		}
		public Builder modifiedSince(LocalDate val) {
			modifiedSince = val;
			return this;
		}
		public CharactersService build() {
			if (APIConfiguration.getInstance() == null) {
				throw new RuntimeException("APIConfiguration not initialized");
			}
			return new CharactersService(this);
		}
	}

	private CharactersService(Builder builder) {
		limite = builder.limiteCat;
		name = builder.name;
		nameStartsWith = builder.nameStartsWith;
		modifiedSince = builder.modifiedSince;
	}

	/**
	 * entrée : rien, retour : List<Chara>
	 */
	public List<marvel.model.Character> get() {
		
		int limiteMarvel = 100;

		List<marvel.model.Character> characters = null;

		if (limite <= limiteMarvel) {
			String request = CharactersURI.getRequestURL(0, limite, name,
					nameStartsWith, modifiedSince);
			characters = new CharacterRequest().getCharacters(request);
		} else {
			int quotient = limite / limiteMarvel;
			int remainder = limite % limiteMarvel;
			characters = new ArrayList<marvel.model.Character>();
			boolean resteDesPersoARecuperer = true;
			int i = 0;
			for (; i < quotient && resteDesPersoARecuperer; i++) {
				String request = CharactersURI.getRequestURL(i * limiteMarvel,
						limiteMarvel, name, nameStartsWith, modifiedSince);
				List<marvel.model.Character> lot = new CharacterRequest()
						.getCharacters(request);
				if (lot.size() == 0) {
					resteDesPersoARecuperer = false;
				}
				characters.addAll(lot);
			}
			if(resteDesPersoARecuperer) {				
				String request = CharactersURI.getRequestURL(i * limiteMarvel,
						remainder, name, nameStartsWith, modifiedSince);
				characters.addAll(new CharacterRequest().getCharacters(request));
			}
		}

		return characters;

	}

}
