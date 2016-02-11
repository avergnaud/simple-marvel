package marvel.model;

import java.util.ArrayList;
import java.util.List;

public class StoryList {

	private int available;
	private int returned;
	private String collectionURI;
	private List<StorySummary> items = new ArrayList<StorySummary>();
}
