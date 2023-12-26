package part01;

import java.util.Scanner;

public class Menu {
	private String items[];
	private String title;
	private Scanner input;

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	private void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	public int getUserChoice() {
		if ( items == null || items.length == 0 ) {
			return 0;
		}
		int value = 0;
		display();
		boolean ok = false;
		do {
			System.out.print("Enter Selection: ");
			try {
				value = input.nextInt();
				if ( (value > 0 && value <= items.length) ) {
					ok = true;
				}
				else {
					System.out.println("Enter a value between 1 and " + items.length);
				}
			}
			catch(Exception ex) {
				System.out.println("Error input.");
				input.nextLine();
			}
		} while (!ok);

		return value;
	}
}
