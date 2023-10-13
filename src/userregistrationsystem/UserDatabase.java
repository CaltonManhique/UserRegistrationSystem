package userregistrationsystem;

import java.util.*;

public class UserDatabase {

	private Set<User> users;

	public UserDatabase() {
		users = new HashSet<>();
	}

	public void addUser(User user) {
		if (users.contains(user)) {
			System.out.println("User already exists.");
		} else {
			users.add(user);
			System.out.println("User added to the DB successfully.");
		}
	}

	public void removeUser(String username) {
		users.remove(getUserByUsername(username));
		System.out.println("User unsubscribed succssfully.");
	}

	public User getUserByUsername(String username) {
		User user = null;
		for (User us : users) {
			if (us.getUsername().equals(username)) {
				user = us;
				break;
			}
		}
		if (user == null) {
			throw new NullPointerException("User not found!");
		} else {
			return user;
		}
	}

	public Set<User> getAllUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
