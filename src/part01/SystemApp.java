package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * System App - Whole System for part 01
 * @author Connor Mallon
 *
 */
public class SystemApp {
	static String[] menuOptions = { "Select from Full List", "Select Tune by Artist", "Select Tune by Genre",
			"Add new Tune", "Display Top 10", "Switch Off", "Exit" };
	static String menuTitle = "QUB Music Department";

	static Menu startMenu = new Menu(menuTitle, menuOptions);

	static final int QUIT = menuOptions.length;
	static final int SWITCHOFF = menuOptions.length - 1;

	static MP3Player myMusic = new MP3Player();		// MP3 Player instance can be accessed anywhere
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int selectMenu = 0;
		addSomeTunes();

		int switchOnCount = 0; // Increments after each switchOn

		if (switchOn(switchOnCount)) { // User first switches on MP3

			while (selectMenu != QUIT) {
				selectMenu = startMenu.getUserChoice();

				if (selectMenu == SWITCHOFF) { // option: 6

					boolean status = switchOff(); // Call to Switch off method
					if (status) {
						selectMenu = QUIT;
					}
				} else if (selectMenu != QUIT) {
					processChoice(selectMenu);
				}
			}
			System.out.println("Goodbye!");
			sc.close();
		}
	}

	/**
	 * Allow a user to ensure a selection choice, where here it will call to the
	 * specific option that the user has chosen.
	 * 
	 * @param selectTune - integer that the user has input
	 */
	private static void processChoice(int selectTune) {
		switch (selectTune) {
		case 1:
			selectFromFullList();
			break;
		case 2:
			selectTuneByArtist();
			break;
		case 3:
			selectTuneByGenre();
			break;
		case 4:
			addNewTune();
			break;
		case 5:
			displayTopTen();
			break;
		case 6:
			switchOff();
			break;
		}
	}
	/**
	 * Display the total number of songs that have been stored into MP3 in a manner:
	 * "title " by "artist"
	 */
	private static void selectFromFullList() {
		String[] list = myMusic.getTuneInfo();
		int id;
		String title, artist;

		// Order the list of tunes in alphatic order - requires new id order as well
		for (int i = 0; i < list.length; i++) {
			String[] split = list[i].split(",");
			title = split[1].trim();
			artist = split[2].trim();
			String strID = split[0].trim();
			id = Integer.parseInt(strID);
			list[i] = title + ", " + artist + "," + id;
		}
		String[] orderedList = bubbleSort(list);
		int[] sortedID = sortIDs(orderedList);

		for (int i = 0; i < orderedList.length; i++) {
			String[] split = orderedList[i].split(",");
			title = split[0].trim();
			artist = split[1].trim();
			orderedList[i] = title + " by " + artist;
		}

		Menu tuneByFullList = new Menu("Select a Tune - Full List", orderedList);
		int selectTune = tuneByFullList.getUserChoice();
		int playTune = sortedID[selectTune - 1];

		System.out.println(myMusic.play(playTune));
	}

	/**
	 * Method allows to play the Titles through the selection choice of ID More
	 * efficient to call this method multiple times instead of sorting ID each time
	 * 
	 * @param list - input array, gets all IDS
	 * @return - int array of all IDs for Tunes
	 */
	private static int[] sortIDs(String[] list) {
		int[] newIDs = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			String[] split = list[i].split(",");
			int len = split.length - 1;
			String strID = split[len].trim(); // ID must be placed at end of String array
			int id = Integer.parseInt(strID);
			newIDs[i] = id;
		} // Then sorted in ascending index to retrieve specific tune
		return newIDs;
	}

	/**
	 * Removes any duplicate artist names that occur. it will add all the artist
	 * names only once, when deciding. It will check the current point artist is not
	 * equal to the next artist index Since it is sorted when arriving to this
	 * point. This is ok
	 * 
	 * @param array - the new artist array without duplicates
	 * @return - array of integers - storing the IDs only
	 */
	private static String[] removeDuplicates(String[] array) {
		String[] displayArray;
		int count = array.length;

		for (int i = 0; i < array.length; i++) {
			if (array[i].isBlank() && array[i].isEmpty()) {
				return null;
			}
		}
		// length of array will decrease if multiple duplicate encouters are met
		String[] tempArray = new String[array.length];

		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = array[i];
			// Add all Tune instances, including duplicates
			// Use this to now Store into a new Array and remove duplicates
		}
		int ind = 0;
		String[] removeDuplicates = new String[tempArray.length];
		for (int i = 0; i < tempArray.length - 1; i++) {
			if (tempArray[i].equals(tempArray[i + 1])) {
				count--;
			} else {
				removeDuplicates[ind] = tempArray[i];
				ind++;
			}
		}
		removeDuplicates[ind] = tempArray[tempArray.length - 1];
		// Add last value to the array - as the for loop does not do this

		displayArray = new String[count];

		int index = 0;
		for (int i = 0; i < displayArray.length; i++) {
			displayArray[index] = removeDuplicates[i];
			index++;
		}
		return displayArray;
	}

	/**
	 * Allow the user to select an artist from the given choices The method calls to
	 * both bubbleSort and removeDuplicates methods This method is called from
	 * selectTuneByArtist
	 * 
	 * @return all artist names within MP3 Player
	 */
	private static String selectArtist() {
		String[] fullList = myMusic.getTuneInfo();
		String artistName = ""; // Breaks out only the Artist name
		String[] nameArray = new String[fullList.length];
		for (int i = 0; i < fullList.length; i++) {
			artistName = fullList[i].split(",")[2].trim(); // using getTuneInfo()
			nameArray[i] = artistName;
		}
		String[] sortedArtists = bubbleSort(nameArray); // Sorts all artist names alphabetically
		String[] refinedArtists = removeDuplicates(sortedArtists);
		// then remove duplicates that appear

		Menu displayArtists = new Menu("Select an Artist", refinedArtists);
		int choice = displayArtists.getUserChoice();
		String chosenArtist = refinedArtists[choice - 1];
		// The users selected Artist
		return chosenArtist;
	}

	/**
	 * The User will have selected an Artist, to now display the total number of
	 * tunes that, that specific artist has made. Displaying the titles in
	 * alphabetic order The user can choose to play any Tune by that artist
	 */
	private static void selectTuneByArtist() {
		String chosenArtist = selectArtist();
		// Allows to pass chosen Artist Name through to local method
		String[] artistTitles = myMusic.getTuneInfo(chosenArtist);
		String strID, title;

		for (int i = 0; i < artistTitles.length; i++) {
			String[] split = artistTitles[i].split(",");
			strID = split[2].trim();
			int id = Integer.parseInt(strID);

			title = split[0].trim();
			artistTitles[i] = title + ", " + id;
			// ID will be used to play specific song
			// swap title and id position in order to sort by title
		}
		String[] sortTitles = bubbleSort(artistTitles);
		int[] sortedIDs = sortIDs(sortTitles);

		for (int i = 0; i < sortTitles.length; i++) {
			String[] split = sortTitles[i].split(",");
			title = split[0].trim();
			sortTitles[i] = title;
		}
		Menu displayArtist = new Menu("Select a Tune - By " + chosenArtist, sortTitles);
		int tuneChoice = displayArtist.getUserChoice();
		int playTune = sortedIDs[tuneChoice - 1];
		System.out.println(myMusic.play(playTune));
	}

	/**
	 * Method sorts an Array into ascending order ' Used within System App has it
	 * more effience I believed, as multiple methods I created require this
	 * bubbleSort method
	 * 
	 * @param array - input array needing to be sorted
	 * @return - sorted Array
	 */
	private static String[] bubbleSort(String[] array) {
		String[] ordered = new String[array.length]; // Assign to be of same length
		for (int i = 0; i < array.length; i++) {
			ordered[i] = array[i]; // Allow to use same elements, without affecting original array
		}
		for (int i = 0; i < ordered.length; i++) {
			for (int j = 0; j < ordered.length; j++) {
				if (ordered[i].charAt(0) >= 'A' && ordered[i].charAt(0) <= 'Z') {
					// Swap the values
					if (ordered[i].charAt(0) < ordered[j].charAt(0)) {
						String temp = ordered[i];
						ordered[i] = ordered[j];
						ordered[j] = temp;
					}
				}
			}
		}
		return ordered;
	}

	/**
	 * Display the top 10 number of songs that have been played by the user. Also
	 * allow the user to play a specific Tune with the corresponding menu choice.
	 */
	private static void displayTopTen() {
		String[] fullList = myMusic.getTuneInfo();

		String strPlayCount;
		String[] split;
		String title;
		String[] topTen = new String[fullList.length]; // having only a length of 10 elements
		int index = 0;

		/**
		 * First gets all songs by title and sorts accordingly with ID attached then
		 * formats once again to only display title + playCount
		 */
		for (int i = 0; i < fullList.length; i++) {
			split = fullList[i].split(",");
			title = split[1].trim();
			strPlayCount = split[5].trim(); // extract play count, then change to int
			int playCount = Integer.parseInt(strPlayCount);
			String strID = split[0].trim();
			topTen[index] = title + ", " + playCount + ", " + strID;
			index++;
		}
		String[] orderTopTen = orderPlayCount(topTen);
		String[] displayTopTen = new String[orderTopTen.length];

		for (int i = 0; i < orderTopTen.length; i++) {
			split = orderTopTen[i].split(", ");
			title = split[0].trim();
			String strCount = split[1].trim();
			int playCount = Integer.parseInt(strCount);
			displayTopTen[i] = title + ", " + playCount;
		}
		int[] sortIDs = sortIDs(orderTopTen);

		Menu topList = new Menu("Top 10 Songs played", displayTopTen);
		int choice = topList.getUserChoice();

		int playTune = sortIDs[choice - 1];
		System.out.println(myMusic.play(playTune));
	}

	/**
	 * Used in collaboration with displayTopTen method, where this sorts all
	 * corresponding titles in order by play Count instead of Title / artist
	 * 
	 * @param array - input Tunes array that needs to be sorted
	 * @return - the new sorted array, ordered by playcount
	 */
	private static String[] orderPlayCount(String[] array) {
		String[] order = new String[array.length];
		int[] playCount = new int[array.length];

		for (int i = 0; i < order.length; i++) {
			order[i] = array[i];
		}
		int index = 0;
		int numberOfSongs = 0;
		// Can only be range from 1 - 10, no higher

		for (int i = 0; i < order.length; i++) {
			String strPlayCount = order[i].split(",")[1].trim();
			int count = Integer.parseInt(strPlayCount);
			playCount[i] = count;
			if (numberOfSongs < 10) { // Does not go above 10
										// Counts number of songs within System
				numberOfSongs++;
			}
		}
		String[] topTen = new String[numberOfSongs];

		for (int i = 0; i < order.length; i++) {
			for (int j = 0; j < order.length; j++) {
				/*
				 * Swap both, the playCount and order Titles Will shove the higher play Count to
				 * the top, bringing along the title as well
				 */
				if (playCount[i] > (playCount[j])) {
					int swap = playCount[i];
					playCount[i] = playCount[j];
					playCount[j] = swap;
					String tmp = order[i];
					order[i] = order[j];
					order[j] = tmp;

					if (topTen.length < 10) {
						topTen[index] = order[i];
						index++;
					}
				}
			}
		}
		/*
		 * Final loop to insert all values into final top10 List Two seperate loops - to
		 * first check number of songs If total number of songs is equal or greater than10
		 */
		if (order.length >= 10) {
			for (int i = 0; i < 10; i++) {
				topTen[i] = order[i];
			}
		} else {
			for (int i = 0; i < topTen.length; i++) {
				topTen[i] = order[i];
			}
		}
		return topTen;
	}

	/**
	 * Allows the user to switch off the MP3 Player if they wish
	 * @return	- true statement to then close the MP3 Player
	 */
	private static boolean switchOff() {
		boolean status = false;

		boolean ok = false;
		System.out.println("Does the User want to switch the MP3Player off?");
		System.out.println("1: for Yes. \n2: for No.");
		do {
			try {
				int input = sc.nextInt();

				if (input == 1) {
					ok = true;
					status = myMusic.switchOff();

				} else if (input == 2) {
					ok = true;
					System.out.println("Returning back to main Menu");
				} else {
					System.out.println("Please input an appropriate number: ");
					System.out.println("1: for Yes. \n2: for No.");
				}
			} catch (InputMismatchException ex) {
				System.out.println("Please input a correct value");
				sc.next();
			}
		} while (!ok);

		return status;
	}

	/**
	 * Allows the user to switch the MP3 player on by input 1 to switch on
	 * else if input = 2, then switch off MP3 player
	 * @param switchNumber - checks the number of times the user has "switched off" 
	 * @param - displaying "MP3 is already off" it has already been switched off
	 * @return	status - if = switchOff() then will switch off MP3 player 
	 */
	private static boolean switchOn(int switchNumber) {

		boolean status = false;
		boolean ok = false;
		System.out.println("Does the user want to switch MP3Player on?");

		System.out.println("1: for Yes. \n2: for No.");

		do {
			try {
				int input = sc.nextInt();
				if (input == 1) {
					ok = true;
					status = myMusic.switchOn();

				} else if (input == 2) {
					ok = false;

					// Counter to first play "Switch off MP3" then will continue
					// to say "Already off" as long as count > 0
					if (switchNumber == 0) {
						status = myMusic.switchOff();
						switchNumber++;
					} else if (switchNumber > 0) {
						System.out.println("MP3 is already off.");
					}

				} else {

					System.out.println("Please input an appropriate number");
					System.out.println("1: for Yes. \n2: for No.\n>");

				}

			} catch (InputMismatchException ex) {
				System.out.println("Please input a correct int data-type.\n>");
				sc.next();
			}
		} while (!ok);

		return status;
	}
	
	 /**
	  * Allow the user to select a tune by a specific genre 
	  * First displaying all genres, where the user will chose from, 
	  * then retrieves all tunes that correspond to that specific genre
	  * For example: input genreType: Rock and Roll - returns all Rock and Roll Tunes
	  */
	private static void selectTuneByGenre() {
		String[] allGenres = { "Rock and Roll", "Easy Listening Pop", "Techno Dance", "Smooth Jazz", "Classical",
				"Unknown Genre" };

		Menu menuGenres = new Menu("Select a Tune - Genre ", allGenres);

		int inputNumber = menuGenres.getUserChoice();

		Genre inputGenreType; // Auto set to Other if no other appropriate value fits
		switch (inputNumber) {
		case 1:
			inputGenreType = Genre.ROCK;
			break;
		case 2:
			inputGenreType = Genre.POP;
			break;
		case 3:
			inputGenreType = Genre.DANCE;
			break;
		case 4:
			inputGenreType = Genre.JAZZ;
			break;
		case 5:
			inputGenreType = Genre.CLASSICAL;
			break;
		default:
			inputGenreType = Genre.OTHER;
			// Case 6 will be Genre.OTHER - and so will any other input type

		}
		String[] fullGenreList = myMusic.getTuneInfo(inputGenreType);

		String[] displayGenres = new String[fullGenreList.length];
		String title;
		String artist;
		for (int i = 0; i < fullGenreList.length; i++) {
			String[] split = fullGenreList[i].split(", ");
			String strID = split[0].trim();
			title = split[1].trim();
			artist = split[2].trim();
			displayGenres[i] = title + "," + artist + "," + strID;
		}
		String[] sortedGenreTitle = bubbleSort(displayGenres);
		int[] sortedIDs = sortIDs(sortedGenreTitle);

		for (int i = 0; i < sortedGenreTitle.length; i++) {

			String[] split = sortedGenreTitle[i].split(",");
			title = split[0].trim();
			artist = split[1].trim();
			sortedGenreTitle[i] = title + " by " + artist;
		}
		Menu genreList = new Menu("Select a Tune - Genre: " + inputGenreType, sortedGenreTitle);
		boolean ok = false;
		do {
			try {
				int choice = genreList.getUserChoice();

				int playTune = sortedIDs[choice - 1];

				System.out.println(myMusic.play(playTune));
				ok = true;

			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("No Tunes created with Genre Type: " + inputGenreType);
				System.out.println(); // Add extra spacing
				break;
			}
		} while (!ok);

	}

	/**
	 * User can add their own specific tunes - by typing in a title, artist, duration and genre
	 * It wil then add the new tune to the list
	 * Try catch employed to ensure an invalid data input is rejected
	 */
	private static void addNewTune() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean ok = false;
		String[] allGenres = { "Rock and Roll", "Easy Listening Pop", "Techno Dance", "Smooth Jazz", "Classical",
				"Unknown Genre" };

		System.out.println("Enter title of Song: ");

		String title = sc.nextLine();
		System.out.println("Enter the Artist of the Song: ");
		String artist = sc.nextLine(); // will reset the scanner input

		System.out.println("Input a Song duration: ");
		do {
			try {
				int duration = sc.nextInt(); // converts String to int input

				Menu chooseGenre = new Menu("Choose a Song Genre from the List ", allGenres);
				int choice = chooseGenre.getUserChoice();
				ok = true;

				Genre genre;
				switch (choice) {
				case 1:
					genre = Genre.ROCK;
					break;
				case 2:
					genre = Genre.POP;
					break;
				case 3:
					genre = Genre.DANCE;
					break;
				case 4:
					genre = Genre.JAZZ;
					break;
				case 5:
					genre = Genre.CLASSICAL;
					break;
				default:
					genre = Genre.OTHER;
				}
				String chosenGenre = allGenres[choice - 1];

				System.out.println("Genre Chosen: " + chosenGenre);

				myMusic.addtune(title, artist, duration, genre);

				System.out.println("Added: " + title + " to the list\n");

			} catch (InputMismatchException ex) {
				System.out.println("Error input!\nPlease input an appropriate int data-type\n>");
				sc.nextLine();
			}
		} while (!ok);

	}

	/**
	 * Some hard coded tunes added to the system - can be accessed through any part of the program
	 * The user can view the Title, Artist and Genre of tune
	 */
	private static void addSomeTunes() {
		myMusic.addtune("My Way", "Frank Sinatra", 130, Genre.JAZZ);
		myMusic.addtune("Fly me to the Moon", "Frank Sinatra", 130, Genre.JAZZ);
		myMusic.addtune("Help", "The Beatles", 300, Genre.POP);
		myMusic.addtune("Unholy War", "Jacob Banks", 300, Genre.POP);
		myMusic.addtune("Devil that I know", "Jacob Banks", 350, Genre.JAZZ);
		myMusic.addtune("Bones", "Low Roar", 195, Genre.ROCK);
		myMusic.addtune("Slow down", "Low Roar", 195, Genre.ROCK);
		myMusic.addtune("Für Elise", "Beethovan", 250, Genre.CLASSICAL);
		myMusic.addtune("Are You Ready for Love", "Elton John", 210, Genre.POP);
		myMusic.addtune("Clouds", "NF", 450, Genre.OTHER);
		myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);
		myMusic.addtune("She Said", "Sundra Karma", 212, Genre.DANCE);
		myMusic.addtune("Olympia", "Sundra Karma", 300, Genre.DANCE);
		myMusic.addtune("Redbone", "Childish Gambino", 300, Genre.JAZZ);
		myMusic.addtune("This is America", "Childish Gambino", 400, Genre.JAZZ);
		myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);
		myMusic.addtune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		myMusic.addtune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		/*
		 * Example to show the System will not add Tunes which either have no valid
		 * input artist or title, nor will it add a tune which has already been added
		 * before
		 */
		 myMusic.addtune("", "", 750, Genre.ROCK);
	}
}
