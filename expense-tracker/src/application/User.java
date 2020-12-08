package application;

public class User {
	private String name;
	private int ID;
	
	/**
	 * Constructs a user object with a given name.
	 * 
	 * @param name The user's name.
	 */
	public User(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	/**
	 * Gets the user's name.
	 * 
	 * @return The user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the user's address.
	 * 
	 * @return the user's address object.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Checks that the name entered is in the correct format.
	 * 
	 * @param name The name the user entered.
	 * @return True if the name is in the correct format.
	 */
	public static boolean validName(String name) {
		// Makes sure user has entered a first name and a last name and are alphabet
		// characters
		String[] fullName = name.split(" ");

		if (fullName.length < 2)
			return false;

		return fullName.length == 2;
	}


	/**
	 * Returns the User as a string
	 */
	@Override
	public String toString() {
		// concatenate all instance fields of user together
		//return name + "|" + ID + "|" + age;
		return name + "|" + ID;
	}

}
