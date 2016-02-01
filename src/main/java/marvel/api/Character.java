package marvel.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Character {

	private int id;
	private String name;
	private String description;
	private Date modified;
	private String resourceURI;
	private List<Url> urls = new ArrayList<Url>();
	private Image thumbnail;
	private ComicList comics;
	private StoryList stories;
	private EvenList events;
	private SeriesList series;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

}
