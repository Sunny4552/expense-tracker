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
	Map<User, List<String>> database = new HashMap<User, List<String>>();

	/**
	 * Constructs a user database with a given map.
	 * 
	 */
	public UserDb() {
		database = new HashMap<User, List<String>>();
	}
	
	/**
	 * Adds User to main database.
	 * 
	 * @param New user that contains valid information
	 */
	public void addUser(User newUser)
	{
		database.put(newUser, new ArrayList<String>());
		///System.out.println(database.get(newUser));
		
	}
	
	/**
	 * Adds new expense associated with the correct user.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be added.
	 */
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
	
	/**
	 * Remove expense associated with the correct user.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be removed.
	 * @return True if expense is removed correctly.
	 */
	public boolean removeExpense(User current, String order)
	{

		List<String> temp = database.get(current);
		for (int i = 0; i < temp.size(); i++)
		{
			 String[] split = temp.get(i).split("/");
			 String ID = split[0];

			 if(ID.equals(order))
			 {
				 current.setBudget(current.getBudget() + Double.parseDouble(split[1]));
				 temp.remove(i);
				 database.put(current, temp);
				 return true;
			 }
			 
			    
		}
		return false;
			
	}
	
	/**
	 * Determines if User is overspending based on expenses.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return returns string of all items that are overspent on.
	 */
	public String overSpending(User current)
	{
		String output = " ";
		List<String> start = new ArrayList<String>();
		List<String> temp = database.get(current);
		for (int i = 0; i < temp.size(); i++)
		{
			int overCount = 0;
			String[] split = temp.get(i).split("/");
			String ID = split[2];
			System.out.println(ID);
			for (int k = 0; k < temp.size(); k++)
			{
				String[] split2 = temp.get(i).split("/");
				String checkID = split2[2];
				if(ID.equals(checkID) && !output.contains(checkID))
				{
					overCount++;
					start.add(temp.get(k));
					temp.remove(k);
					k--;
				}
				
			}
			if(overCount > 2)
				output += " " + ID + "(s)";
		}
		database.put(current, start);
		return output;
	}
	
	/**
	 * Checks if adding an expense meets all error checks.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @param expsense that is to be added.
	 */
	public boolean addCheck(User current, String expense)
	{
		if(current.getBudget() == null)
			return false;
		if(current.getBudget() - Double.parseDouble(expense) < 0)
			return false;
		return true;
	}
	
	/**
	 * Get all expenses in a form of a list.
	 * 
	 * @return String combined with all expenses is returned.
	 */
	public String getExpense()
	{
		String output = " ";
		for (Entry<User, List<String>> entry : database.entrySet())
		{
		        for(String expenses : entry.getValue()){
		            output += " " + expenses + " | \n ";
		        }
//		        output += "\n ";
		    
		}
		return output;
	}
	
	/**
	 * Looks through database for User specified.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return True if User is found.
	 */
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
	
	/**
	 * Checks to see if User logging in is a registered User.
	 * 
	 * @param user The user whose name is to be searched for.
	 * @return Return user if found.
	 */
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
