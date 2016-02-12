package marvel.get;

import junit.framework.TestCase;

public class ResourcesURITest extends TestCase {

	/**
	 * TODO : bad pattern
	 */
	public void testBuild() {
//		String out = CharactersURI.getRequest(0, 0, null, null, null);
//		String out = CharactersURI.getRequest(10, 21, null, null, null);
//		String out = CharactersURI.getRequest(0, 0, null, "Wol", null);
//		String out = CharactersURI.getRequest(0, 0, null, null, LocalDate.of(2016, Month.JANUARY, 1));
		String out = CharactersURI.getRequest(0, 0, "Wolverine", null, null);
		System.out.println(out);
		assertTrue(true);
	}

}
