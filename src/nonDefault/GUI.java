package nonDefault;
// Made by: Abdullah Sohail
// The GUI client for rating books and receiving recommendations based off the books
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
	Container frame;
	JButton createUser;
	JButton login;
	JButton randomBooks;
	JButton suggestBooks;
	JButton search;
	JButton rate;
	JTextField searchBar;
	JLabel userName;

	public GUI() {
		super("GUI"); // Set the frame's name
		frame = getContentPane();
		frame.setLayout(new BorderLayout());

		createUser = new JButton("Create User");
		login = new JButton("User login");
		randomBooks = new JButton("Show 10 random books");
		suggestBooks = new JButton("Recommended Books");
		search = new JButton("Search");
		searchBar = new JTextField("Enter a book name to search", 30);
		userName = new JLabel("   User: Not logged in");
		rate = new JButton("Rate a book");

		JLabel logo = new JLabel(new ImageIcon("logo.png"));
		JLabel promo = new JLabel(new ImageIcon("promo.jpg"));

		JPanel north = new JPanel(new BorderLayout());
		JPanel sNorth = new JPanel(new GridLayout(1, 3));
		JPanel center = new JPanel(new GridLayout(1, 10));
		JPanel south = new JPanel(new GridLayout(1, 5));

		north.add(logo, BorderLayout.NORTH);
		sNorth.add(searchBar);
		sNorth.add(search);
		sNorth.add(userName);
		north.add(sNorth, BorderLayout.SOUTH);

		center.add(promo);

		south.add(createUser);
		south.add(login);
		south.add(randomBooks);
		south.add(suggestBooks);
		south.add(rate);

		createUser.addActionListener(this);
		login.addActionListener(this);
		randomBooks.addActionListener(this);
		suggestBooks.addActionListener(this);
		rate.addActionListener(this);
		search.addActionListener(this);

		frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);

		setSize(900, 700); // Set the frame's size
		setVisible(true); // Show the frame
	} // Constructor

	public void actionPerformed(ActionEvent e) {

		StoreInfo u = new StoreInfo();

		if (e.getSource() == createUser) {
			String uHolder = JOptionPane.showInputDialog("Enter a user name (no spaces)");
			u.userName(uHolder);
			userName.setText(uHolder);
			JOptionPane.showMessageDialog(null, "User created, currently logged in");
		} else if (e.getSource() == login) {
			String uHolder = JOptionPane.showInputDialog("Enter your user name");
			if (u.check(uHolder)) {
				JOptionPane.showMessageDialog(null, "Logged In");
				userName.setText(uHolder);
			} else {
				JOptionPane.showMessageDialog(null, "User not found!");
			}
		} else if (e.getSource() == randomBooks) {

			String[] bookNames = u.random();
			randomBooks(bookNames);

		} else if (e.getSource() == suggestBooks) {
			if (userName.getText().equals("   User: Not logged in")) {
				JOptionPane.showMessageDialog(null, "First login as a user!");
			} else {
				String[] recommend = u.recommend(userName.getText());
				recommendedBooks(recommend);
			}

		} else if (e.getSource() == search) {
			String s = u.search(searchBar.getText());
			JOptionPane.showMessageDialog(null, s);
		} else if (e.getSource() == rate) {
			if (userName.getText().equals("   User: Not logged in")) {
				JOptionPane.showMessageDialog(null, "First login as a user!");
			} else {
				String bookName = JOptionPane.showInputDialog("Enter the name of the book you wish to rate");
				int rateNum = Integer.parseInt(JOptionPane.showInputDialog("Enter the rating"));
				u.rate(userName.getText(), bookName, rateNum);
				JOptionPane.showMessageDialog(null, "Your rating has been stored!");
			}
		}
	}

	public static void randomBooks(String[] books) {
		JFrame rBook = new JFrame("10 Random Books");

		StoreInfo u = new StoreInfo();

		JPanel bookHolder = new JPanel(new GridLayout(10, 1));
		for (int i = 0; i < books.length; i++) {
			int a = u.averageRatings(books[i]);
			int b = u.totalRatings(books[i]);
			JLabel a1 = new JLabel(books[i] + " --- Average Rating: " + a + " --- Total Ratings: " + b);
			bookHolder.add(a1);
		}

		rBook.getContentPane().add(bookHolder);
		rBook.setSize(500, 800);
		rBook.setVisible(true);
	}

	public static void recommendedBooks(String[] books) {
		JFrame rBook = new JFrame("Recommended books for you");

		StoreInfo u = new StoreInfo();
		JPanel bookHolder = new JPanel(new GridLayout(10, 1));
		for (int i = 0; i < 10; i++) {
			int a = u.averageRatings(books[i]);
			int b = u.totalRatings(books[i]);
			JLabel a1 = new JLabel(books[i] + " --- Average Rating: " + a + " --- Total Ratings: " + b);
			bookHolder.add(a1);
		}

		rBook.getContentPane().add(bookHolder);
		rBook.setSize(500, 800);
		rBook.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI(); // Create a GUI frame
	} // main method
}
