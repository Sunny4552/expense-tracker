package application;

import java.util.ArrayList;

public class ExpenseTracker {

	private UserDb database;

	/**
	 * Constructs an exposure tracker with a given database 
	 * 
	 * @param databaseFilePath The file path to the database map.
	 */
	public ExpenseTracker() {
		database = new UserDb();
	}

	/**
	 * Adds User to main database.
	 * 
	 * @param New user that contains valid information
	 */
	public void createNewUser(User user) {
		// calls writeNewUser() from UserDb
		database.addUser(user);
		// create empty records for the people who interacted with the user
	}

	/**
	 * Adds new expense associated with the correct user in database.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be added.
	 */
	public void addExpenseDB(User current, String expense)
	{
		database.addExpense(current, expense);
	}
	
	/**
	 * Remove expense associated with the correct user in database.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be removed.
	 * @return True if expense is removed correctly.
	 */
	public boolean removeExpenseDB(User current, String order)
	{
		return database.removeExpense(current, order);
	}
	
	/**
	 * Determines if User is overspending based on expenses in database.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return returns string of all items that are overspent on.
	 */
	public String overSpendingDB(User current)
	{
		return database.overSpending(current);
	}
	
	/**
	 * Checks if adding an expense meets all error checks in database.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be added.
	 */
	public boolean addCheckDB(User current, String expense)
	{
		return database.addCheck(current, expense);
	}
	
	/**
	 * Looks through database for User specified.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return True if User is found.
	 */
	public boolean loginUser(User user) {
		return database.findUser(user);
	}
	
	/**
	 * Checks to see if User logging in is a registered User in database.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return Return user if found.
	 */
	public User getUser(User get)
	{
		return database.returnUser(get);
	}
	
	/**
	 * Get all expenses in a form of a list from database.
	 * 
	 * @return String combined with all expenses is returned.
	 */
	public String getExpenses()
	{
		return database.getExpense();
	}

}
