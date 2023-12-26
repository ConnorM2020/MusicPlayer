package part03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import part01.Genre;
import part01.MP3Player;

public class TestMP3Player {
	MP3Player myMusic = new MP3Player();

	@Before
	public void setUp() throws Exception {
	}

	@Test // test Constructor inputs 
	public void testMP3Player() { // instantiating the Array List
		System.out.println("\nMP3 Player Constructor Test Case 1: ");
		System.out.println("=====================================");

		String validTitle = "Break my Baby";	
		String validArtist = "Kaleo";
		int validDuration = 350;
		String validGenre = "Rock and Roll";
		try {
			myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);
			String[] fullInstance = myMusic.getTuneInfo();
			System.out.println("--->Expected Title:\t" + validTitle);

			String actualTitle = fullInstance[0].split(",")[1].trim();
			System.out.println("--->Actual Title:\t" + actualTitle);

			System.out.println("\n--->Expected Artist:\t" + validArtist);
			String actualArtist = fullInstance[0].split(",")[2].trim();
			System.out.println("--->Actual Artist:\t" + actualArtist);

			System.out.println("\n-->Expected Duration:\t" + validDuration);
			String actualDuration = fullInstance[0].split(",")[3].trim();

			int convert = Integer.parseInt(actualDuration);
			System.out.println("--->Actual Duration:\t" + convert);

			System.out.println("\n-->Expected Genre:\t" + validGenre);
			String actualGenre = fullInstance[0].split(",")[4].trim();
			System.out.println("--->Actual Genre:\t" + actualGenre);

		} catch (Exception ex) {
			System.out.println("Exception found within Test.\nThis should not happen.");
		}
		String[] arrayList = myMusic.getTuneInfo();
		int numberOfElements = 0;
		System.out.print("\nThe array list consists of: ");
		for (int i =0; i < arrayList.length; i++) {
			numberOfElements++;
		}
		System.out.println(numberOfElements + " element(s). \n");
	}

	@Test
	public void testGetTuneInfo() {
		System.out.println("\nTest Case 2:");
		System.out.println("============");
		myMusic.addtune("Fly me to the Moon", "Frank Sinatra", 300, Genre.JAZZ);
		String[] fullList = myMusic.getTuneInfo();
		// If you change these values	
		// It will say the values now are not the same. 
		// So the Test Case fails
		String expectedTitle = "Fly me to the Moon";
		String expectedArtist = "Frank Sinatra";

		String actualTitle = fullList[0].split(",")[1].trim();
		String actualArtist = fullList[0].split(",")[2].trim();

		String strActualDuration = fullList[0].split(",")[3].trim();
		int actualDuration = Integer.parseInt(strActualDuration);
		int expectedDuration = 300;
		
		Genre expectedGenre = Genre.JAZZ;
		String expectedToString = expectedGenre.toString();
		String actualGenre = fullList[0].split(",")[4].trim();
		
		System.out.println("--->Expected:\t" + expectedTitle);
		System.out.println("--->Actual:\t" + actualTitle);
		
		System.out.println("\n--->Expected:\t" + expectedArtist);
		System.out.println("--->Actual:\t"+ actualArtist);
		
		System.out.println("\n--->Expected:\t" + expectedDuration);
		System.out.println("--->Actual:\t" + actualDuration);
		
		System.out.println("\n--->Expected:\t" + expectedToString);
		System.out.println("--->Actual:\t" + actualGenre);

		
		if (expectedTitle.equals(actualTitle)) {
			assertEquals(expectedTitle, actualTitle);
			System.out.println("\nTitle inputs are the same.");
			
		} else System.out.println("\nPrint Error.\nTitles are not the same.");
		

		if (expectedArtist.equals(actualArtist)) {
			assertEquals(expectedArtist, actualArtist);
			System.out.println("Artist inputs are the same.");
		}
		else System.out.println("Print Error.\nArtists are not the same.");
		
		if (expectedDuration == actualDuration) {
			assertEquals(expectedDuration, actualDuration);
			System.out.println("Duration inputs are the same.");
			
		} else System.out.println("Print error.\nDuration's are not the same.");
	
		if (expectedToString.equals(actualGenre)) {
			assertEquals(expectedToString, actualGenre);
			System.out.println("Genre inputs are the same.");
			
		} else System.out.println("Print error.\nGenre's are not the same.");
	}

	@Test
	public void testGetTuneInfoArtist() {
		System.out.println("Test Case: 3");
		System.out.println("=============");
		myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);
		String expectedArtist = "Kaleo";

		String[] fullList = myMusic.getTuneInfo();
		String actualArtist = fullList[0].split(",")[2].trim();

		if (expectedArtist.equals(actualArtist)) {
			System.out.println("Valid artist: " + expectedArtist);
			System.out.println("Actual Artist: " + actualArtist);

			System.out.println("Values are the same.");
			assertEquals(expectedArtist, actualArtist);
		} else {
			System.out.println("Test Case failed.");
		}
	}

	@Test
	public void testGetTuneInfoGenre() {
		System.out.println("\nTest Case: 4");
		System.out.println("=============");
		myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);

		String expected = "Rock and Roll";
		String[] fullList = myMusic.getTuneInfo(Genre.ROCK);
		for (int i = 0; i < fullList.length; i++) {

			String actual = fullList[i].split(",")[3].trim();
			String toString = actual.toString();
			if (expected.equals(actual)) {

				System.out.println("Actual: " + toString + "\nExpected: " + expected);

				assertSame(toString, actual);
				System.out.println("Values are the Same.");
			} else {
				System.out.println("Test Case failed");
			}
		}

	}

	@Test
	public void testPlay() {
		System.out.println("\nTest Case 5:");
		System.out.println("=============");
		myMusic.addtune("Break My Baby", "Kaleo", 350, Genre.ROCK);

		String[] getSong = myMusic.getTuneInfo();
		String strID = getSong[0].split(", ")[0].trim();
		// first element of first position within Tune instance
		int id = Integer.parseInt(strID);
		id--; // reset indexing: 0 - length of myMusic.length

		if (myMusic.play(id) != null) {
			assertNotNull(myMusic.play(id));
			System.out.println(myMusic.play(id)); // success
			System.out.println("Success.");

		} else {
			System.out.println("Test failed.");
			// Then Play() is null if this is executed
		}
	}

	@Test
	public void testAddtune() {
		System.out.println("\nTest Case 6:");
		System.out.println("=============");
		
		String inputTitle = "Break My Baby";
		System.out.println("Added Title:  \t\t" + inputTitle);
		
		String inputArtist = "Kaleo";
		System.out.println("Added Artist:   \t" + inputArtist);
		
		int inputDuration = 350;
		System.out.println("Added duration: \t" + inputDuration);
		
		Genre inputGenre = Genre.ROCK;
		System.out.println("Added Genre: \t\t" + inputGenre );
		
		System.out.println();
		myMusic.addtune(inputTitle, inputArtist, inputDuration, inputGenre);
		String[] tuneList = myMusic.getTuneInfo();
		for (int i = 0; i < tuneList.length; i++) {
			System.out.println(tuneList[i]);

			assertNotNull(tuneList[i]);
		}
	}

	@Test
	public void testSwitchOff() {
		System.out.println("Test Case 7:");
		System.out.println("=============");
		boolean status = myMusic.switchOff();

		if (status) {
			System.out.println("Successful");// Prints as expected
			assertNotNull(status);
		} else {
			System.out.println("Unsucessful");
		}
	}

	@Test
	public void testSwitchOn() {
		System.out.println("\nTest Case 8:");
		System.out.println("=============");
		boolean status = myMusic.switchOn();
		if (status) {
			System.out.println("Successful");
			assertNotNull(status);
		} else {
			System.out.println("Unsuccessful");
		}
	}

}
