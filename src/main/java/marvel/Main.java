package marvel;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import marvel.api.CharactersAPI;
import marvel.api.CharactersRequest;

public class Main {

	public static void main(String[] args) {

		//conf
		CharactersAPI api = CharactersAPI
			.configureKeys("ac627b5a9da2dd5127e9583595c671f9", "c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1")
			.configureProxyHost("px-internet")
			.configureProxyPort("80")
			.init();
		
		CharactersRequest firstRequest = api.requestBuilder()
			.limite(101)
			.modifiedSince(LocalDate.of(2013, Month.JANUARY, 15))
			.build();
		
		List<marvel.model.Character> persos = firstRequest.get();
		System.out.println("nombre de characters retourn�s " + persos.size());

	}
}