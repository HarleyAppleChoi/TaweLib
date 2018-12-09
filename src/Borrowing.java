import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Hau Yi Choi
 * @version 3.0
 *
 */
public class Borrowing implements Storable {

	private Date INITIAL_DATE;
	private Date endDate;
	private Date returnDate;
	private final int BORROW_NO;
	// private final String USER;
	private String RESOURCE_ID;
	DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	String statement = "";
	SQLHandle sql = new SQLHandle();
	

	/**
	 * Selects all the information about a specific borrowing from the database. 
	 * @param id
	 * @throws Exception
	 */
	public Borrowing(int id) throws Exception {
		statement = "select * from Borrowing where BorrowingID ='" + id + "';";
		ResultSet r = sql.nonStaticGet(statement);
		BORROW_NO = id;
		while (r.next()) {
			INITIAL_DATE = r.getDate("borrowDate");
			endDate = r.getDate("dueDate");
			returnDate = r.getDate("returnDate");
			RESOURCE_ID = Integer.toString(r.getInt("resourceID"));
		}
	}

	/**
	 * Creates a new borrowing.
	 * Finds the maximum BorrowingID from the borrowing table, and adds 1 to it.
	 * sets the initial date to the date it is created, and adds the information to the borrowing table.
	 * @param rID
	 * @throws SQLException
	 */
	// when the borrowing is new created
	public Borrowing(String rID) throws SQLException {
		String statement = "select max(borrowingID) from Borrowing";
		ResultSet r = sql.nonStaticGet(statement);
		int i = 0;
		while (r.next()) {
			i = r.getInt("max(borrowingID)");
		}
		BORROW_NO = i + 1;
		INITIAL_DATE = new Date();
		RESOURCE_ID = rID;
		// USER = uID;
		System.out.println("Borrowing adding...");
		statement = "insert into borrowing values('" + this.BORROW_NO + "','" + dateFormat.format(INITIAL_DATE) + "',"
				+ "null,null,'" + this.RESOURCE_ID + "','y');";
		sql.set(statement);
		System.out.println("Borrowing added");
	}

	/**
	 * Checks if the resource is overdue.
	 * 
	 * @return If the end date is not null and is before the current date, return true, otherwise false.
	 */
	public boolean isOverdue() {
		boolean o = false;
		// current date
		Date d = new Date();
		if (endDate != null) {
			if (endDate.before(d)) {
				o = true;
			}
		}
		return o;
	}

	
	/**
	 * Set method to set the date.
	 * @return current date
	 */
	public void setReturnDate() {
		returnDate = new Date();
	}

	/**
	 * Get method to get the date.
	 * @return returnDate
	 */
	public String getReturnDate() {
		String value = "No return Date";
		if(returnDate!=null) {
			value = dateFormat.format(returnDate);
		}
		return value;

	}
	
	public String getOverdueDate() {
		String value = "No due Date";
		if(returnDate!=null) {
			value = dateFormat.format(endDate);
		}
		return value;
	}

	/**
	 * This method calculates the fine amount.
	 * If the fine is overdue, it find the type of resource that has been borrowed,
	 * If the finePerDay = 2 and the maxFine = 25, the resource is a Book or DVD, otherwise it is a Laptop.
	 * Calculates the fine amount by finding the difference between the end date and the returned date and
	 * converts it into days.
	 * The fine is the number of days * fine per day.
	 * 
	 * @return fine and if the fine amount is greater than the maxFine for that resource, returns the max fine.
	 * @throws SQLException
	 */
	public int fine() throws SQLException {
		int fine = 0;
		if (isOverdue()) {
			// find fine per day
			// see what is the type of the resource. If the SQL return an empty set on the
			// table, that is not that kind of resource.
			int finePerDay = 0;
			int maxFine = 0;
			statement = "select resourceID from (select resourceID from book union all select resourceID from DVD)as T where resourceID ='"
					+ this.RESOURCE_ID+"';";
			ResultSet r = sql.nonStaticGet(statement);
			if (r.next()) {
				//this is book/DVD
				finePerDay = 2;
				maxFine = 25;
			} else {
				//if not Book/DVD then must be a Laptop
				finePerDay = 10;
				maxFine = 100;
			}

			if (endDate != null) {
				if (returnDate.compareTo(endDate) > 0) {
					

					fine = (int) (getOverdueDay() * finePerDay);
					if (fine > maxFine) {
						fine = maxFine;
					}
				}
			}
		}
		return fine;
	}
	
	
	public int getOverdueDay() {
		long diff = returnDate.getTime() - endDate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		return (int)days;
	}

	/**
	 * Get method to get the initial date.
	 * @return INITIAL_DATE
	 */
	public Date getInitialDate() {
		return INITIAL_DATE;
	}

	/**
	 * Get method to get the end date.
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set method to set the end date.
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Set method to set the returnDate to the day when the method was called.
	 * @param returnDate
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = new Date();
	}

	/**
	 * Get method to get the return date
	 * @return
	 */
	public String getReturnDateString() {
		return dateFormat.format(returnDate);

	}

	/**
	 * Get method to get the borrow number.
	 * @return BORROW_NO
	 */
	public int getBorrowNo() {
		return BORROW_NO;
	}
	/*
	 * public normalUser getUser() { return USER; } public void setUser(normalUser
	 * user) { this.USER = user; }
	 */
	/*
	 * public Resource getResourceType() { return resourceType; }
	 */
	/*
	 * public void setResourceType(Resource resourceType) { this.resourceType =
	 * resourceType; }
	 */
	// check if resource available.
	// if true, add borrowid and resourceid to users currently borrowing table.
	// -1 from numAvCopies,

	/**
	 * when that is only an update.
	 * 
	 * @throws SQLException
	 */
	@Override
	public void store() throws SQLException {
		sql.set("insert into borrowing values(" + this.BORROW_NO + "," + this.INITIAL_DATE.toString() + ","
				+ "null," + this.RESOURCE_ID + "," + this.returnDate.toString() + "y");
	}
	
	public String getResourceID() {
		return RESOURCE_ID;
	}

}
