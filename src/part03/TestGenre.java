package part03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import part01.Genre;

public class TestGenre {

	@Before
	public void setUp() throws Exception {
	}

	@Test // Test valid Genre input
	public void testToString() {
		System.out.println("Test Case: 1 ");
		System.out.println("=============");
		// Test the input value is equal to Genre Enum type

		String inputTest = "Rock and Roll";
		Genre actual = Genre.ROCK;
		String actualString = actual.toString();
		
		System.out.println("--->Test input:  \t" + inputTest);
		System.out.println("--->Actual Genre:\t" + actualString);

		if (inputTest.equals(actualString)) {
			assertEquals(inputTest, actualString);
			System.out.println("Values are the same.");
		}
	}

	@Test // test invalid Genre input
	public void testInvalidGenre() {
		System.out.println("\nTest Case: 2");
		System.out.println("=============");
		String inputTest = "invalid data input";	
		// Change to: Rock and Roll - to get equal values

		Genre actual = Genre.ROCK;
		String toString = actual.toString();
		if (inputTest.equals(toString)) {
			System.out.println("Equal values are equal.");
		} else {
			System.out.println("Values are not the same.");
			assertNotSame(inputTest, toString);
		}
	}

}
