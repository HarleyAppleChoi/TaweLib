package user;

import java.awt.Image;

public class librarian extends user {
	
	protected int staffNo;
	protected int employmentDate;
	
	librarian(int staffNo, int employmentDaye, String username, String firstName,  String lastName,  int mobileNo, Image userImage) 
	
	{
	super(username, firstName,username, firstName,  lastName,  mobileNo, userImage);
	
	  this.staffNo = staffNo;
 	  this.employmentDate = employmentDate;
	}
	
		
	protected int getStaffNo() {
		return staffNo;
	}
	
	protected int getEmploymentDate() {
		return employmentDate;
	}
	
	protected void setStaffNo(int l) {
		staffNo = l;
	}
	
	protected void setEmploymentDat(int l) {
		employmentDate = l;
	}
	
	


}
