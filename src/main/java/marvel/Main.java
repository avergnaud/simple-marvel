package marvel;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import marvel.api.CharactersAPI;
import marvel.api.request.CharactersRequest;

public class Main {

	public static void main(String[] args) {

		//conf
		CharactersAPI api = CharactersAPI
			.configureKeys("ac627b5a9da2dd5127e9583595c671f9", "c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1")
			.configureProxyHost("px-internet")/*optional*/
			.configureProxyPort("80")/*optional*/
			.rootUlr("http://gateway.marvel.com/v1/public/characters")/*optional*/
			.limiteMarvel(20)/*optional*/
			.init();
		
		CharactersRequest firstRequest = api.requestBuilder()
				.limite(2000)/*optional*/
				.modifiedSince(LocalDate.of(2015, Month.JANUARY, 1))/*optional*/
				.nameStartsWith("T")/*optional*/
				.build();
		
		List<marvel.model.Character> persos = firstRequest.get();
		
		System.out.println("nombre de characters retournés " + persos.size());
		
		persos.stream()
			.filter(character -> !"".equals(character.getDescription()))
			.forEach(System.out::println);

	}
}