package application;

public class User {
	private String name;
	private int ID;
	private Double budget;
	
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
	
	public void setBudget(Double val)
	{
		this.budget = val;
	}
	
	public Double getBudget()
	{
		return budget;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
