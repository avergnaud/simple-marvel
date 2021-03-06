package marvel.model;

import java.util.ArrayList;
import java.util.List;

public class StoryList {

	private int available;
	private int returned;
	private String collectionURI;
	private List<StorySummary> items = new ArrayList<StorySummary>();

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

	public List<StorySummary> getItems() {
		return items;
	}

	public void setItems(List<StorySummary> items) {
		this.items = items;
	}

}
