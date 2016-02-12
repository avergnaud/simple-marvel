package marvel.api;


/**
 * http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java
 */
public class APIConfiguration {
	
	private APIConfiguration() { }
	
	/** Instance unique initialisée au chargement de la classe */
	private static APIConfiguration INSTANCE = new APIConfiguration();

	/** Point d'accès */
	public static APIConfiguration getInstance() {
		return INSTANCE;
	}
	
	//Required
	private String rootUrl;
	private String publicKey;
	private String privateKey;
	//Optionnal
	private String proxyHost;
	private String proxyPort;

	/**
	 * Doit être appelé à l'init...
	 */
	public void init(String rootUrl, String publicKey, String privateKey, String proxyHost, String proxyPort) {
		this.rootUrl = rootUrl;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
	}

	public String getRootUrl() {
		return rootUrl;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public String getProxyHost() {
		return proxyHost;
	}
	public String getProxyPort() {
		return proxyPort;
	}

}
