package part01;
/**
 * Creating new Tune instances where the
 * System or User can add new tunes
 * @author Connor Mallon
 *
 */
public class Tune {
	private int id;
	private static int nextID = 1;
	private String title;
	private String artist;
	private int duration; // seconds
	private int playCount;
	private Genre style;

	public Tune(String title, String artist, int duration, Genre style) {
		if (!title.isBlank() || !artist.isBlank() || duration != 0 || style != null) {
			this.id = useNextID();
			setTitle(title);
			setArtist(artist);
			setDuration(duration);
			setGenre(style);
			
		} else {
			this.id = 0;
			setTitle(null);
			setArtist(null);
			setDuration(0);
			setGenre(null);
		}
	}

	private static int useNextID() {
		return nextID++;
	}

	public String toString() {
		String res = "";
		res += getID() + ", ";
		res += getTitle() + ", ";
		res += getArtist() + ", ";
		res += getDuration() + ", ";
		res += getGenre() + ", ";
		res += getPlayCount();
		// returns all details of tune: id, duration, style added
		return res;
	}
	public String play() {
		increasePlayCount(); // corresponds to given Tune instance
		String play = "Now playing ... " + title + ", ";
		play += "by " + artist;

		return play;
	}

	// Create Getters and setters as appropriate
	// Will set each id for each specific Tune instance
	private void setTitle(String title) {
		this.title = title;
	}

	private void setArtist(String artist) {
		this.artist = artist;
	}

	private void setDuration(int duration) {
		this.duration = duration;
	}

	private void increasePlayCount() {
		playCount++;
	}

	private void setGenre(Genre gen) {
		this.style = gen;
	}

	public int getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getArtist() {
		return this.artist;
	}

	public int getDuration() {
		return this.duration;
	}

	public int getPlayCount() {
		return this.playCount;
	}

	public Genre getGenre() {
		return this.style;
	}

}
