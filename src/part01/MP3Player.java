package part01;

import java.util.ArrayList;

/**
 * Integration section
 * Retrieveing specific Tunes required for SystemApp
 * @author Connor Mallon
 *
 */
public class MP3Player implements iPlayer { // Composition of Tune class

	private ArrayList<Tune> soundData; // Store array of full item list

	public MP3Player() {
		soundData = new ArrayList<Tune>(); // Instantiation of arrayList
	}

	// Use to retrieve the full instance of each tune details
	public String[] getTuneInfo() {
		String[] songDetails = new String[soundData.size()];
		for (int i = 0; i < soundData.size(); i++) {
			songDetails[i] = soundData.get(i).toString();
		}
		return songDetails;
	}

	// Returns tune instances of a specific Artist
	public String[] getTuneInfo(String artist) {
		// int artistcount = 0;
		String[] tuneTitles;
		int count = 0;

		for (int i = 0; i < soundData.size(); i++) {
			if (artist.equals(soundData.get(i).getArtist())) {
				count++; // gets number of songs for the artist
			}
		}
		tuneTitles = new String[count];
		int tuneTitlesIndex = 0;

		for (int i = 0; i < soundData.size(); i++) {
			if (artist.equalsIgnoreCase(soundData.get(i).getArtist())) {
				tuneTitles[tuneTitlesIndex] = soundData.get(i).getTitle() + "," + soundData.get(i).getArtist() + ","
						+ soundData.get(i).getID();
				tuneTitlesIndex++;
			}
		}
		return tuneTitles;
	}

	/*
	 * Selects a tune by a specific Genre
	 * Returning all corresponding tunes by that chosen Genre
	 */
	public String[] getTuneInfo(Genre gen) {
		String[] tuneGenre;
		// For loop to get the length of elements to be stored within the array
		int count = 0;
		int indexPos = 0;

		for (int i = 0; i < soundData.size(); i++) {
			if (gen.equals(soundData.get(i).getGenre())) {
				count++; // create new length for array
							// Gets number of songs for artist
			}
		}
		tuneGenre = new String[count]; // dynamically gets length of array
		String fullSong; // contains full instance of song - will need to break out

		for (int j = 0; j < soundData.size(); j++) {
			if (gen.equals(soundData.get(j).getGenre())) {
				// System.out.println("Genres found at position: " + j);
				fullSong = soundData.get(j).toString();
				String[] split = fullSong.split(",");
				String strID = split[0].trim();
				int id = Integer.parseInt(strID);
				String artist = split[2].trim();
				String genre = split[4].trim();	//  Stores Genre of Title 
				String genreTitle = split[1].trim(); // Now extracts the Genre Song title in String format
				tuneGenre[indexPos] = id + ", " + genreTitle + ", " + artist + "," + genre;
				// gets each genre name and puts into array
				indexPos++;
			}
		}
		return tuneGenre;
	}

	// Play a specific tune chosen from given selection options
	public String play(int tuneID) {
		String res = "";
		res = soundData.get(tuneID - 1).play(); // Calls to Tune class
		return res;
	}

	/**
	 * Allow to add hard coded tunes into "addSomeTunes"
	 * But also can be called in order for the user to add their own personal Tunes
	 */
	public boolean addtune(String title, String artist, int duration, Genre gen) {

		if (title.isBlank() || artist.isBlank() || duration == 0) {
			return false;
		}
		for (int i = 0; i < soundData.size(); i++) {
			if (artist.equals(soundData.get(i).getArtist())) {
				/* if both the input Artist and title are similar to another instance stored
				 * within the system.
				 * Then do not add the instance to the Table */
				if (title.equals(soundData.get(i).getTitle())) {
					return false;
				}
			}
		}
		if (!title.isBlank() || !artist.isBlank() || duration != 0) {
			Tune song = new Tune(title, artist, duration, gen); // Compare the input paramaters
			// to check if a tune has already been
			// initalised
			soundData.add(song); // Adds Tune to array List
			return true;
		}
		// soundData.add(new Tune (title,artist, duration, gen ));
		return false;
	}
	// Display a prompt to the user saying MP3 has switched Off
	public boolean switchOff() {
		boolean status = true; // System will be switched Off
		System.out.println("Switching Off MP3Player.");

		return status;
	}

	// Display a prompt to the user saying MP3 has switched on
	public boolean switchOn() {
		boolean status = true; // System will be switched on
		System.out.println("Switching On MP3Player.");

		return status;
	}

}
