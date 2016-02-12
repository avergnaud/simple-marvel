package marvel.get;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import marvel.chiffrement.MD5;
import marvel.props.MyProperties;

/**
 * Construit une URL de requ�te GET pour r�cup�rer des perso MARVEL
 */
public class CharactersURI {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

	public static String getRequest(int offset, int limit, String name, String nameStartsWith, LocalDate modifiedSince) {

		//2015-01-12 (12 janvier 2015)
		
		LocalDateTime now = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long ts = now.atZone(zoneId).toEpochSecond();
		
		MyProperties props = MyProperties.getInstance();
		
		String rootUrl = props.get("rootUrl");
		String publicKey = props.get("publicKey");
		String privateKey = props.get("privateKey");

		MD5 md5 = new MD5();
		String hash = md5.hash(ts, privateKey, publicKey);

		String query = "ts=" + ts + "&apikey=" + publicKey + "&hash=" + hash;
		
		if(offset != 0) {
			query += "&offset=" + offset;
		}
		if(limit != 0) {
			query += "&limit=" + limit;
		}
		if(name != null) {
			query += "&name=" + name;
		}
		if(nameStartsWith != null) {
			query += "&nameStartsWith=" + nameStartsWith;
		}
		if(modifiedSince != null) {
			String dateString = dtf.format(modifiedSince);
			query += "&modifiedSince=" + dateString;
		}

		return rootUrl + "?" + query;
	}
}
