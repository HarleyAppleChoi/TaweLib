/**
 * librarian.java
 * @author 901526( Jwana Abdalah), Modified by Hau Yi Choi.
 * CSC-230 Assignment 2 
 */


import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class Librarian extends User {
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
	  super(String username, String firstName,  String lastName,  int mobileNo, Image userImage)
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
	public void addLaptop((String title, String year, String image, int numAvailableCopies, int duration,
			String manufacturer, String model, String operationSystem) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);

		String query = "insert into laptop (id, author,publisher,genre,ISBN.language_)" + "values(" + id + "','" + model
				+ "','" + manufacturer;

		if (model != null) {

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
	public  void addDvd((String title, String year, String image, int numAvailableCopies, int duration,
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
	 */
	public void borrow(int resourceId, String userName) {
		NormalUser user = new NormalUser(userName);
		
		
	}
}

	

	
	 

