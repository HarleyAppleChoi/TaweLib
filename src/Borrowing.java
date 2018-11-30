import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Borrowing implements Storable {

	private Date INITIAL_DATE;
	private Date endDate;
	private Date returnDate;
	private final int BORROW_NO;
	// private final String USER;
	private String RESOURCE_ID;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	// when the borrowing is in database
	public Borrowing(int id) throws Exception {
		String statement = "select * from Borrowing where BorrowingID ='" + id + "';";
		ResultSet r = SQLHandle.get(statement);
		BORROW_NO = id;
		while (r.next()) {
			INITIAL_DATE = r.getDate("borrowDate");
			endDate = r.getDate("dueDate");
			returnDate = r.getDate("return_date");
			RESOURCE_ID = Integer.toString(r.getInt("resourceID"));
		}
	}

	// when the borrowing is new created
	public Borrowing(String rID) throws SQLException {
		String statement = "select max(borrowingID) from Borrowing";
		ResultSet r = SQLHandle.get(statement);
		int i = 0;
		while (r.next()) {
			i = r.getInt("max(borrowingID)");
		}
		BORROW_NO = i + 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		INITIAL_DATE = new Date();
		RESOURCE_ID = rID;
		// USER = uID;
		System.out.println("Borrowing adding...");
		statement = "insert into borrowing values('" + this.BORROW_NO + "','" + dateFormat.format(INITIAL_DATE) + "',"
				+ "null," + this.RESOURCE_ID + ",null,'y');";
		SQLHandle.set(statement);
		System.out.println("Borrowing added");
	}

	public void setendDate() {

	}

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
	
	public void setReturnDate() {
		returnDate = new Date();
	}
	
	public String getReturnDate() {
		return dateFormat.format(returnDate);

	}
	
	public int fine() throws SQLException {
		int fine = 0;
		if (isOverdue()) {
			//find fine per day
			//String statement = "select distinct resourceID from current_borrow_his outer join book where borrowingID = '"+BORROW_NO+"';";
			//ResultSet R = SQLHandle.get(statement);
			
			int finePerDay = 2;
			
			if(endDate!=null) {
				if(returnDate.compareTo(endDate)>0){
					long diff = returnDate.getTime() - endDate.getTime();
					long days = diff / (1000 * 60 * 60 * 24);
					
					fine = (int) (days * 2);
				}
			}
		}
		return fine;
	}

	public Date getInitialDate() {
		return INITIAL_DATE;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

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
	 * when that is only a update.
	 * 
	 * @throws SQLException
	 */
	@Override
	public void store() throws SQLException {
		SQLHandle.set("insert into borrowing values(" + this.BORROW_NO + "," + this.INITIAL_DATE.toString() + ","
				+ "null," + this.RESOURCE_ID + "," + this.returnDate.toString() + "y");
	}

}
