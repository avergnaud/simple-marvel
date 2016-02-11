package marvel.get;

import marvel.chiffrement.MD5;
import marvel.props.MyProperties;

/**
 * Construit une URL de requête GET pour récupérer des perso MARVEL
 * @author Adrien
 *
 */
public class ResourcesURI {

	public static String characters(long ts) {

		MyProperties props = MyProperties.getInstance();
		String apiKey = props.get("apikey");
		
		String rootUrl = props.get("rootUrl");

		String publicKey = props.get("publicKey");

		String privateKey = props.get("privateKey");

		MD5 md5 = new MD5();
		String hash = md5.hash(ts, privateKey, publicKey);

		String query = "ts=" + ts + "&apikey=" + apiKey + "&hash=" + hash;

		return rootUrl + "?" + query;
	}

}
