package marvel.get;

import marvel.chiffrement.MD5;
import marvel.props.MyProperties;

/**
 * Construit une requête GET pour récupérer un ou plusieurs perso MARVEL
 * @author Adrien
 *
 */
public class RequestBuilder {

	public static String build(String[] args, long ts) {
		
		String url = "http://gateway.marvel.com/v1/public/characters";

		MyProperties props = new MyProperties();
		String apiKey = props.get("apikey");

		String publicKey = props.get("publicKey");

		String privateKey = props.get("privateKey");

		MD5 md5 = new MD5();
		String hash = md5.hash(ts, privateKey, publicKey);

		String query = "ts=" + ts + "&apikey=" + apiKey + "&hash=" + hash;

		if (args.length == 1) {
			/* ex "Wolverine" */
			String name = args[0];
			query += "&name=" + name;
		}

		return url + "?" + query;
	}

}
