package userregistrationsystem;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import com.opencsv.exceptions.CsvValidationException;

public class UserRegistrationSystem {

	private UserDatabase userDatabase = new UserDatabase();
	private String fileName = "userDb.csv";
	private Scanner scanner = new Scanner(System.in);
	private FileReaderAndWriter fir = new FileReaderAndWriter();

	public boolean isValidEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public void runApp() {
		try {
			userDatabase.setUsers(fir.fileReader(fileName));
		} catch (CsvValidationException | IOException e) {
		}

		int option = 0;
		do {
			System.out.println("1. Register a new user" + "\n" + "2. View user details by username" + "\n"
					+ "3. View all registered users" + "\n" + "4. Remove a user account" + "\n"
					+ "5. Save and Exit the program" + "\n");
			option = scanner.nextInt();
			scanner.nextLine(); // To clear the Buffer
			switch (option) {
			case 1 -> {
				User user = null;
				System.out.println("Enter a username:");
				String username = scanner.nextLine();
				System.out.println("Enter a password:");
				String password = scanner.nextLine();
				System.out.println("Enter an email:");
				String email = scanner.nextLine();
				if (!isValidEmail(email)) {
					System.out.println("Invalid email! Enter a valid email:");
					email = scanner.nextLine();
				}
				user = new User(username, password, email);
				try {
					userDatabase.addUser(user);
					fir.fileWriter(user, fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case 2 -> {
				System.out.println("Enter username:");
				String username = scanner.nextLine();
				try {
					userDatabase.getUserByUsername(username);
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
			}
			case 3 -> System.out.println(userDatabase.getAllUsers());
			case 4 -> {
				System.out.println("Enter username:");
				String username = scanner.nextLine();
				try {
					userDatabase.removeUser(username);
					fir.updateFile(userDatabase.getAllUsers(), fileName);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
			}
			case 5 -> {
				try {
					fir.updateFile(userDatabase.getAllUsers(), fileName);
				} catch (IOException e) {
				}
				option = 0;
				System.out.println("***** Program exited *****");
			}
			default -> System.out.println("Invalid option number!!");
			}

		} while (option != 0);

		scanner.close();
	}

}
