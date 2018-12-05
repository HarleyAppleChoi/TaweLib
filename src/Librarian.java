import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.image.Image;

 /**
    * Librarian is a class contains the methods and attributes used by library staff
    * Also permit them manage the user,resource and borrowing of books
    * @author Jwana Abdalah
    * @modified by Hau Yi Choi
    */
public class Librarian extends User  {
  
	
	protected int staffNo;
	protected int employmentDate;
	
	/**
	 * constructor to construct a Librarian.
	 * @param staffNo stores the staffNo.
	 * @param employmentDate stores the employmentDate.
	 * @param username stores the username 
	 * @param firstName stores first name
	 * @param lastName stores last name
	  *@param mobileNo stores mobile number 
	 * @param userImage stores the user image
	 */
	
	public Librarian(int staffNo, int employmentDaye, String username, String firstName,  String lastName,  int mobileNo, Image userImage) 
	{
	  super(username, firstName, lastName, mobileNo, userImage);
	  this.staffNo = staffNo;
	  this.employmentDate = employmentDate;
	}
	
	/**
	 * Empty constructor.
	*/
	public Librarian() {
		
	}

	
	/** 
	 * Get method to get the staffNo.
	 * @return staffNo
	 */
	
	private int getStaffNo() {
		return staffNo;
	}
	/**
	 * Set method to set the staffNo.
	 * @pram staffNo
	*/
	private int setStaffNo(int staffNo) {
	    this.staffNo = staffNo;
	}
	
	/** 
	 * Get method to get the employmentDate.
	 * @return employmentDate
     */
	private int getEmploymentDate() {
		return employmentDate;
	}
	
	
	 /**
	  * Set method to set the employmentDate.
	  * @param l sets employment date
	  * 
	  */
	public void setEmploymentDate(int employmentDate) {
		this.employmentDate = employmentDate;
	}
	
   	/**
   	 * This method allows a Librarian to add a new resource to the database.
	 * @param title
	 * @param year
	 * @param image
	 * @param numCopies
	 * @param duration
	 * @return unique id of new resource
	 * @throws SQLException
	 */
	private int addResource(String title, String year, String image, int numCopies, int duration) throws SQLException {
	    //selects max id from resource
		int id = SQLHandle.get("select max(id) from resource;").getInt(0);
		System.out.println(id);
		
		//SQL query to add a new resource with the inputed values.
		String query = "insert into resource (id,title,year_,image,numcopies, duration)"
				+ "values ('" + id +"','" + title +"','" + year + "','" + image + "','" + numCopies +"','" + duration +"')"  ;		

		SQLHandle.set(query);
		return id;
	}
	
	/**
	 * This method allows a Librarian to add a Book to the database.
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
		int id = addResource(title, year, image, numCopies, duration);
		
		String gen = "null";
		if(!genre.isEmpty()) {
			gen = genre;
		}
		
		String isbn = "null";
		if(!ISBN.isEmpty()) {
			isbn = ISBN;
		}
		
		String lang = "null";
		if(!language.isEmpty()) {
			lan = langauge;
		}
		
		//SQL query to add a Book to the Database with the entered values.
		String query = "insert into book (id, author, publisher, genre, ISBN, language)" 
				+ "values('" + id +"','" + author + "','" + publisher + genre +"','"  + ISBN +"','"  + language +"');";
		SQLHandle.set(query);
	}
	
     /**
      * This method allows a Librarian to add a laptop to Database.
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
			String manufacturer, String model, String operatingSystem) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);

//SQL query to add a new laptop and its values to the database.
		String query = "insert into laptop (id, manufacturer, model, operatingSystem)" + "values('" + id + "','" + model
				+ "','" + manufacturer + "','" + operatingSystem + "');";
		SQLHandle.set(query);
	}
	
	
     /**
      * This method allow a Librarian to add a new DVD to the database.
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
			String director, String language, String runtime, String[] subtitle) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);
		
		String lang = "null";
		if(!language.isEmpty()) {
			lang = language;
		}

        //SQL statement to add the DVD and it's values to te database.
		String query = "insert into DVD (id, director, runtime, language)" + "values('" + id + "','" + director
				+ "','" + runtime +  "','" + language +  "');";
		SQLHandle.set(query);
        
        // If there are subtitle languages, adds each of them to the
        // DVD_subtitle table along with the corresponding resourceID.
        String subt = "null";
		if(!subtitle.isEmpty()) {
		    
			while(subLanguages.hasNext()) {
	            sublang = sublanguages.readNext();
	            //SQl query to add the subtitle language to the database.
                String query = "insert into DVD_subtitle (id, subtitle)" + "values('" + id + "','" + sublang + "');";
		        SQLHandle.set(query);
            }
		}
	}
	

	/**
	 * This method allows the NormalUser to borrow a resource through the Librarian.
	 * @param resourceId
	 * @param userName
	 * @throws Exception 
	 */
	public void borrow(int resourceId, String userName) throws Exception {
		NormalUser user = new NormalUser(userName);
		user.borrow(resourceId);
		System.out.println("Successfully Borrowed Resource.");
	}
	
	/**
	 * This method allows a NormalUser to return a Resource through the librarian.
	 * Removes the user from the current_borrowing table and move them to the current_borrowing_his table.
	 * If the resource is overdue, it calculates the fine for that user.
	*/
	public void returnResource(int borrowNo) throws Exception {
	    
		//SQL statement to find the username who borrowed the resource
		String statement = "";
		statement = "Select distinct username from current_borrowing where borrowingID = '" 
		+ borrowNo + "';";
		ResultSet r =SQLHandle.get(statement);
		String userName = "";
		while(r.next()) {
			userName = r.getString("username");
		}
		
		//SQL statement to find the resourceID of the borrowed resource
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
		
		//If the resource is overdue, it reduced the balance of the User by the amount calculated
		if(b.isOverdue()) {
			u.reduceBalance(b.fine());
			statement = "update normal_user set balance = '" + u.getBalance() + "' where username = '" 
					+ userName +"';";
			SQLHandle.set(statement);
			
			
			//insert the fine into transaction related
			statement = "select max(transID) from transaction;";
			 r = SQLHandle.get(statement);
			int i = 0;
			//finds the max transID
			while (r.next()) {
				i = r.getInt("max(transID)")+1;
			}
			//adds the fine information into the transaction table, the overdue_transaction
			//table and the transaction_his table
			statement = "insert into transaction values('"+ i+"','"+b.fine()+"');";
			SQLHandle.set(statement);
			statement = "insert into overdue_transaction values('"+ i + "','"+b.getBorrowNo()+"');";
			SQLHandle.set(statement);
			statement = "insert into transaction_his values('"+ u.getUsename()+"','"+i+"');";
			SQLHandle.set(statement);
			
			System.out.println("Fine is reduced from balance which is " + b.fine());
		}
		
		//reserve the resource for the next User in it's requesting queue
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
		System.out.println("Successfully returned Resource.");
		
		
	}
	
	public void payFine(int amount, String username) throws Exception {
		NormalUser u= new NormalUser(username);
		
		int newBalance = u.getBalance()+amount;
		String statement = "Update normal_user set balance = "+ newBalance+";";
		SQLHandle.set(statement);
	}
	
	public void request(int resourceID, String username) throws Exception {
		NormalUser u = new NormalUser(username);
		u.request(resourceID);
	}
	
	
	private void newUser(String username,
			String password,
			String firstname,
			String lastname,
			int mobileNo,
			String address,
			String image) throws SQLException {
		checkName(username);
		String statement = "INSERT INTO `user_`(`username`, `Password`, `firstname`, `lastname`, `mobileNo`, `address`, `image`)"
				+ " VALUES ('"+username+"','"+ password.hashCode() +"','"+firstname+"','"+lastname+"','"+mobileNo +"','"+address+"','"+image+"');";
		SQLHandle.set(statement);
		
	}
	
	public void newNormalUser(
			String username,
			String password,
			String firstname,
			String lastname,
			int mobileNo,
			String address,
			String image) throws SQLException {
		newUser(username,password,firstname,lastname,mobileNo,address,image);
		String statement = "INSERT INTO `normal_user`(`username`, `balance`) VALUES ('"+username+"','"+0+"');";
		SQLHandle.set(statement);
	}

	
	public void newLibrarian(
			String username,
			String password,
			String firstname,
			String lastname,
			int mobileNo,
			String address,
			String image,
			String employmentDate) throws SQLException {
		newUser(username,password,firstname,lastname,mobileNo,address,image);
		
		//get new StaffNo
		String statement = "select max(staffNo) from librarian;";
		ResultSet r = SQLHandle.get(statement);
		r.next();
		int sNo = r.getInt("max(staffNo)") +1;
	
		statement = "INSERT INTO `librarian`(`username`, `employmentDate`, `staffNo`) "
				+ "VALUES ('"+username+"','"+employmentDate+"',"+sNo+")";
		SQLHandle.set(statement);
	}
	
	private void checkName(String username) throws SQLException,IllegalArgumentException {
		String statement = "select username from user_ where username = '"+username+"';";
		ResultSet r = SQLHandle.get(statement);
		//if the set is not empty, means that is already userd
		if (r.next()==true) { 
			throw new IllegalArgumentException("Name already used");
		}
	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub
		
	}
}

	

	
	 

