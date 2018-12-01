import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;

import javafx.scene.image.Image;

/**
 * NormalUser.java
 * @author Hau Yi Choi
 * @version 2.1
 */

public class NormalUser extends User implements Storable {

	protected int balance;
	protected int[] transaction;
	protected LinkedList<Integer> requesting= new LinkedList<>();
	protected LinkedList<Integer> reserved =new LinkedList<>();
	private LinkedList<Borrowing> currentBorrowHistory = new LinkedList<>();

	// sql statement
	private String statement;

	/**
	 * When user is already in the database.
	 * 
	 * @param username
	 * @throws Exception
	 */
	public NormalUser(String username) throws Exception {
		super.username = username;
		statement = "select * from normal_user where username = '" + username + "';";

		ResultSet r = SQLHandle.get(statement);
		while (r.next()) {
			super.username = r.getString("username");
			balance = r.getInt("balance");
		}

		try {
			statement = "select borrowingID from current_borrowing where username = '" + username + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				Borrowing b = new Borrowing(r.getInt("borrowingID"));
				currentBorrowHistory.add(b);
			}
			statement = "select * from resered_item where username = '" + username + "';";

			r = SQLHandle.get(statement);
			while (r.next()) {
				reserved.add(r.getInt("ResourceID"));
			}

			statement = "select * from requesting  where username = '" + username + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				requesting.add(r.getInt("ResourceID"));
			}
		} catch (SQLSyntaxErrorException e) {
			/** it can be do nothing when Table 'cw230.resered_item' doesn't exist because
			 * sometime
			 */ the user dont have any requesting or current borrow or request
		}catch(SQLException ex) {
			// it can be do nothing when Table 'cw230.resered_item' doesn't exist because
						// sometime
						// the user dont have any requesting or current borrow or request
		}
	}

	private boolean canBorrow() {
		boolean b = true;
		if (balance < 0) {
			b = false;
		} else if (!currentBorrowHistory.isEmpty()) {
			for (int i = 0; i < currentBorrowHistory.size(); i++) {
				if (currentBorrowHistory.get(i).isOverdue()) {
					b = false;
					break;
				}
			}
		}
		return b;
	}

	public void borrow(int resourceID) throws Exception {
		Resource r = new Resource(resourceID);
		if (!canBorrow()) {
			throw new IllegalArgumentException("You cannot borrow either you get fine or have something overdue");
		} else {
			Borrowing b = r.borrow(super.username);
			currentBorrowHistory.add(b);
		}

		// storing
		statement = "insert into current_borrowing values ('" + this.username + "','"
				+ currentBorrowHistory.getLast().getBorrowNo() + "');";
		SQLHandle.set(statement);

	}

	public void getUserinfo(String username, String firstName, String lastName, int mobileNo, Image userImage) {
		super.username = username;
		super.firstName = firstName;
		super.lastName = lastName;
		super.mobileNo = mobileNo;
		super.userImage = userImage;
	}
	
	public void reduceBalance(int fine) {
		balance -= fine;
	}
	
	public int getBalance() {
		return balance;
	}
	

	public void reserve(String resourse) {

	}

	/*
	 * public Book searchBook(String author) { for (Book b : books) { if
	 * (b.getAuthor().equals(author)) { return b; }
	 * 
	 * return null; } }
	 */
	public void requestBook(Resource r, String title) {

	}

	public void getBook() {

	}

	/*
	 * public void returnBook(Object bookList, Object book) { bookList.put(book,
	 * bookList.get(book) + 1); }
	 */
	private void storeBorrow() throws SQLException {
		// when borrowing something
		// only one item can be borrow each time borrow method is called

	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub

	}

}
