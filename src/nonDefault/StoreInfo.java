package nonDefault;
/*
 * Creator: Abdullah Sohail
 * Purpose: Back end methods to store recommendations and recommend books
 */
import java.io.*;
import java.util.*;

public class StoreInfo {

	public StoreInfo() {
	}
	
	// Intermediary method to read books from file and then use books method to write the book names to bookNames.txt
	public String[] bookNames() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader("books55.txt"));
			int lineCount = 0;
			String line = buffer.readLine();
			while (line != null) {
				lineCount++;
				line = buffer.readLine();
			}
			buffer.close();
			buffer = new BufferedReader(new FileReader("books55.txt"));

			String[] bookHolder = new String[lineCount];
			String splitinto = "";
			for (int i = 0; i < lineCount; i++) {
				splitinto += buffer.readLine() + ",";
			}
			for (int i = 0; i < lineCount; i++) {
				bookHolder = splitinto.split(",");
			}
			books(bookHolder);
			return bookHolder;
		} catch (Exception q) {

		}
		String[] temp = new String[1];
		return (temp);
	}
	
	// Method that, when given a string array, writes each line in the array to a file called bookNames.txt
	public void books(String[] booknames) {
		try {
			PrintWriter kb = new PrintWriter(new FileWriter("bookNames.txt"));
			for (int i = 0, j = 1; i < booknames.length / 2; i++, j += 2) {
				kb.println(booknames[j]);
			}
			kb.close();
		} catch (Exception e) {

		}
	}

	// Method to get the list of book titles
	public String[] bookTitles() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader("bookNames.txt"));
			int lineCount = 0;
			String line = buffer.readLine();
			while (line != null) {
				lineCount++;
				line = buffer.readLine();
			}
			buffer.close();
			buffer = new BufferedReader(new FileReader("bookNames.txt"));

			String[] bookHolder = new String[lineCount];
			for (int i = 0; i < lineCount; i++) {
				bookHolder[i] = buffer.readLine();
			}
			return bookHolder;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//If this gets returned, an error occurred
		String[] dontReturn = new String[0];
		return dontReturn;
	}

	// Method that returns the ratings for each book
	public int[][] ratings() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader("ratings.txt"));
			int lineCount = 0;
			String line = buffer.readLine();
			while (line != null) {
				lineCount++;
				line = buffer.readLine();
			}
			buffer.close();

			buffer = new BufferedReader(new FileReader("ratings.txt"));

			String hold = "";
			for (int i = 0; i < lineCount; i++) {
				hold += buffer.readLine() + " ";
			}
			int[][] rate = new int[lineCount][55];
			String[] tHolder = hold.split(" ");

			int k = 0;

			for (int i = 0; i < lineCount; i++) {
				k++;
				for (int j = 0; j < 55; j++) {
					rate[i][j] = Integer.parseInt(tHolder[k]);
					k++;
				}
			}
			return rate;

		} catch (Exception l) {

		}
		// This should not be returned, as that means there is an error
		int[][] dontReachhere = new int[1][1];
		return dontReachhere;
	}
	
	// For any user created, this method will create an empty ratings list for them
	public void userName(String user) {
		try {
			PrintWriter fw = new PrintWriter(new FileWriter("ratings.txt", true));
			fw.println();
			fw.print(user);
			for (int i = 0; i < 55; i++) {
				fw.print(" 0");
			}
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Method that gets the average ratings for each book
	public int averageRatings(String bookname) {
		int[][] ratings = ratings();
		String[] bookTitles = bookTitles();

		int bookPlace = 0;
		for (int i = 0; i < bookTitles.length; i++) {
			if (bookname.equals(bookTitles[i])) {
				bookPlace = i;
				break;
			}
		}

		int avg = 0;
		for (int i = 0; i < ratings.length; i++) {
			avg += ratings[i][bookPlace];
		}
		return avg;
	}
	
	// Gets the total ratings
	public int totalRatings(String bookname) {
		int[][] ratings = ratings();
		String[] bookTitles = bookTitles();

		int bookPlace = 0;
		for (int i = 0; i < bookTitles.length; i++) {
			if (bookname.equals(bookTitles[i])) {
				bookPlace = i;
				break;
			}
		}

		int total = 0;
		for (int i = 0; i < ratings.length; i++) {
			if (ratings[i][bookPlace] != 0) {
				total++;
			}
		}
		return total;
	}

	// Method to get list of usernames
	public String[] userNameHolder() {
		try {

			BufferedReader buffer = new BufferedReader(new FileReader("ratings.txt"));
			int lineCount = 0;
			String line = buffer.readLine();
			while (line != null) {
				lineCount++;
				line = buffer.readLine();
			}
			buffer.close();

			buffer = new BufferedReader(new FileReader("ratings.txt"));

			String hold = "";
			for (int i = 0; i < lineCount; i++) {
				hold += buffer.readLine() + " ";
			}
			buffer.close();

			String[] tHolder = hold.split(" ");
			String[] uzer = new String[lineCount];
			for (int i = 0, j = 0; i < lineCount; i++, j += 56) {
				uzer[i] = tHolder[j];
			}

			return uzer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] dontReturn = new String[0];
		return dontReturn;
	}
	
	// Method to cehck whether the user is registered
	public boolean check(String user) {
		String[] userName = userNameHolder();
		for (int i = 0; i < userName.length; i++) {
			if (userName[i].equals(user)) {
				return true;
			}
		}
		return (false);
	}
	
	// Method to let a user rate a book
	public void rate(String userName, String bookName, int rateNum) {

		int[][] tRate = ratings();
		String[] bookTitles = bookTitles();
		String[] userNames = userNameHolder();

		int bookLocation = 0;
		int userLocation = 0;

		for (int i = 0; i < bookTitles.length; i++) {
			if (bookName.equals(bookTitles[i])) {
				bookLocation = i;
				break;
			}
		}
		for (int i = 0; i < userNames.length; i++) {
			if (userName.equals(userNames[i])) {
				userLocation = i;
				break;
			}
		}

		tRate[userLocation][bookLocation] = rateNum;

		try {
			PrintWriter kb = new PrintWriter(new FileWriter("ratings.txt"));
			for (int i = 0; i < tRate.length; i++) {
				kb.print(userNames[i]);
				for (int j = 0; j < tRate[0].length; j++) {
					kb.print(" " + tRate[i][j]);
				}
				kb.println();
				kb.close();
			}
		} catch (Exception e) {

		}

	}
	
	// Method to recommend a book
	public String[] recommend(String user) {
		int[][] ratings = ratings();

		String[] bookNames = bookTitles();
		String[] usernames = userNameHolder();

		int userLoc = 0;

		for (int i = 0; i < usernames.length; i++) {

			if (user.equals(usernames[i])) {
				userLoc = i;
				break;
			}
		}

		ArrayList<Integer> unread = new ArrayList<Integer>();
		for (int i = 0; i < ratings[0].length; i++) {
			if (ratings[userLoc][i] == 0) {
				unread.add(i);
			}
		}

		int[] pop = new int[unread.size()];
		for (int i = 0; i < pop.length; i++)
			pop[i] = 0;
		
		for (int i = 0; i < unread.size(); i++) {
			for (int j = 0; j < ratings.length; j++) {
				if (ratings[j][unread.get(i)] != 0) {
					pop[i] += ratings[j][unread.get(i)];
				}
			}
		}
		String[] correspond = new String[pop.length];
		for (int y = 0; y < pop.length; y++){
			correspond[y] = bookNames[unread.get(y)];
		}

		
		int i, j, t = 0;
		String te = "";
		for (i = 0; i < pop.length; i++) {
			for (j = 1; j < (pop.length - i); j++) {
				if (pop[j - 1] < pop[j]) {
					t = pop[j - 1];
					pop[j - 1] = pop[j];
					pop[j] = t;

					te = correspond[j - 1];
					correspond[j - 1] = correspond [j];
					correspond[j] = te;;
				}
			}
		}

		String[] rBooks = new String[10];


		for (int i1 = 0; i1 < 10; i1++) {
			rBooks[i1] = correspond[i1];
		}
		return rBooks;
	}
	
	// Method to get 10 random list of books
	public String[] random() {
		String[] bookNames = bookTitles();
		int[] bookPlace = new int[10];
		for (int i = 0; i < 10; i++)
			bookPlace[i] = 0;

		for (int i = 0; i < 10; i++) {
			int random = (int) (Math.random() * 54);
			for (int j = 0; j < 10; j++) {
				if (bookPlace[j] == random) {
					random++;
					j = 0;
				}
			}
			bookPlace[i] = random;
		}
		String[] books1 = new String[bookPlace.length];
		for (int i = 0; i < bookPlace.length; i++)
			books1[i] = (bookNames[bookPlace[i]]);
		return books1;
	}
	
	// Method to search for a book and return the average rating, total ratings and book name for a book
	public String search(String search) {

		String[] bookNames = bookTitles();

		for (int i = 0; i < bookNames.length; i++) {
			if (bookNames[i].equalsIgnoreCase(search)) {
				int tRate = totalRatings(bookNames[i]);
				int aRate = averageRatings(bookNames[i]);

				String returnThis = (bookNames[i] + "\nAverage Rating: " + aRate + "\nTotal Ratings: " + tRate);
				return returnThis;
			}
		}
		return ("Book not found");
	}

	public static void main(String[] args) throws IOException {
		
		//Tests:
		
		/*
		 * StoreInfo t = new StoreInfo();
		 * System.out.println(t.averageRatings("Speak"));
		 * System.out.println(t.totalRatings("Speak"));
		 */

		/*
		 * String[] print = t.bookNames(); for (int i = 0; i < print.length;
		 * i++) { // System.out.println(print[i]); }
		 * 
		 * int[][] print2 = t.ratings(); for (int i = 0; i < print2.length; i++)
		 * { for (int j = 0; j < 55; j++) { System.out.println(print2[i][j]); }
		 * 
		 * }
		 */
		// t.userName("swag");

		/*
		 * String[] booksTitles = t.userNameHolder(); for (int i = 0; i <
		 * booksTitles.length; i++) { System.out.println(booksTitles[i]); }
		 */
		// System.out.println(t.search("Speak"));
	}

}
