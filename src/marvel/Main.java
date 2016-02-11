package marvel;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;

import marvel.props.MyProperties;
import marvel.chiffrement.MD5;
import marvel.get.SingleRequest;
import marvel.write.Writer;

/**
http://developer.marvel.com/documentation/authorization
http://developer.marvel.com/docs#!/public/getCreatorCollection_get_0
*/
public class Main {

	public static void main(String[] args) {
	
		if(args.length != 1) {
			System.out.println("input one name ex: Wolverine");
			return;
		}
			
		String url = "http://gateway.marvel.com/v1/public/characters";
		String charset = StandardCharsets.UTF_8.name();
		
		String name = args[0];/*ex "Wolverine"*/
		
		MyProperties props = new MyProperties();
		String apiKey = props.get("apikey");
		
		String publicKey = props.get("publicKey");
		
		LocalDateTime now = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long ts = now.atZone(zoneId).toEpochSecond();
		
		String privateKey = props.get("privateKey");
		
		MD5 md5 = new MD5();
		String hash = md5.hash(ts, privateKey, publicKey);
		
		String query = "ts=" + ts +	"&apikey=" + apiKey + "&hash=" + hash +	"&name=" + name;
		
		StringBuilder sb = new SingleRequest().get(url + "?" + query);
		
		Writer.write(sb,"out.json");
	}
}