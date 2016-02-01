package marvel.get;

import junit.framework.TestCase;

public class RequestBuilderTest extends TestCase {

	public void testBuild() {

		long ts = 1454345050;
		String retourAttendu = "http://gateway.marvel.com/v1/public/characters?ts=1454345050&apikey=ac627b5a9da2dd5127e9583595c671f9&hash=8dc4bc776b18ccd6b0799b0445f03144&name=Wolverine";

		String out = RequestBuilder.build(new String[] { "Wolverine" }, ts);
		assertTrue(retourAttendu.equals(out));
	}

}
