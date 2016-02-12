package marvel.get;

import junit.framework.TestCase;
import marvel.api.APIConfiguration;

public class SimpleHTTPRequestTest extends TestCase {

	protected void setUp() throws Exception {
		APIConfiguration.getInstance().init(
				"http://gateway.marvel.com/v1/public/characters", 
				"ac627b5a9da2dd5127e9583595c671f9", 
				"c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1", 
				"px-internet", 
				"80");
	}
	
	public void testGet() {
		SimpleHTTPRequest r = new SimpleHTTPRequest();
		StringBuilder retour = r.get("http://www.cat-amania.com/");
		assertTrue(retour != null && retour.toString().startsWith("<!DOCTYPE html PUBLIC"));
	}

}
