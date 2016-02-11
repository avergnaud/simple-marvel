package marvel.props;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

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