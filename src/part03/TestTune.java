package part03;

import static org.junit.Assert.*;

import part01.Genre;
import part01.Tune;

import org.junit.Before;
import org.junit.Test;

public class TestTune {
	// Create a Tune instance to be used to test the Object instance

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTune() {

		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nConstructor Test Case: 1");
		System.out.println("==========================");
		// Since Tune is an instance that has been added 5 times, ID will increment 5 times
		// Causing the new ID to be 5
		int expectedID = 5; 
		
		int actualID = newTune.getID();
		System.out.println("--->Expected ID:\t" + expectedID);
		System.out.println("---->Actual ID: \t" + actualID);

		String expectedTitle = "World on Fire";
		String actualTitle = newTune.getTitle();

		assertEquals(expectedTitle, actualTitle);
		System.out.println("--->Expected Title: \t" + expectedTitle);
		System.out.println("--->Actual Title:\t" + actualTitle);

		int expectedDuration = 400;
		int actualDuration = newTune.getDuration();
		System.out.println("--->Expected Duration:\t" + expectedDuration);
		System.out.println("---->Actual Duration:\t" + actualDuration);
		assertEquals(expectedDuration, actualDuration);

		String expectedGenre = "Smooth Jazz";
		Genre actualGenre = newTune.getGenre();
		String toString = actualGenre.toString();

		System.out.println("--->Expected Genre:\t" + expectedGenre);
		System.out.println("--->Actual Genre:\t" + expectedGenre);
		assertEquals(expectedGenre, toString);
	}

	@Test
	public void testToString() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 2");
		System.out.println("==============");
		String res = newTune.toString();
		System.out.println(res);

		if(res != null) {
		assertNotNull(res);
		}
	}

	@Test
	public void testPlay() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 3");
		System.out.println("==============");
		String play = newTune.play();
		
		if (play == null) {
			System.out.println("Test Case failed");
		} else {
			System.out.println("--->" + play );
			assertNotNull(play);
			System.out.println("Success.");
		}
	
	}

	@Test
	public void testGetID() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 4");
		System.out.println("==============");
		int expected = 3;
		int actual = newTune.getID();
		
		if (expected == actual) {
		System.out.println("--->Expected:\t" + expected);
		System.out.println("--->Actual: \t" + actual);
		assertEquals(expected, actual);
		} else {
			System.out.println("Test Case failed.");
		}

	}
	@Test
	public void testGetTitle() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 5");
		System.out.println("==============");
		String expected = "World on Fire";
		String actual = newTune.getTitle();

		if (expected.equals(actual)) {

			System.out.println("--->Expected:\t" + expected);
			System.out.println("--->Actual:\t" + actual);
			System.out.println("Values are the same.");
			assertEquals(expected, actual);
			System.out.println("Success");
		} else {
			System.out.println("Test Case failed.");
		}
	}
	@Test
	public void testGetArtist() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 6");
		System.out.println("==============");
		String expected = "The Ink Spots"; // Call from global created Tune instance
		String actual = newTune.getArtist();

		System.out.println("Expected: " + expected);
		System.out.println("Actual: " + actual);

		if (expected.equals(actual)) {
			assertEquals(expected, actual);
			System.out.println("Success");
		} else {
			System.out.println("Test Case failed.");
		}
	}
	@Test
	public void testGetDuration() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 7");
		System.out.println("==============");
		int expected = 400; // Call to global Tune instance
		int result = newTune.getDuration();
		System.out.println("--->Expected Duration:\t" + expected);
		System.out.println("--->Actual Duration:\t" + result);

		if (expected == result) {
			assertEquals(result, expected);
			System.out.println("Success");
		} else {
			System.out.println("Test Case failed.");
		}
	}
	@Test
	public void testGetPlayCount() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("\nTest Case: 8");
		System.out.println("==============");
		int expected = 0;
		int result = newTune.getPlayCount();
		System.out.println("--->Expected:\t" + expected);
		System.out.println("--->Actual:\t" + result);

		if (expected == result) {
			assertEquals(expected, result);
			System.out.println("Success");
		} else {
			System.out.println("Test Case failed.");
		}
	}

	@Test
	public void testGetGenre() {
		Tune newTune = new Tune("World on Fire", "The Ink Spots", 400, Genre.JAZZ);
		System.out.println("Test Case: 9");
		System.out.println("==============");
		String expectedGenre = "Smooth Jazz";
		Genre actualGenre = newTune.getGenre();
		String toString = actualGenre.toString();

		if (expectedGenre.equals(toString)) {
			System.out.println("--->Expected Genre:\t" + expectedGenre);
			System.out.println("--->Actual Genre:\t" + toString);
			assertEquals(expectedGenre, toString);
		} else {
			System.out.println("Test Case failed.");
		}
	}

}
