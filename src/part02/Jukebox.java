package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import part01.Genre;
import part01.MP3Player;
/**
 * System App - Whole System for part 02
 * Additional functionality of insert Coin
 * and extended switch on, switch Off
 * @author Connor Mallon
 *
 */
public class Jukebox extends MP3Player {

	private int credits;
	private int costPerCredit;
	/*
	 * Create additonal instance variables to allow the user to view total number of
	 * coins that they have inserted into the Jukebox
	 */
	private int totalCoins;

	String[] fullList = getTuneInfo(); // Gets all Songs from MP3 player

	// Public Constructor, setting Credits + cost percredit to 0
	public Jukebox() {
		// Values are initally set to 0 upon instantiaton
		setCredits(0);
		setCostPerCredit(0);
	}

	// Should not be able to be modified by the user - only to insert coins
	// Can only be modified if credits = 0
	public void setCostPerCredit(int costPerCredit) {
		if (credits == 0) {
			this.costPerCredit = costPerCredit;
		}
	}

	// Use this only within the Jukebox class -> cannot be accessed publicly
	private void setCredits(int credits) {
		this.credits = credits;
	}

	@Override // Method is overridden from  MP3 
	/*
	 * Instead calls to here, checking if user has credits or
	 * if cost per credit = 0
	 */
	public String play(int tuneID) {
		String res = "";
		if (credits > 0) {
			credits--;
			res = super.play(tuneID) + "\n";

		} else if (costPerCredit == 0) { // Cost per credit has not yet been set
			res += "Free play Song.\n"; // Then it is a free play song if costPerCredit = 0
			res += super.play(tuneID) + "\n";

		} else if (credits == 0) {
			res += "Error. Not enough credits to play song.\nPlease buy more credits.\n";
		}
		return res;
	}

	/**
	 * The user will switch off Jukebox, storing the state of songs to the csv file
	 * Storing ID, Title, Artist, duration, Genre, PlayCount 
	 * in that order*/
	@Override
	public boolean switchOff() {

		// Add a functionality of the time of which the jukeBox is switched off and on
		String[] storeTitles = getTuneInfo();
		boolean status = true;
		String store = "saveTunes.csv";
		String creditsCSV = "saveCredits.csv";
		try {
			PrintWriter myPw = new PrintWriter(store);
			PrintWriter myCredits = new PrintWriter(creditsCSV);

			myPw.println("ID, Title, Artist, Duration, Genre, PlayCount");
			// Tune instances will be ordered by ID within the csv file
			for (String i : storeTitles) {
				myPw.println(i);
			}
			myCredits.println("Credits, CostPerCredit, TotalCoins");
			myCredits.print(credits + "," + costPerCredit + "," + totalCoins);

			myCredits.close(); // Close both scanners
			myPw.close(); // Tunes + Credits Scanner
			System.out.println("\nTune details have been stored to file path:\n" + store);
		} catch (FileNotFoundException ex) {
			System.out.println("File could not be found.");
			ex.printStackTrace();
		}
		return status;
	}

	
	/**
	 * Restores the number of tunes back to the System
	 * Adding all of the tunes back from the CSV file 
	 * Else if the file is not yet there, first switch on, 
	 * then switch off again to store Tunes to a csv
	 * then switch on again to display "Restored State of Songs"
	 */
	@Override
	public boolean switchOn() {
		boolean status = true;
		boolean hasHeader = true;
		String store = "saveTunes.csv";
		String creditsCSV = "saveCredits.csv";
		String title, artist;

		try {

			File newFile = new File(store);
			Scanner sc = new Scanner(newFile);

			File saveCredits = new File(creditsCSV);
			Scanner credits = new Scanner(saveCredits);

			System.out.println("Restored State of songs.");
			Genre restoreGenre = Genre.OTHER; // Auto set to other

			String jukeBox = "";
			if (hasHeader) {
				sc.nextLine();
				credits.nextLine(); // Skip the header
				jukeBox = credits.nextLine(); // Contains credits + cost per credit
			}

			while (sc.hasNextLine()) {
				String fullSongs = sc.nextLine();

				// Reads the number of songs within the csv file
				String[] split = fullSongs.split(",");
				title = split[1].trim();
				artist = split[2].trim();
				String strDuration = split[3].trim();
				int duration = Integer.parseInt(strDuration);
				String strGenre = split[4].trim();
				String strPlayCount = split[5].trim();

				@SuppressWarnings("unused")
				int getPlayCount = Integer.parseInt(strPlayCount); // Retrieved play count from CSV
				/**
				 * Could not find a way to set each instance for the playCount I have managed to
				 * extract it and it matches to the specific tune instance Upon being played,
				 * however unable to set the new play count after switching off and back on
				 */
				if (strGenre.equals("Rock and Roll"))
					restoreGenre = Genre.ROCK;
				else if (strGenre.equals("Easy Listening Pop"))
					restoreGenre = Genre.POP;
				else if (strGenre.equals("Techno Dance"))
					restoreGenre = Genre.DANCE;
				else if (strGenre.equals("Unknown Genre"))
					restoreGenre = Genre.OTHER;
				else if (strGenre.equals("Smooth Jazz"))
					restoreGenre = Genre.JAZZ;
				else if (strGenre.equals("Classical"))
					restoreGenre = Genre.CLASSICAL;

				addtune(title, artist, duration, restoreGenre); // Inheritance from MP3 player
			}
			String strCredits = jukeBox.split(",")[0].trim();
			String strCostPerCredit = jukeBox.split(",")[1].trim();
			String strTotalCoins = jukeBox.split(",")[2].trim();

			int getCredits = Integer.parseInt(strCredits);
			int getCostPerCredit = Integer.parseInt(strCostPerCredit);
			int addedCoins = Integer.parseInt(strTotalCoins);

			setCostPerCredit(getCostPerCredit);
			setCredits(getCredits);

			totalCoins += addedCoins; // total amount of coins will also be saved to the CSV
			// Adding the new coins back into the total amount of Coins instance variable 

			credits.close();
			sc.close();

		} catch (FileNotFoundException ex) {
			System.out.println("File could not be found.");
			// ex.printStackTrace();

		}

		return status;
	}

	public int getCostPerCredit() {
		return this.costPerCredit;
	}

	public int getCredits() {
		return this.credits;
	}

	// Returns the total number of coins that the user has input into the jukebox
	public int getTotalCoins() {
		return this.totalCoins;
	}

	/**
	 * Allow the user to insert a coin to the jukebox
	 * In exchange for credits 
	 * @param coin	- insert value of coin into jukebox 
	 */
	public void insertCoin(int coin) {

		if (coin == 10 || coin == 20 || coin == 50) {
			totalCoins += coin;
			String format = String.format("%d", coin);
			if (totalCoins < costPerCredit) {

				System.out.println("Adding: " + format + "p to the Jukebox.");

			} else if (totalCoins >= costPerCredit) { // Use set perCredit

				while (totalCoins >= costPerCredit) {
					credits++;
					totalCoins -= costPerCredit;
				}
				System.out.println("Credits: " + credits);
				printCanPlay();
			}
		} else if (coin == 100 || coin == 200) {
			totalCoins += coin;

			if (totalCoins < costPerCredit) {
				if (coin == 100) { // Else £2.00 will be added to the Jukebox
					// Can only happen if costPerCredit = £2.00
					// As nothing else is bigger than £2.00.
					System.out.println("Adding: £1.00 to the Jukebox.");
				}
			} else if (totalCoins >= costPerCredit) {

				while (totalCoins >= costPerCredit) {
					credits++;
					totalCoins -= costPerCredit;
				}
				System.out.println("Credits: " + credits);
				printCanPlay();

			} else {
				System.out.println("Coin is rejected. Please try again.");
			}
		}
	}

	// Simple print statement to show that a Tune can now be played
	private void printCanPlay() {
		System.out.println("Jukebox can now play Tunes.");
	}

}
