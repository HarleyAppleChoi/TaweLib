import java.sql.Date;

public class Borrowing {
	
	private Date initialDate;
	private Date endDate;
	private Date returnDate;
	private int borrowNo;
	private normalUser user;
	private int ResourceID;
	
	Borrowing(Date initial, int ResourceID){
		initialDate = initial;
		this.ResourceID = ResourceID;
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
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
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
		return borrowNo;
	}
	public void setBorrowNo(int borrowNo) {
		this.borrowNo = borrowNo;
	}
	public normalUser getUser() {
		return user;
	}
	public void setUser(normalUser user) {
		this.user = user;
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
	
	
	
	
}
