package marvel.props;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TODO http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java
 * @author Adrien
 *
 */
public class MyProperties {
	
	Properties props = null;
	
	public MyProperties() {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream("/config.properties");
			props = new Properties();
			props.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}
}