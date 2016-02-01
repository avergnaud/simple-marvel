package marvel.get;

import junit.framework.TestCase;

public class SingleRequestTest extends TestCase {

	SingleRequest sr = new SingleRequest();
	
	public void testGet() {
		StringBuilder retour = sr.get("http://www.cat-amania.com/");
		assertTrue(retour != null && retour.toString().startsWith("<!DOCTYPE html PUBLIC"));
	}

}
