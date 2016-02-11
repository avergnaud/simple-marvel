package marvel.chiffrement;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * https://fr.wikipedia.org/wiki/MD5
 * requis pour appeler les services MARVEL
 */
public class MD5 {

	public String hash(long ts, String privateKey, String publicKey) {
		String toHash = "" + ts + privateKey + publicKey;
		String hash = null;
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] hashByte = md.digest(toHash.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hashByte.length; i++) {
				if ((0xff & hashByte[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hashByte[i])));
				}
				else {
					hexString.append(Integer.toHexString(0xFF & hashByte[i]));
				}
			}
			hash = hexString.toString();
		 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hash;
	}
}