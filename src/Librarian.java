/**
 * librarian.java
 * @author 901526( Jwana Abdalah), Modified by Hau Yi Choi.
 * CSC-230 Assignment 2 
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.image.Image;

public class Librarian extends User  {
   /**
    * Librarian is a class contains the methods and attributes used by library staff
    * Also permit them manage the user,resource and borrowing of books
    * 
    */
	
	protected int staffNo;
	protected int employmentDate;
	
	/**
	 * constructor 
	 * @param staffNo stores the staffNo.
	 * @param employmentDate stores the employmentDate.
	 * @param username stores the username 
	 * @param firstName stores first name
	 * @param lastName stores last name
	  *@param mobileNo stores mobile number 
	 * @patram userImage stores the user image
	 */
	
	public Librarian(int staffNo, int employmentDaye, String username, String firstName,  String lastName,  int mobileNo, Image userImage) 
	{
	  super(username, firstName, lastName, mobileNo, userImage);
	}
	
	public Librarian() {
		
	}

	
	/** 
	 * @return staffNo
	 */
	
	private int getStaffNo() {
		return staffNo;
	}
	
	/** 
	 * @return employmentDate
     */
	private int getEmploymentDate() {
		return employmentDate;
	}
	
    /**
     * @param l sets staffNo
     */
   private void setStaffNo(int l) {
		staffNo = l;
	}
	
	 /**
	  * @param l sets employment date
	  * 
	  */
	public void setEmploymentDat(int l) {
		employmentDate = l;
	}
	
   	/**
   	 * method to add resource 
   	 * constructor
	 * @param title
	 * @param year
	 * @param image
	 * @param numCopies
	 * @param duration
	 * @return
	 * @throws SQLException
	 */
	private int addResource(String title,String year, String image, int numCopies, int duration) throws SQLException {
		int id = SQLHandle.get("select max(id) from resource;").getInt(0);
		System.out.println(id);
		
		String query = "insert into resource (id,title,year_,image,numcopies, duration)"
				+ "values ('" + id +"','" + title +"','" + year + "','" + image + "','" + numCopies +"','" + duration +")"  ;		

		SQLHandle.set(query);
		return id;
	}
	/**
	 * method adds book to resourse 
	 * constructor 
	 * @param title
	 * @param year
	 * @param image
	 * @param numCopies
	 * @param duration
	 * @param author
	 * @param publisher
	 * @param genre
	 * @param ISBN
	 * @throws SQLException
	 */
	
	public void addBook(String title,String year, String image, int numCopies, int duration
			,String author, String publisher, String genre, String ISBN ) throws SQLException {
		int id = addResource(title,year, image, numCopies, duration);
		
		String query = "insert into book (id, author,publisher,genre,ISBN.language_)" 
				+ "values(" + id +"','" + author+"','" +publisher;
		
		if (genre != null) {
			
		}
		
	}
     /**

      * method adds book to resource 
      * consttuctor
	  * @param title
	  * @param year
	  * @param image
	  * @param numAvailableCopies
	  * @param duration
	  * @param manufacturer
	  * @param model
	  * @param operationSystem
	  * @throws SQLException
	  */
	public void addLaptop(String title, String year, String image, int numAvailableCopies, int duration,
			String manufacturer, String model, String operationSystem) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);

		String query = "insert into laptop (id, author,publisher,genre,ISBN.language_)" + "values(" + id + "','" + model
				+ "','" + manufacturer;

		if (model != null) {

		}
	}
		/**
      * method adds Dvd to resource 
      * consttuctor
	  * @param title
	  * @param year
	  * @param image
	  * @param numAvailableCopies
	  * @param duration
	  * @param director
	  * @param runtime
	  * @param language
	  * @throws SQLException
	  */
	public  void addDvd(String title, String year, String image, int numAvailableCopies, int duration,
			String director, String language, String runtime) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);

		String query = "insert into laptop (id, author,publisher,genre,ISBN.language_)" + "values(" + id + "','" + director
				+ "','" + runtime;

		if (director != null) {

		}

	}
	

	/**
	 * function for normalUser to borrow any resource
	 * @param resourceId
	 * @param userName
	 * @throws Exception 
	 */
	public void borrow(int resourceId, String userName) throws Exception {
		NormalUser user = new NormalUser(userName);
		user.borrow(resourceId);
		System.out.println("Borrow Success!!!");
	}
	
	public void returnResource(int borrowNo) throws Exception{
		//find the user who borrow the item
		String statement = "";
		statement = "Select distinct username from current_borrowing where borrowingID = '" 
		+ borrowNo + "';";
		ResultSet r =SQLHandle.get(statement);
		String userName = "";
		while(r.next()) {
			userName = r.getString("username");
		}
		
		String resourceID ="";
		statement = "Select resourceID from current_borrow_his where borrowingID = '" 
				+ borrowNo + "';";
		 r =SQLHandle.get(statement);
		while(r.next()) {
			resourceID = r.getString("resourceID");
		}
		Resource resource = new Resource(Integer.parseInt(resourceID));
		NormalUser u = new NormalUser(userName);
		Borrowing b = new Borrowing(borrowNo);
		b.setReturnDate();
		
		//if that is overdue, it reduce the balance and do the transaction history
		if(b.isOverdue()) {
			u.reduceBalance(b.fine());
			statement = "update normal_user set balance = '" + u.getBalance() + "' where username = '" 
					+ userName +"';";
			SQLHandle.set(statement);
			
			
			//insert the fine into transaction related
			//find maximum id
			statement = "select max(transID) from transaction;";
			 r = SQLHandle.get(statement);
			int i = 0;
			while (r.next()) {
				i = r.getInt("max(transID)")+1;
			}
			statement = "insert into transaction values('"+ i+"','"+b.fine()+"');";
			SQLHandle.set(statement);
			statement = "insert into overdue_transaction values('"+ i + "','"+b.getBorrowNo()+"');";
			SQLHandle.set(statement);
			statement = "insert into transaction_his values('"+ u.getUsename()+"','"+i+"');";
			SQLHandle.set(statement);
			
			System.out.println("Fine is reduced from balance which is " + b.fine());
		}
		
		//reserve to the next person in the queue
		resource.reserve();
		
		//writing into database(compulsory)
		statement = "delete from current_borrow_his where borrowingID = '" 
							+ borrowNo +"';";
		SQLHandle.set(statement);
		statement = "delete from current_borrowing where borrowingID = '" + borrowNo
				+"' and userName = '" + userName + "';";
		SQLHandle.set(statement);
		statement = "insert into returned_his values('"+ u.getUsename()+"','"+ b.getBorrowNo() +"');";
		SQLHandle.set(statement);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		statement = "UPDATE borrowing SET onLoan = 'n' , returnDate = '"+dateFormat.format(new Date())+"' WHERE borrowingID =" + borrowNo+";";
		SQLHandle.set(statement);
		System.out.println("return Success!!");
		
		
	}
	
	public void request(int resourceID, String username) throws Exception {
		NormalUser u = new NormalUser(username);
		u.request(resourceID);
	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub
		
	}
}

	

	
	 

