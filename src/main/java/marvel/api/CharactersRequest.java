package marvel.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import marvel.util.ConstruitURL;
import marvel.util.RecupereCharacters;

/**
 * Effective Java item 2
 */
public class CharactersRequest {

	final CharactersAPI api;
	final int limite;
	final String name;
	final String nameStartsWith;
	final LocalDate modifiedSince;

	public static class Builder {

		//required - réf à l'api
		CharactersAPI api;
		
		// Optionnal parameters
		private int limite;
		private String name;
		private String nameStartsWith;
		private LocalDate modifiedSince;
		
		public Builder(CharactersAPI api) {
			this.api = api;
		}

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
		public CharactersRequest build() {
			if(limite <= api.limiteMarvel) {
				return new SingleHttpRequest(this);
			} else {
				return new MultipleHttpRequests(this);
			}
		}
	}

	CharactersRequest(Builder builder) {
		api = builder.api;
		limite = builder.limite;
		name = builder.name;
		nameStartsWith = builder.nameStartsWith;
		modifiedSince = builder.modifiedSince;
	}

	/**
	 * API ensures this method is always overridden
	 */
	public List<marvel.model.Character> get() {

		List<marvel.model.Character> characters = null;
		//must be overriden
		return characters;

	}

}
