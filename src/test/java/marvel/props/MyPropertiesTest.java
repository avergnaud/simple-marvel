package marvel.props;

import junit.framework.TestCase;

public class MyPropertiesTest extends TestCase {

	MyProperties props = new MyProperties();
	
	public void testGet() {
		String apiKeyValue = props.get("apikey");
		assertTrue("ac627b5a9da2dd5127e9583595c671f9".equals(apiKeyValue));
	}

}
