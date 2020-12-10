package application;

import java.util.ArrayList;

public class ExpenseTracker {

	private UserDb database;

	/**
	 * Constructs an exposure tracker with a given database file path
	 * 
	 * @param databaseFilePath The file path to the database text file.
	 */
	public ExpenseTracker() {
		database = new UserDb();
	}

	public void createNewUser(User user) {
		// calls writeNewUser() from UserDb
		database.addUser(user);

		// create empty records for the people who interacted with the user
	}

	public void addExpenseDB(User current, String expense)
	{
		database.addExpense(current, expense);
	}
	
	public boolean loginUser(User user) {
		return database.findUser(user);
	}
	
	public User getUser(User get)
	{
		return database.returnUser(get);
	}


}
