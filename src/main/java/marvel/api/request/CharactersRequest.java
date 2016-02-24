package marvel.api.request;

import java.time.LocalDate;
import java.util.List;

import marvel.api.CharactersAPI;

/**
 * api.requestBuilder().build()
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
		/**default is 20*/
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
	 * returns a list of MARVEL characters
	 */
	public List<marvel.model.Character> get() {

		List<marvel.model.Character> characters = null;
		//API ensures this method is always overridden
		return characters;

	}

	public void alimente(List<marvel.model.Character> liste) {
		//API ensures this method is always overridden
	}
	
}
