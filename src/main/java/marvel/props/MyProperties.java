package marvel.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java
 */
public class MyProperties {
	
	/* Instance unique pré-initialisée */
	private static MyProperties INSTANCE = new MyProperties();
	
	private Properties props = null;
	
	private MyProperties() {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream("/config.properties");
			props = new Properties();
			props.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}
	
	/** Point d'accès pour l'instance unique du singleton */
	public static MyProperties getInstance() {
		return INSTANCE;
	}
}