import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;

import javafx.scene.image.Image;

/**
 * NormalUser.java This class contain all the methods that allowing normalUser
 * tp borrow resource, also check thier balance to know if they can bprrow
 * resource.
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
			statement = "select * from reserved_item where username = '" + username + "';";

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
			/*
			 * it can be do nothing when Table 'cw230.resered_item' doesn't exist because
			 * sometime the user dont have any requesting or current borrow or request
			 */
		} catch (SQLException ex) {
			/*
			 * it can be do nothing when Table 'cw230.resered_item' doesn't exist because
			 * sometime the user dont have any requesting or current borrow or request
			 */
		}
	}

	/**
	 * this is boolean function to checke if the user can borrorow any resource. 
	 * if their balabce < 0 they can borrow 
	 * @return b
	 */

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

	/**
	 * function to borrow resource and add it to curentBorrowHistory
	 * 
	 * @param resourceID
	 * @throws Exception
	 *             if the normalUser either get fine or overdue item in thier
	 *             balance
	 */
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

	/**
	 * method to get all user's information. constructor
	 * 
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param userImage
	 */

	public void getUserinfo(String username, String firstName, String lastName, int mobileNo, Image userImage) {
		super.username = username;
		super.firstName = firstName;
		super.lastName = lastName;
		super.mobileNo = mobileNo;
		super.userImage = userImage;
	}

	/**
	 * method to reduce balance
	 * 
	 * @param fine
	 */
	public void reduceBalance(int fine) {
		balance -= fine;
	}

	/**
	 * method to get balance
	 * 
	 * @return balance
	 */
	public int getBalance() {
		return balance;
	}
	

	/**
	 * this method allowes normalUser to request for resource
	 * and add it ti database table
	 * @param resourceID
	 * @throws Exception
	 */
	public void request(int resourceID) throws Exception {
		Resource r = new Resource(resourceID);
		statement = "insert into request_item value('" + this.username + "','" + r.getId() + "');";
		SQLHandle.set(statement);
		r.request(this.username);
	}

	public String transactionHistory() throws Exception {
		SQLHandle a = new SQLHandle();
		String history = "Date&Time	Amount	ResourceID		numOfOverdueDays\n";
		String statement = "select T.transID, T.amount, T.date,W.resourceID, W.borrowingID  from \n"
				+ "(select transaction.transID,amount,date,username from transaction,transaction_his \n"
				+ " where transaction.transID=transaction_his.transID) as T \n" + "left outer join \n"
				+ "(select transID,borrowing.borrowingID, borrowDate, dueDate, returnDate, resourceID "
				+ "from overdue_transaction,borrowing \n"
				+ " where overdue_transaction.borrowingID = borrowing.borrowingID) as W\n" + "on T.transID=W.transID \n"
				+ "where T.username = '" + this.username + "';";
		ResultSet r = a.nonStaticGet(statement);

		// generate string
		while (r.next()) {
			// if a transaction is a fine
			if (r.getString("borrowingID")!=null) {
				Borrowing b = new Borrowing(r.getInt("borrowingID"));
				int days = b.getOverdueDay();
				history = history +"Overdue transaction:"+ String.format("%s , %o, %s, %o\n", r.getDate("date").toString(), r.getInt("amount"),
						r.getString("resourceID"), days);
				
			} else {
				//transaction is not a fine
				history = history + "Normal transaction:"+String.format("%s , %o\n", r.getDate("date"), r.getInt("amount"));
			}

		}
		r.close();

		return history;

	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub
	}

}
