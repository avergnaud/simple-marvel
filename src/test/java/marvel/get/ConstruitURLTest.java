package marvel.get;

import marvel.api.APIConfiguration;
import junit.framework.TestCase;

public class ConstruitURLTest extends TestCase {

	protected void setUp() throws Exception {
		APIConfiguration.getInstance().init(
				"http://gateway.marvel.com/v1/public/characters", 
				"ac627b5a9da2dd5127e9583595c671f9", 
				"c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1", 
				"px-internet", 
				"80");
	}
	
	public void testAvecParams() {
		String url = ConstruitURL.avecParams(0, 1, null, null, null);
		
		assertTrue(url.contains("apikey=ac627b5a9da2dd5127e9583595c671"));
	}

}
