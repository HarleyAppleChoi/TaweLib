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
	private String RESOURCE_ID;
	DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	String statement = "";
	SQLHandle sql = new SQLHandle();

	/**
	 * Selects all the information about a specific borrowing event from the database. 
	 * @param id The unique identifier of the borrowing event.
	 * @throws Exception
	 */
	public Borrowing(int id) throws Exception {
		statement = "select * from Borrowing where BorrowingID ='" + id + "';";
		ResultSet r = sql.nonStaticGet(statement);
		//Get all the data associated with the borrowing event from the database.
		BORROW_NO = id;
		while (r.next()) {
			INITIAL_DATE = r.getDate("borrowDate");
			endDate = r.getDate("dueDate");
			returnDate = r.getDate("returnDate");
			RESOURCE_ID = Integer.toString(r.getInt("resourceID"));
		}
	}

	/**
	 * Creates a new borrowing event.
	 * Finds the maximum BorrowingID from the borrowing table, and increases it by 1 to ensure unique borrowing ID.
	 * Sets the initial date to the date it is created, and adds the information to the borrowing table.
	 * @param rID ID of the resource being borrowed.
	 * @throws SQLException
	 */
	public Borrowing(String rID) throws SQLException {
		String statement = "select max(borrowingID) from Borrowing";
		ResultSet r = sql.nonStaticGet(statement);
		int i = 0;
		//Increase borrowingID by 1 to ensure unique ID
		while (r.next()) {
			i = r.getInt("max(borrowingID)");
		}
		BORROW_NO = i + 1;
		INITIAL_DATE = new Date();
		RESOURCE_ID = rID;
		System.out.println("Borrowing adding...");
		statement = "insert into borrowing values('" + this.BORROW_NO + "','" + dateFormat.format(INITIAL_DATE) + "',"
				+ "null,null,'" + this.RESOURCE_ID + "','y');";
		SQLHandle.set(statement);
		System.out.println("Borrowing added");
	}

	/**
	 * Method to check if the resource is overdue.
	 * 
	 * @return o Returns true if the resource is overdue, false if it's not overdue.
	 */
	public boolean isOverdue() {
		boolean o = false;
		// Current date
		Date d = new Date();
		if (endDate != null) {
			if (endDate.before(d)) {
				o = true;
			}
		}
		return o;
	}

	/**
	 * Method to set the date.
	 * @return The current date.
	 */
	public void setReturnDate() {
		returnDate = new Date();
	}

	/**
	 * Method to get the date.
	 * @return value The date the item was returned.
	 */
	public String getReturnDate() {
		String value = "No return Date";
		if(returnDate!=null) {
			value = dateFormat.format(returnDate);
		}
		return value;

	}
	
	/**
	 * Method to get the date when the item is due to be returned.
	 * @return value The date when the item is due to be returned.
	 */
	public String getOverdueDate() {
		String value = "No due Date";
		if(returnDate!=null) {
			value = dateFormat.format(endDate);
		}
		return value;
	}

	/**
	 * This method calculates the amount of fine to be paid for returning an item late.
	 * If the item is overdue, it finds the type of resource that has been borrowed,
	 * calculates the fine amount by finding the difference between the end date and the returned date and
	 * converts it into days.
	 * The fine is the number of days multiplied by fine per day.
	 * 
	 * @return fine The amount of fine to be paid, or if it's greater than the maximum fine, it returns the maximum fine.
	 * @throws SQLException
	 */
	public int fine() throws SQLException {
		int fine = 0;
		if (isOverdue()) {
			//Find fine per day and identify the type of the resource. 
			//If the SQL return an empty set on the table, it is not that kind of resource.
			int finePerDay = 0;
			int maxFine = 0;
			statement = "select resourceID from (select resourceID from book union all select resourceID from DVD)as T where resourceID ='"
					+ this.RESOURCE_ID+"';";
			ResultSet r = sql.nonStaticGet(statement);
			if (r.next()) {
				//This is book/DVD
				finePerDay = 2;
				maxFine = 25;
			} else {
				//If not Book/DVD then must be a Laptop
				finePerDay = 10;
				maxFine = 100;
			}
			//If the date when the resource was returned is after the due date.
			if (endDate != null) {
				if (returnDate.compareTo(endDate) > 0) {
					//Calculate the fine by multiplying the days passed after the due date by the daily fine 
					//associated with that certain type of resource.
					//Use maximum fine if calculated fine is greater than maximum fine.
					fine = (int) (getOverdueDay() * finePerDay);
					if (fine > maxFine) {
						fine = maxFine;
					}
				}
			}
		}
		return fine;
	}
	
	/**
	 * Method to get the days passed since the item was due to be returned.
	 * @return days The number of days passed since the item was due to be returned.
	 */
	public int getOverdueDay() {
		//The day the item was returned minus the date it was due.
		long diff = returnDate.getTime() - endDate.getTime();
		//Converted from milliseconds to days.
		long days = diff / (1000 * 60 * 60 * 24);
		return (int)days;
	}

	/**
	 * Method to get the initial date.
	 * @return INITIAL_DATE The initial date.
	 */
	public Date getInitialDate() {
		return INITIAL_DATE;
	}

	/**
	 * Method to get the date the item is due to be returned.
	 * @return endDate The date the item is due to be returned.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Method to set the date the item is due to be returned.
	 * @param endDate The date the item is due to be returned.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Method to set the date the item was returned. Automatically sets this date to the day when the method is called.
	 * @param returnDate The date the item was returned.
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = new Date();
	}

	/**
	 * Method to get the date the item was returned.
	 * @return returnDate The date the item was returned converted into string.
	 */
	public String getReturnDateString() {
		return dateFormat.format(returnDate);

	}

	/**
	 * Method to get the borrowing event's unique identifier.
	 * @return BORROW_NO The borrowing event's unique identifier.
	 */
	public int getBorrowNo() {
		return BORROW_NO;
	}

	/**
	 * Method for updating an already existing borrowing event.
	 * 
	 * @throws SQLException
	 */
	@Override
	public void store() throws SQLException {
		SQLHandle.set("insert into borrowing values(" + this.BORROW_NO + "," + this.INITIAL_DATE.toString() + ","
				+ "null," + this.RESOURCE_ID + "," + this.returnDate.toString() + "y");
	}
	
	/**
	 * Method to get the unique identifier of the resource.
	 * @return RESOURCE_ID The unique identifier of the resource.
	 */
	public String getResourceID() {
		return RESOURCE_ID;
	}

}
