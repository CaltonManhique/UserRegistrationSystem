package userregistrationsystem;

import java.io.*;
import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class FileReaderAndWriter {

	private static String pathName = "UserDB/";

	public void fileWriter(User user, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { user.getUsername(), user.getPassword(), user.getEmail() };
		writer.writeNext(data);

		writer.close();
	}

	public Set<User> fileReader(String fileName) throws IOException, CsvValidationException {
		Set<User> arr = new HashSet<User>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			User user = new User(nextLine[0], nextLine[1], nextLine[2]);
			arr.add(user);
		}

		reader.close();

		for (User us : arr)
			System.out.println(us);
		return arr;
	}

	// Updates the file overwritng all data in the file
	public void updateFile(Set<User> users, String fileName) throws IOException {
		Set<String[]> arr = new HashSet<String[]>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (User user : users) {
			String[] data = { user.getUsername(), user.getPassword(), user.getEmail() };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}
}
