package marvel;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import marvel.api.APIConfiguration;
import marvel.api.CharactersService;

public class Main {

	public static void main(String[] args) {

		//conf
		APIConfiguration.getInstance().init(
				"http://gateway.marvel.com/v1/public/characters", 
				"ac627b5a9da2dd5127e9583595c671f9", 
				"c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1", 
				"px-internet", 
				"80");

		//get
		CharactersService characters = new CharactersService.Builder()
				.limite(1000)
				.modifiedSince(LocalDate.of(2013, Month.JANUARY, 1))
				.build();
		List<marvel.model.Character> chars = characters.get();
		System.out.println("nombre de characters retournés " + chars.size());
	}
}