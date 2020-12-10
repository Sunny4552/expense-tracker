package application;

import java.util.ArrayList;

public class ExpenseTracker {

	private UserDb database;

	public ExpenseTracker() {
		database = new UserDb();
	}

	public void createNewUser(User user) {
		// calls writeNewUser() from UserDb
		database.addUser(user);

		// create empty expenses
	}

	public void addExpenseDB(User current, String expense)
	{
		database.addExpense(current, expense);
	}
}
