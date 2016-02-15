package marvel.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import marvel.api.CharactersAPI;
import marvel.chiffrement.MD5;

/**
 * Construit une URL de requête GET pour récupérer des perso MARVEL
 */
public class ConstruitURL {

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String avecLesParametres(CharactersAPI api, int offset,
			int limit, String name, String nameStartsWith,
			LocalDate modifiedSince) {

		String rootUrl = api.rootUrl;
		String publicKey = api.publicKey;
		String privateKey = api.privateKey;

		LocalDateTime now = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long ts = now.atZone(zoneId).toEpochSecond();

		MD5 md5 = new MD5();
		String hash = md5.hash(ts, privateKey, publicKey);

		String query = "ts=" + ts + "&apikey=" + publicKey + "&hash=" + hash;

		if (offset != 0) {
			query += "&offset=" + offset;
		}
		if (limit != 0) {
			query += "&limit=" + limit;
		}
		if (name != null) {
			query += "&name=" + name;
		}
		if (nameStartsWith != null) {
			query += "&nameStartsWith=" + nameStartsWith;
		}
		if (modifiedSince != null) {
			String dateString = dtf.format(modifiedSince);
			query += "&modifiedSince=" + dateString;
		}

		return rootUrl + "?" + query;
	}
}
