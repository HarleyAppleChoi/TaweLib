import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrowing implements Storable {
	
	private final Date INITIAL_DATE;
	private Date endDate;
	private Date returnDate;
	private final int BORROW_NO;
	private final NormalUser USER;
	private final int RESOURCE_ID;
	
	//when the borrowing is in database
	public Borrowing(int id) throws Exception{
		ResultSet r = SQLHandle.get("select * from Borrowing where BorrowingID ='" + id +"';");
		BORROW_NO = id;
		
			INITIAL_DATE = r.getDate("initialDate");
			endDate = r.getDate("endDate");
			returnDate = r.getDate("returnDate");
			RESOURCE_ID = r.getInt("resourceID");
		
	}
	
	
	//when the borrowing is new created
	public Borrowing(int rID){
		BORROWING_NO = SQLHandle.get("select max(borrowingID) from Borrowing").getInt("max(borrowingID)") +1;
		INITIAL_DATE = new Date();
		RESOURCE_ID = rID;
		SQLHandle.set("insert into borrowing values(" +this.BORROW_NO+","+ this.INITIAL_DATE.toString()+","
				+"null,"+this.RESOURCE_ID+",null,y");
	}
	
	public void setendDate() {
		
	}
	
	public boolean isOverdue() {
		boolean o = false;
		//current date
		Date d = new Date();
		if (endDate.before(d)) {
			o = true;
		}
		return o;
	}
	
	
	public Date getInitialDate() {
		return INITIAL_DATE;
	}
	public void setInitialDate(Date initialDate) {
		this.INITIAL_DATE = initialDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getBorrowNo() {
		return BORROW_NO;
	}
	public void setBorrowNo(int borrowNo) {
		this.BORROW_NO = borrowNo;
	}
	public normalUser getUser() {
		return USER;
	}
	public void setUser(normalUser user) {
		this.USER = user;
	}
	public Resource getResourceType() {
		return resourceType;
	}
	public void setResourceType(Resource resourceType) {
		this.resourceType = resourceType;
	}
	//check if resource available.
	//if true, add borrowid and resourceid to users currently borrowing table.
	//         -1 from numAvCopies, 
	
	
	public void borrowResource(Resource resourceType, normalUser user) {
		int resourceID = resourceType.getId();
		
		String query = "select numAvCopies from resource where id = '" + resourceID + "';";
		
		if (query == "0") {
			
			
		 } else {
			
			 
		 }
	}

	/**
	 * when that is only a update.
	 * @throws SQLException
	 */
	@Override
	public void store() throws SQLException {
		SQLHandle.set("insert into borrowing values(" +this.BORROW_NO+","+ this.INITIAL_DATE.toString()+","
				+"null,"+this.RESOURCE_ID+","+this.returnDate.toString()+"y");
	}
	
	
	
	
}
