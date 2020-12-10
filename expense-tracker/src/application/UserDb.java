package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class UserDb {
	File databaseFile;
	List<String> databaseLines; // all the lines of the database
	int numRecords; // number of records in database
	Map<User, List<String>> database = new HashMap<User, List<String>>();

	/**
	 * Constructs a user database with a given file path.
	 * 
	 * @param filePath Path to file.
	 */
	public UserDb() {
		database = new HashMap<User, List<String>>();
	}

	/**
	 * Searches for the given name.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return True if the name is found.
	 */
//	public boolean nameExistsInDb(User user) {
//		// if the user can't be found in the database
//		if (findUser(user) == -1) {
//			return false;
//		}
//		return true;
//	}
	public void addUser(User newUser)
	{
		database.put(newUser, new ArrayList<String>());
		///System.out.println(database.get(newUser));
		
	}
	
	public void addExpense(User current, String expense)
	{
		List<String> temp = database.get(current);
		temp.add(expense);
		database.put(current, temp);
		for (Entry<User, List<String>> entry : database.entrySet())
		{
			 System.out.print(entry.getKey()+" | ");
		        for(String expenses : entry.getValue()){
		            System.out.print(expenses+" |");
		        }
		        System.out.println();
		    
		}
	}
	
	public boolean findUser(User user) {

		// search through the database
		for (Entry<User, List<String>> entry : database.entrySet())
		{
			 if(entry.getKey().getID() == user.getID())
			 {
				 return true;
			 }

		    
		}

		// if the user can't be found
		return false;
	}
	
	public User returnUser(User r)
	{
		for (Entry<User, List<String>> entry : database.entrySet()) {
	        if (entry.getKey().equals(r)) {
	            return entry.getKey();
	        }
	    }
		return r;
	}


}
