import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;

import javafx.scene.image.Image;

/**
 * This class contains all the methods used by normal users.
 * They can borrow, request and reserve resources,
 * check their balance and view their reserved items, requested items 
 * and borrowing and transaction history.
 * 
 * @author Hau Yi Choi
 * @version 2.1
 */

public class NormalUser extends User implements Storable {

	protected int balance;
	protected int[] transaction;
	protected LinkedList<Integer> requesting = new LinkedList<>();
	protected LinkedList<Integer> reserved = new LinkedList<>();
	private LinkedList<Borrowing> currentBorrowHistory = new LinkedList<>();

	//SQL statement.
	private String statement;

	/**
	 * Method for checking borrow history, reserved and requested items
	 * when the user is already in the database.
	 * 
	 * @param username The username.
	 * @throws Exception
	 */
	public NormalUser(String username) throws Exception {
		super.username = username;
		//SQL statement to get the username from the database
		statement = "select * from normal_user where username = '" + username + "';";

		ResultSet r = SQLHandle.get(statement);
		while (r.next()) {
			super.username = r.getString("username");
			balance = r.getInt("balance");
		}

		try {
			//Add the borrowing to the current borrow history.
			statement = "select borrowingID from current_borrowing where username = '" + username + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				Borrowing b = new Borrowing(r.getInt("borrowingID"));
				currentBorrowHistory.add(b);
			}
			statement = "select * from reserved_item where username = '" + username + "';";

			//Add a resource to the reserved list.
			r = SQLHandle.get(statement);
			while (r.next()) {
				reserved.add(r.getInt("ResourceID"));
			}

			//Add a resource to the requested list.
			statement = "select * from requesting  where username = '" + username + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				requesting.add(r.getInt("ResourceID"));
			}
		} catch (SQLSyntaxErrorException e) {
			/*
			 * It might do nothing when Table 'cw230.resered_item' doesn't exist because
			 * the user have not reserved, requested or currently borrowing anything.
			 */
		} catch (SQLException ex) {
			/*
			 * It might do nothing when Table 'cw230.resered_item' doesn't exist because
			 * the user have not reserved, requested or currently borrowing anything.
			 */
		}
	}

	/**
	 * Function to check if the user can borrow any resource. 
	 * If they have outstanding fines or hasn't returned a book 
	 * beyond its return date they can't borrow.
	 * 
	 * @return b True if they can borrow, false when can't.
	 */
	private boolean canBorrow() {
		boolean b = true;
		//Check if they have outstanding fines.
		if (balance < 0) {
			b = false;
		//Check if they have books beyond their return date.
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

	/**
	 * Function to borrow a resource and add it to curentBorrowHistory.
	 * 
	 * @param resourceID The ID of the resource being borrowed.
	 * @throws Exception When the user has outstanding fines or 
	 * still borrowing books beyond their return date. 
	 */
	public void borrow(int resourceID) throws Exception {
		Resource r = new Resource(resourceID);
		//Display message for user if they can't borrow.
		if (!canBorrow()) {
			throw new IllegalArgumentException("You cannot borrow either you get fine or have something overdue");
		//Otherwise add it to their currently borrowed items.
		} else {
			Borrowing b = r.borrow(super.username);
			currentBorrowHistory.add(b);
		}

		//Add it to the database.
		statement = "insert into current_borrowing values ('" + this.username + "','"
				+ currentBorrowHistory.getLast().getBorrowNo() + "');";
		SQLHandle.set(statement);

	}

	/**
	 * Method to get all the information about the user.
	 * 
	 * @param username The username of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param mobileNo The mobile number of the user.
	 * @param userImage The user's image.
	 */
	public void getUserinfo(String username, String firstName, String lastName, int mobileNo, String address, Image userImage) {
		super.username = username;
		super.firstName = firstName;
		super.lastName = lastName;
		super.mobileNo = mobileNo;
		super.address = address;
		super.userImage = userImage;
	}

	/**
	 * Method to reduce the user's balance
	 * 
	 * @param fine The amount of the fine to reduce the user's balance with.
	 */
	public void reduceBalance(int fine) {
		balance -= fine;
	}

	/**
	 * Method to get the user's balance.
	 * 
	 * @return balance The user's balance.
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * This method allows the user to request a resource 
	 * and adds it to the database.
	 * 
	 * @param resourceID The ID of the resource.
	 * @throws Exception
	 */
	public void request(int resourceID) throws Exception {
		Resource r = new Resource(resourceID);
		//Add the user to the requesting list of the resource.
		statement = "insert into request_item value('" + this.username + "','" + r.getId() + "');";
		SQLHandle.set(statement);
		r.request(this.username);
	}

	/**
	 * Method to display the user's transaction history,
	 * including date, amount, resourceID and days overdue if overdue.
	 * 
	 * @return The borrowing history.
	 * @throws Exception
	 */
	public String transactionHistory() throws Exception {
		SQLHandle a = new SQLHandle();
		String history = String.format("%20s %20s %20s %20s \n", "Date" , "Amount", "resourceID(ifOverdue)", "days(if Overdue)");
		//SQL statement to get date, amount and resourceID.
		String statement = "select T.transID, T.amount, T.date,W.resourceID, W.borrowingID  from \n"
				+ "(select transaction.transID,amount,date,username from transaction,transaction_his \n"
				+ " where transaction.transID=transaction_his.transID) as T \n" + "left outer join \n"
				+ "(select transID,borrowing.borrowingID, borrowDate, dueDate, returnDate, resourceID "
				+ "from overdue_transaction,borrowing \n"
				+ " where overdue_transaction.borrowingID = borrowing.borrowingID) as W\n" + "on T.transID=W.transID \n"
				+ "where T.username = '" + this.username + "' order by date;";
		ResultSet r = a.nonStaticGet(statement);

		// Generate string.
		while (r.next()) {
			// If a transaction is a fine.
			if (r.getString("borrowingID") != null) {
				Borrowing b = new Borrowing(r.getInt("borrowingID"));
				int days = b.getOverdueDay();
				history = history + String.format("%20s %20o %20s %20o\n",
						r.getDate("date").toString(), r.getInt("amount"), r.getString("resourceID"), days);
			} else {
				//If transaction is not a fine.
				history = history 
						+ String.format("%20s %20o\n", r.getDate("date"), r.getInt("amount"));
			}
		}
		r.close();

		return history;

	}

	/**
	 * Method to get the currently borrowed items.
	 * 
	 * @return The currently borrowed items.
	 */
	public String getBorrowedList() {
		String history = String.format("%20s %20s \n", "ResourceID","Duedate");
		for (int i = 0; i < currentBorrowHistory.size(); i++) {
			history = history + currentBorrowHistory.get(i).getResourceID()
					+ currentBorrowHistory.get(i).getOverdueDate()+"\n";
		}
		return history;
	}
	
	/**
	 * Method to get the requested items.
	 * 
	 * @return The requested items.
	 */
	public String getRequestedItem() {
		String history = "ResourceID	\n";
		for (int i = 0; i < requesting.size(); i++) {
			history = history + requesting.get(i)+"\n";
		}
		return history;
	}
	
	/**
	 * Method to get the reserved items.
	 * 
	 * @return The reserved items.
	 */
	public String getReservedItem() {
		String history = "ResourceID	\n";
		for (int i = 0; i < requesting.size(); i++) {
			history = history + reserved.get(i)+"\n";
		}
		return history;
	}

	/**
	 * Method to get the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub
	}

}
