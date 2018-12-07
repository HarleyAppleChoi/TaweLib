import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;

/**
 * Librarian is a class contains the methods and attributes used by library
 * staff Also permit them manage the user,resource and borrowing of books
 * 
 * @author Jwana Abdalah
 * @modified by Hau Yi Choi
 */
public class Librarian extends User {

	protected int staffNo;
	protected int employmentDate;

	/**
	 * constructor to construct a Librarian.
	 * 
	 * @param staffNo
	 *            stores the staffNo.
	 * @param employmentDate
	 *            stores the employmentDate.
	 * @param username
	 *            stores the username
	 * @param firstName
	 *            stores first name
	 * @param lastName
	 *            stores last name
	 * @param mobileNo
	 *            stores mobile number
	 * @param userImage
	 *            stores the user image
	 */

	public Librarian(int staffNo,String password ,int employmentDaye, String username, String firstName, String lastName, int mobileNo,
			Image userImage) {
		super(username, password, lastName, lastName, mobileNo, userImage);
		this.staffNo = staffNo;
		this.employmentDate = employmentDate;
	}

	/**
	 * Empty constructor.
	 */
	public Librarian() {

	}

	/**
	 * Get method to get the employmentDate.
	 * 
	 * @return employmentDate
	 */
	private int getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * Set method to set the employmentDate.
	 * 
	 * @param l
	 *            sets employment date
	 * 
	 */
	public void setEmploymentDate(int employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * This method allows a Librarian to add a new resource to the database.
	 * 
	 * @param title
	 * @param year
	 * @param image
	 * @param numCopies
	 * @param duration
	 * @return unique id of new resource
	 * @throws SQLException
	 */
	private int addResource(String title, String year, String image, int numCopies, int duration) throws SQLException {
		// selects max id from resource
		
		
		ResultSet r = SQLHandle.get("select max(resourceID) from resource;");
		r.next();
		int id = r.getInt("max(resourceID)")+1;
		
		System.out.println(id);

		// SQL query to add a new resource with the inputed values.
		String query = "insert into resource (resourceID,title,year,image,numAvcopies, duration)" + "values ('" + id
				+ "','" + title + "','" + year + "','" + image + "','" + numCopies + "','" + duration + "')";

		SQLHandle.set(query);
		return id;
	}

	/**
	 * This method allows a Librarian to add a Book to the database.
	 * 
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

	public void addBook(String title, String year, String image, int numCopies, int duration, String author,
			String publisher, String genre, String ISBN, String language) throws SQLException {
		int id = addResource(title, year, image, numCopies, duration);

		String gen = "null";
		if (!genre.isEmpty()) {
			gen = "'" + genre + "'";
		}

		String isbn = "null";
		if (!ISBN.isEmpty()) {
			isbn = "'" + ISBN + "'";
			;
		}

		String lang = "null";
		if (!language.isEmpty()) {
			lang = "'" + language + "'";
		}

		// SQL query to add a Book to the Database with the entered values.
		String query = "insert into book (resourceID, author, publisher, genre, ISBN, language)" + "values('" + id
				+ "','" + author + "','" + publisher + "'," + gen + "," + isbn + "," + lang + ");";
		SQLHandle.set(query);
	}

	/**
	 * This method allow a Librarian to add a new DVD to the database.
	 * 
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
	public void addDvd(String title, String year, String image, int numAvailableCopies, int duration, String director,
			String language, String runtime, String subtitle) throws SQLException {
		int id = addResource(title, year, image, numAvailableCopies, duration);

		String lang = "null";
		if (!language.isEmpty()) {
			lang = language;
		}

		// SQL statement to add the DVD and it's values to the database.
		String query = "insert into DVD (resourceID, director, runtime, language)" + "values('" + id + "','" + director
				+ "','" + runtime + "','" + language + "');";
		SQLHandle.set(query);

		// If there are subtitle languages, adds each of them to the
		// DVD_subtitle table along with the corresponding resourceID.
		if (subtitle.length() != 0) {
			String[] subtitles = subtitle.split("\\s*,\\s*");
			List<String> subtitleLang = new ArrayList<String>(Arrays.asList(subtitles));

			for (int i = 0; i < subtitleLang.size(); i++) {
				String subLang = subtitleLang.get(i);
				String query2 = "insert into DVD_subtitle (resourceID, subtitle)" + "values('" + id + "','" + subLang
						+ "');";
				SQLHandle.set(query2);
			}
		}
	}

	/**
	 * Show each borrowtime , returntime, user involved on pacticular resources.
	 * 
	 * @param resourceID
	 * @return
	 * @throws SQLException
	 */
	public String getBorrowingHistory(int resourceID) throws SQLException {
		String query = "select borrowing.borrowDate, borrowing.returnDate, T.username \n" + "from borrowing,\n"
				+ "((select * from returned_his) union all (select * from current_borrowing)) as T\n"
				+ "where borrowing.borrowingID = T.borrowingID " + "and resourceID = '" + resourceID
				+ "' order by borrowDate";

		ResultSet r = SQLHandle.get(query);
		String result = String.format("%20s %20s %20s \n", "borrowtime" , "returntime", "user");
		while (r.next()) {
			result += String.format("%20s %20s %20s \n", r.getString("borrowDate") , r.getString("returnDate"), r.getString("username"));
		}
		return result;
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

		// SQL query to add a new laptop and its values to the database.
		String query = "insert into laptop (resourceID, manufacturer, model, operatingSystem)" + "values('" + id + "','"
				+ model + "','" + manufacturer + "','" + operatingSystem + "');";
		SQLHandle.set(query);

	}

	/**
	 * This method allows the NormalUser to borrow a resource through the Librarian.
	 * 
	 * @param resourceId
	 * @param userName
	 * @throws Exception
	 */
	public void borrow(int resourceId, String username) throws Exception {
		NormalUser user = new NormalUser(username);
		user.borrow(resourceId);
		System.out.println("Successfully Borrowed Resource.");
	}

	/**
	 * This method allows a NormalUser to return a Resource through the librarian.
	 * Removes the user from the current_borrowing table and move them to the
	 * current_borrowing_his table. If the resource is overdue, it calculates the
	 * fine for that user.
	 * 
	 * @param borrowNo
	 */
	public void returnResource(int borrowNo) throws Exception {

		// SQL statement to find the username who borrowed the resource
		String statement = "";
		statement = "Select distinct username from current_borrowing where borrowingID = '" + borrowNo + "';";
		ResultSet r = SQLHandle.get(statement);
		String userName = "";
		while (r.next()) {
			userName = r.getString("username");
		}

		// SQL statement to find the resourceID of the borrowed resource
		String resourceID = "";
		statement = "Select resourceID from current_borrow_his where borrowingID = '" + borrowNo + "';";
		r = SQLHandle.get(statement);
		while (r.next()) {
			resourceID = r.getString("resourceID");
		}
		Resource resource = new Resource(Integer.parseInt(resourceID));
		NormalUser u = new NormalUser(userName);
		Borrowing b = new Borrowing(borrowNo);
		b.setReturnDate();

		// If the resource is overdue, it reduced the balance of the User by the amount
		// calculated
		if (b.isOverdue()) {
			u.reduceBalance(b.fine());
			statement = "update normal_user set balance = '" + u.getBalance() + "' where username = '" + userName
					+ "';";
			SQLHandle.set(statement);

			statement = "select max(transID) from transaction;";
			r = SQLHandle.get(statement);
			int i = 0;
			// finds the max transID
			while (r.next()) {
				i = r.getInt("max(transID)") + 1;
			}
			// adds the fine information into the transaction table, the overdue_transaction
			// table and the transaction_his table
			statement = "insert into transaction values('" + i + "','" + b.fine() + "');";
			SQLHandle.set(statement);
			statement = "insert into overdue_transaction values('" + i + "','" + b.getBorrowNo() + "');";
			SQLHandle.set(statement);
			statement = "insert into transaction_his values('" + u.getUsename() + "','" + i + "');";
			SQLHandle.set(statement);

			System.out.println("Fine is reduced from balance which is " + b.fine());
		}

		// reserve the resource for the next User in it's requesting queue
		resource.reserve();

		// Updating the database
		// removing the user from the current_borrow_his table
		statement = "delete from current_borrow_his where borrowingID = '" + borrowNo + "';";
		SQLHandle.set(statement);
		// removing the user from the current_borrowing table
		statement = "delete from current_borrowing where borrowingID = '" + borrowNo + "' and userName = '" + userName
				+ "';";
		SQLHandle.set(statement);
		// adding the user to the returned_his table
		statement = "insert into returned_his values('" + u.getUserName() + "','" + b.getBorrowNo() + "');";
		SQLHandle.set(statement);

		statement = "UPDATE borrowing SET onLoan = 'n' , returnDate = '" + getCurrentDate() + "' WHERE borrowingID ="
				+ borrowNo + ";";
		SQLHandle.set(statement);
		System.out.println("Successfully returned Resource.");
	}

	/**
	 * This method allows a User to pay off their fine by interacting with a
	 * librarian.
	 * 
	 * @param amount
	 * @param username
	 */
	private String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(new Date());
	}

	private String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public void payFine(int amount, String username) throws Exception {
		NormalUser u = new NormalUser(username);

		int newBalance = u.getBalance() + amount;
		// SQL query to update the normal_user table with the new balance amount
		String statement = "Update normal_user set balance = " + newBalance + ";";
		SQLHandle.set(statement);

		statement = "select count(*) from transaction;";
		ResultSet r = SQLHandle.get(statement);
		r.next();
		int sNo = r.getInt("count(*)") + 1;
		statement = "INSERT INTO `transaction`(`transID`, `amount`, `date`) VALUES " + "('" + sNo + "','" + amount
				+ "','" + getCurrentTime() + "'); ";
		SQLHandle.set(statement);

		statement = "INSERT INTO `transaction_his`(`username`, `transID`) VALUES ('" + username + "','" + sNo + "')";
		SQLHandle.set(statement);
		System.out.println("fine paid");
	}

	/**
	 * This method allows a user to request a resource.
	 * 
	 * @param resourceID
	 * @param username
	 */
	public void request(int resourceID, String username) throws Exception {
		NormalUser u = new NormalUser(username);
		u.request(resourceID);
	}

	/**
	 * This method allows a librarian to create a new User. Hashcode is stored in the password field.
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param address
	 * @param image
	 */
	private void newUser(String username, String password, String firstName, String lastName, int mobileNo,
			String address, String image) throws SQLException {

		checkName(username);
		String statement = "INSERT INTO user_(username, Password, firstname, lastname, mobileNo, address, image)"
				+ " VALUES ('" + username + "','" + password.hashCode() + "','" + firstName + "','" + lastName + "','"
				+ mobileNo + "','" + address + "','" + image + "');";
		SQLHandle.set(statement);

	}

	/**
	 * This method allows a librarian to create a new NormalUser.
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param address
	 * @param image
	 * 
	 */
	public void newNormalUser(String username, String password, String firstname, String lastname, int mobileNo,
			String address, String image) throws SQLException {

		// SQL query to add the NormalUser to the database
		newUser(username, password, firstname, lastname, mobileNo, address, image);
		String statement = "INSERT INTO normal_user(username, balance) VALUES ('" + username + "','" + 0 + "');";
		SQLHandle.set(statement);
		System.out.println("NormalUserAdded");
	}

	/**
	 * This method allows a librarian to create a new Librarian.
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param address
	 * @param image
	 * @param employmentDate
	 */
	public void newLibrarian(String username, String password, String firstname, String lastname, int mobileNo,
			String address, String image, String employmentDate) throws SQLException {

		newUser(username, password, firstname, lastname, mobileNo, address, image);

		// get new unique StaffNo
		String statement = "select max(staffNo) from librarian;";
		ResultSet r = SQLHandle.get(statement);
		r.next();
		int sNo = r.getInt("max(staffNo)") + 1;

		// SQL query to add the new Librarian to the database
		statement = "INSERT INTO librarian(username, employmentDate, staffNo) " + "VALUES ('" + username + "','"
				+ employmentDate + "'," + sNo + ")";
		SQLHandle.set(statement);
	}

	/**
	 * This method checks if the entered username is unique and has not been used
	 * before.
	 * 
	 * @param username
	 */
	private void checkName(String username) throws SQLException, IllegalArgumentException {
		String statement = "select username from user_ where username = '" + username + "';";
		ResultSet r = SQLHandle.get(statement);
		// if the set is not empty, means that is already in use
		if (r.next() == true) {
			throw new IllegalArgumentException("Name already being used");
		}
	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub

	}
}
