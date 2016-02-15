package marvel.api;

/**
 * Builder pour la configuration
 */
public class CharactersAPI {
	
	//Required
	public final String publicKey;
	public final String privateKey;
	//Optionnal
	public final String rootUrl;
	public final int limiteMarvel;
	public final String proxyHost;
	public final String proxyPort;

	public static class Builder {
		//Required
		private String publicKey;
		private String privateKey;
		//Optionnal
		private String rootUrl = "http://gateway.marvel.com/v1/public/characters";
		private int limiteMarvel = 100;
		private String proxyHost;
		private String proxyPort;
		
		public Builder(String publicKey, String privateKey) {
			this.publicKey = publicKey;
			this.privateKey = privateKey;
		}
		public Builder rootUlr(String val) {
			rootUrl = val;
			return this;
		}
		public Builder limiteMarvel(int val) {
			limiteMarvel = val;
			return this;
		}
		public Builder configureProxyHost(String val) {
			proxyHost = val;
			return this;
		}
		public Builder configureProxyPort(String val) {
			proxyPort = val;
			return this;
		}
		public CharactersAPI init() {
			return new CharactersAPI(this);
		}
	}
	private CharactersAPI(Builder builder) {
		publicKey = builder.publicKey;
		privateKey = builder.privateKey;
		rootUrl = builder.rootUrl;
		limiteMarvel = builder.limiteMarvel;
		proxyHost = builder.proxyHost;
		proxyPort = builder.proxyPort;
	}
	public static Builder configureKeys(String publicKey, String privateKey) {
		return new Builder(publicKey, privateKey);
	}
	public CharactersRequest.Builder requestBuilder() {
		return new CharactersRequest.Builder(this);
	}
}
