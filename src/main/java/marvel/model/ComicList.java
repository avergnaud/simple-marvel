package marvel.model;

import java.util.ArrayList;
import java.util.List;

public class ComicList {

	private int available;
	private int returned;
	private String collectionURI;
	private List<ComicSummary> items = new ArrayList<ComicSummary>();

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getReturned() {
		return returned;
	}

	public void setReturned(int returned) {
		this.returned = returned;
	}

	public String getCollectionURI() {
		return collectionURI;
	}

	public void setCollectionURI(String collectionURI) {
		this.collectionURI = collectionURI;
	}

	public List<ComicSummary> getItems() {
		return items;
	}

	public void setItems(List<ComicSummary> items) {
		this.items = items;
	}

}
