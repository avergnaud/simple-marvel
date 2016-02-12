package marvel.get;

import junit.framework.TestCase;

public class SingleRequestTest extends TestCase {

	SimpleHTTPRequest sr = new SimpleHTTPRequest();
	
	public void testGet() {
		StringBuilder retour = sr.get("http://www.cat-amania.com/");
		assertTrue(retour != null && retour.toString().startsWith("<!DOCTYPE html PUBLIC"));
	}

}
