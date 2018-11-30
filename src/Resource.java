
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.sun.prism.Image;

public class Resource implements Storable{
	protected final int ID;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected int numCopies;
	protected boolean canBorrow;
	protected int numAvailableCopies;
	protected LinkedList<Borrowing> currentBorrow;
	protected LinkedList<String> request;
	protected LinkedList<String> reserve;
	//sql statement
	private String statement;

	public Resource(int resourceId) throws Exception {
		ID = resourceId;
		statement = "select borrowingID from currentBorrowHis where resourceID = '" + resourceId + "';";
		ResultSet r = SQLHandle
				.get(statement);
		while (r.next()) {
			Borrowing e = new Borrowing(r.getInt("borrowingID"));
			currentBorrow.add(e);
		}
		// requesting queue
		statement="select username from requested where resourceID = '" + ID + "';";
		r = SQLHandle.get(statement);
		while (r.next()) {
			request.add((r.getString("username")));
		}
		// reserved queue
		statement="select username from reserved where resourceID = '" + ID + "';";
		r = SQLHandle.get(statement);
		while (r.next()) {
			reserve.add((r.getString("username")));
		}
	}

	/**
	 * If someone reserved resources, that is not able to borrow.
	 * 
	 * @return
	 */
	private boolean canBorrow() {
		boolean b = true;
		if (currentBorrow.size() + reserve.size() >= numCopies) {
			b = false;
		}
		return b;
	}

	/**
	 * 
	 * @param userName
	 * @return the Borrowing object of that specific borrow.
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
	public Borrowing borrow(String userName) throws IllegalArgumentException, SQLException {
		if (!canBorrow()) {
			throw new IllegalArgumentException("You cannot borrow. Try request it.");
		} else if (isReserved(userName) != -1) {
			reserve.remove(isReserved(userName));
		}
		Borrowing b = new Borrowing(String.valueOf(ID));
		currentBorrow.add(b);
		statement="insert  into current_borrow_history values (" 
				+ this.ID + ","+b.getBorrowNo() +");";
		SQLHandle.set(statement);
		return b;
	}

	/**
	 * Find out if the user is already reserved the item
	 * 
	 * @param user
	 * @return Index of the reserve.-1 if the user do not reserve the item. 
	 */
	private int isReserved(String user) {
		int i = 0;
		for (i = 0; i <= reserve.size(); i++) {
			if (i==reserve.size()) {
				i=-1;
			}else if (reserve.get(i) == user) {
				break;
			}
		}
		return i;
	}

	public int getId() {
		return ID;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Image getThumbNailImage() {
		return thumbNailImage;
	}

	public void setThumbNailImage(Image thumbNailImage) {
		this.thumbNailImage = thumbNailImage;
	}

	public int getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	public int getNumAvailableCopies() {
		return numAvailableCopies;
	}

	public void setNumAvailableCopies(int numAvailableCopies) {
		this.numAvailableCopies = numAvailableCopies;
	}

	public static boolean resourceAvailable(boolean canBorrow, int id) {

		String query = "select numAvCopies from resource where id = '" + id + "';";
		if (query == "0") {
			canBorrow = false;
		} else {
			canBorrow = true;
		}
		return canBorrow;
	}

	@Override
	public void store() throws SQLException {
			
	}
}
