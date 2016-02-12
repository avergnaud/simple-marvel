package marvel.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import marvel.get.GetCharacters;
import marvel.get.ConstruitURL;

/**
 * Effective Java item 2
 */
public class CharactersService {

	private final int limite;
	private final String name;
	private final String nameStartsWith;
	private final LocalDate modifiedSince;

	public static class Builder {

		// Optionnal parameters
		private int limite;
		private String name;
		private String nameStartsWith;
		private LocalDate modifiedSince;

		public Builder limite(int val) {
			limite = val;
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
		limite = builder.limite;
		name = builder.name;
		nameStartsWith = builder.nameStartsWith;
		modifiedSince = builder.modifiedSince;
	}

	/**
	 * récupère les personnages
	 */
	public List<marvel.model.Character> get() {
		
		int limiteMarvel = 100;

		List<marvel.model.Character> characters = null;

		if (limite <= limiteMarvel) {
			String request = ConstruitURL.avecParams(0, limite, name,
					nameStartsWith, modifiedSince);
			characters = new GetCharacters().fromURL(request);
		} else {
			int quotient = limite / limiteMarvel;
			int remainder = limite % limiteMarvel;
			characters = new ArrayList<marvel.model.Character>();
			boolean resteDesPersoARecuperer = true;
			int i = 0;
			for (; i < quotient && resteDesPersoARecuperer; i++) {
				String request = ConstruitURL.avecParams(i * limiteMarvel,
						limiteMarvel, name, nameStartsWith, modifiedSince);
				List<marvel.model.Character> lot = new GetCharacters()
						.fromURL(request);
				if (lot.size() == 0) {
					resteDesPersoARecuperer = false;
				}
				characters.addAll(lot);
			}
			if(resteDesPersoARecuperer) {				
				String request = ConstruitURL.avecParams(i * limiteMarvel,
						remainder, name, nameStartsWith, modifiedSince);
				characters.addAll(new GetCharacters().fromURL(request));
			}
		}

		return characters;

	}

}
