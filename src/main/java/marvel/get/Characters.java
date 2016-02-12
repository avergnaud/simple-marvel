package marvel.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import marvel.props.MyProperties;

/**
 * http://programmers.stackexchange.com/questions/118039/benefit-of-using-static-inner-builder-class
 * compliqué par la contrainte "immutable"
 * effective Java item 2
 */
public class Characters {

	//Optionnal parameters
	private final int limiteCat;
	private final String name;
	private final String nameStartsWith;
	private final LocalDate modifiedSince;
	
	public static class Builder {
		
		//Optionnal parameters
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
		
		public Characters build() {
			return new Characters(this);
		}
	}
	
	private Characters(Builder builder) {
		limiteCat = builder.limiteCat;
		name = builder.name;
		nameStartsWith = builder.nameStartsWith;
		modifiedSince = builder.modifiedSince;
	}
	
	/**
	 * entrée : rien,
	 * retour : List<Chara>
	 */
	public List<marvel.model.Character> get() {
		int limiteMarvel = Integer.parseInt(MyProperties.getInstance().get("limiteMarvel"));
		
		List<marvel.model.Character> characters = null;
		
		if(limiteCat <= limiteMarvel) {
			String request = CharactersURI.getRequest(0, limiteCat, name, nameStartsWith, modifiedSince);
			characters = new CharacterRequest().getCharacters(request);
		} else {
			int quotient = limiteCat / limiteMarvel;
			int remainder = limiteCat % limiteMarvel;
			characters = new ArrayList<marvel.model.Character>();
			boolean resteDesPersoARecuperer = true;
			int i = 0;
			for(; i<quotient && resteDesPersoARecuperer; i++) {
				String request = CharactersURI.getRequest(i*limiteMarvel, limiteMarvel, name, nameStartsWith, modifiedSince);
				List<marvel.model.Character> lot = new CharacterRequest().getCharacters(request);
				if(lot.size() == 0) {
					resteDesPersoARecuperer = false;
				}
				characters.addAll(lot);
			}
			String request = CharactersURI.getRequest(i*limiteMarvel, remainder, name, nameStartsWith, modifiedSince);
			characters.addAll(new CharacterRequest().getCharacters(request));
		}
		
		return characters;
		
	}
	
}
