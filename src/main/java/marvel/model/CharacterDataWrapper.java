package marvel.model;

public class CharacterDataWrapper {

	private int code;

	private String status;

	private String copyright;

	private String attributionText;
	private String attributionHTML;
	private CharacterDataContainer data;
	private String etag;

	public CharacterDataWrapper() {
		super();
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getAttributionText() {
		return attributionText;
	}

	public CharacterDataContainer getData() {
		return data;
	}

	public String getAttributionHTML() {
		return attributionHTML;
	}

	public String getEtag() {
		return etag;
	}

}
