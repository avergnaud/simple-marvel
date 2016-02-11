package marvel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import marvel.get.ResourcesURI;
import marvel.get.SingleRequest;
import marvel.model.CharacterDataWrapper;
import marvel.write.Writer;

import com.google.gson.Gson;

/**
 * R�cup�re les 20 premiers perso MARVEL
 * @author Adrien
 *
 */
public class Main {

	public static void main(String[] args) {
	
		//Construction de l'URL � appeler
		LocalDateTime now = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long ts = now.atZone(zoneId).toEpochSecond();
		
		String requestUrl = ResourcesURI.characters(ts);
		
		//Appel HTTP
		StringBuilder sb = new SingleRequest().get(requestUrl);
		
		//Mapping json vers java
		String json = sb.toString();
		Gson gson = new Gson();
		CharacterDataWrapper resultatRequeteObjet = gson.fromJson(json, CharacterDataWrapper.class);
		
		//R�cup�ration et �criture
		List<marvel.model.Character> maListe = resultatRequeteObjet.getData().getResults();
		System.out.println("nombre de characters retourn�s " + maListe.size());
		Writer.write(sb,"out.json");
	}
}