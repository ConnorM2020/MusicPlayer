package part01;

public enum Genre {
	ROCK(0), POP(1), DANCE(2),
	JAZZ(3), CLASSICAL(4), OTHER(5);
	
	/*
	 * Converting the Enum type to a String equivlent 
	 * Calling the toString() function will display the String equivelent
	 * For example: ROCK - to Rock and Roll
	 */
	private String[] music = {"Rock and Roll","Easy Listening Pop",
			"Techno Dance", "Smooth Jazz", "Classical", "Unknown Genre"};
	
	private int choice;
	// The Enum parameter gets passed through the Private constructor
	
	private Genre(int choice) {
		this.choice = choice;
	}
	
	
	public String toString() {
		return music[choice];
	
	}
	

}
