package application;

import java.util.ArrayList;

public class ExpenseTracker {

	private UserDb database;

	/**
	 * Constructs an exposure tracker with a given database file path
	 * 
	 * @param databaseFilePath The file path to the database text file.
	 */
	public ExpenseTracker(String databaseFilePath) {
		database = new UserDb(databaseFilePath);
	}

	/**
	 * Checks if a user could login using provided information.
	 * 
	 * @param user The user with provided information trying to login.
	 * @return True if the user exists in the database and could login, false
	 *         otherwise.
	 */
	public boolean loginUser(User user) {
		return userAlrRegistered(user);
	}

	/**
	 * Adds all of the user's information to the database.
	 * 
	 * @param user         The user with provided information trying to register.
	 * @param testStatus   The test status of the user being registered.
	 * @param interactions A list of interaction's names of the user being
	 *                     registered, separated by ", ".
	 */
	public void createNewUser(User user, String testStatus, String interactions) {
		// calls writeNewUser() from UserDb
		int userLineNum = database.writeNewUser(user, testStatus, interactions);

		// create empty records for the people who interacted with the user
	}


	/**
	 * Gets the user's COVID-19 test status .
	 * 
	 * @param user The user that the system will be checking the test status for.
	 * @return The user's COVID test status.
	 */
	public String getTestStatus(User user) {
		return database.readTestStatus(user);
	}

	/**
	 * Gets the user's exposure status.
	 * 
	 * @param user The user that the system will be checking the exposure status
	 *             for.
	 * @return The user's exposure status.
	 */

	/**
	 * Retrieves an array of the names of the user's interactions.
	 * 
	 * @param user The user's whose interaction is being requested.
	 * @return An array of the user's interactions.
	 */

	public boolean userAlrRegistered(User user) {
		// check if the name exists in the database and if the record has an address
		// (fully registered).
		return database.nameExistsInDb(user) && database.userFullyRegistered(user);
	}

	/**
	 * Adds an interaction to the user's current list of interactions in the
	 * database.
	 * 
	 * @param user        The user who is adding an interaction.
	 * @param interaction The name of the person whom the user has interacted with.
	 */





}