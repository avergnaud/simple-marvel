package marvel;

import java.util.List;

import marvel.get.CharacterRequest;
import marvel.get.Characters;

/**
 * Récupère les 20 premiers perso MARVEL
 */
public class Main {

	public static void main(String[] args) {
	
		//Récupération et écriture
		CharacterRequest request = new CharacterRequest();
		Characters characters = new Characters.Builder()
		.limiteCat(10000)
		.build();
		List<marvel.model.Character> chars = characters.get();
		System.out.println("nombre de characters retournés " + chars.size());
	}
}