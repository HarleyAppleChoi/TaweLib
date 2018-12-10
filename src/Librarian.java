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
 * This class contains the methods and attributes used by library staff.
 * to manage the users, resources and the borrowing and returning of resources.
 * 
 * @author Jwana Abdalah
 * @modified by Hau Yi Choi
 * @modified by Eniko Debreczeny
 */
public class Librarian extends User {

	protected int staffNo;
	protected int employmentDate;
	
	/**
	 * Constructor to create a new instance of Librarian.
	 * 
	 * @param staffNo The staff number of the librarian.
	 * @param employmentDate The date the librarian started working.
	 * @param username Username of the librarian.
	 * @param firstName First name of the librarian.
	 * @param lastName Last name of the librarian.
	 * @param mobileNo Mobile number of the librarian
	 * @param address Address of the librarian.
	 * @param userImage Photo of the librarian.
	 */
	public Librarian(int staffNo, int employmentDate, String username, String password, String firstName, String lastName, int mobileNo,
			String address, int userImage) {
		super(username, password, firstName, lastName, mobileNo, address, userImage);
		this.staffNo = staffNo;
		this.employmentDate = employmentDate;
		super.sql = new SQLHandle();
	}

	/**
	 * Empty constructor.
	 */
	public Librarian() {
		super.sql = new SQLHandle();
	}

	/**
	 * Get method to get the employmentDate.
	 * 
	 * @return employmentDate The date the librarian started working.
	 */
	private int getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * Set method to set the employmentDate.
	 * 
	 * @param employment date The date the librarian started working.
	 * 
	 */
	public void setEmploymentDate(int employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * This method allows a Librarian to add a new resource to the database.
	 * 
	 * @param title The title of the resource.
	 * @param year The year the resource was released. 
	 * @param image An image of the resource.
	 * @param numCopies The number of copies of the resource owned by the library.
	 * @param duration The duration of the resource.
	 * @return id Unique identifier of the resource.
	 * @throws SQLException
	 */
	private int addResource(String title, String year, String image, int numCopies, int duration) throws SQLException {
		// Selects maximum id from resource and increases it by one to ensure unique ID.
		ResultSet r = sql.nonStaticGet("select max(resourceID) from resource;");
		r.next();
		int id = r.getInt("max(resourceID)")+1;
		
		System.out.println(id);

		// SQL query to add a new resource with the inpu values.
		String query = "insert into resource (resourceID,title,year,image,numAvcopies, duration)" + "values ('" + id
				+ "','" + title + "','" + year + "','" + image + "','" + numCopies + "','" + duration + "')";

		sql.set(query);
		return id;
	}

	/**
	 * This method allows a Librarian to add a Book to the database.
	 * 
	 * @param title The title of the book.
	 * @param year The year the book was published.
	 * @param thumbNailImage Image of the book cover.
	 * @param numCopies Number of copies owned by the library.
	 * @param duration The duration of the book.
	 * @param author The author of the book.
	 * @param publisher The publisher of the book.
	 * @param genre The genre of the book.
	 * @param isbn the ISBN number of the book.
	 * @throws SQLException
	 */

	public void addBook(String title, String year, String image, int numCopies, int duration, String author,
			String publisher, String genre, String ISBN, String language) throws SQLException {
		int id = addResource(title, year, image, numCopies, duration);
		//It's optional to enter genre, if nothing is entered then leave it empty.
		String gen = "null";
		if (!genre.isEmpty()) {
			gen = "'" + genre + "'";
		}
		//It's optional to enter isbn, if nothing is entered then leave it empty.
		String isbn = "null";
		if (!ISBN.isEmpty()) {
			isbn = "'" + ISBN + "'";
			;
		}
		//It's optional to enter language, if nothing is entered then leave it empty.
		String lang = "null";
		if (!language.isEmpty()) {
			lang = "'" + language + "'";
		}

		// SQL query to add a Book to the Database with the input values.
		String query = "insert into book (resourceID, author, publisher, genre, ISBN, language)" + "values('" + id
				+ "','" + author + "','" + publisher + "'," + gen + "," + isbn + "," + lang + ");";
		sql.set(query);
	}

	/**
	 * This method allows a Librarian to add a new DVD to the database.
	 * 
	 * @param title The title of the DVD.
	 * @param year The year the DVD was released.
	 * @param thumbNailImage Image of the DVD cover.
	 * @param numAvailableCopies The number of copies available to borrow from the library.
	 * @param duration The duration of the DVD.
	 * @param director The director of the contents of the DVD.
	 * @param runtime The runtime of the content of the DVD.
	 * @param language The language of the DVD.
	
	 * @throws SQLException
	 */
	public void addDvd(String title, String year, String image, int numAvCopies, int duration, String director,
			String language, String runtime, String subtitle) throws SQLException {
		int id = addResource(title, year, image, numAvCopies, duration);
		
		////It's optional to enter language, if nothing is entered then leave it empty.
		String lang = "null";
		if (!language.isEmpty()) {
			lang = language;
		}

		// SQL statement to add the DVD and it's values to the database.
		String query = "insert into DVD (resourceID, director, runtime, _language)" + "values('" + id + "','" + director
				+ "','" + runtime + "','" + lang + "');";
		sql.set(query);

		// If there are subtitles available, add each of them to an arraylist 
		// then add them to the DVD_subtitle table along with the corresponding resourceID.
		if (subtitle.length() != 0) {
			String[] subtitles = subtitle.split("\\s*,\\s*");
			List<String> subtitleLang = new ArrayList<String>(Arrays.asList(subtitles));

			for (int i = 0; i < subtitleLang.size(); i++) {
				String subLang = subtitleLang.get(i);
				String query2 = "insert into DVD_subtitle (resourceID, subtitle)" + "values('" + id + "','" + subLang
						+ "');";
				sql.set(query2);
			}
		}
	}

	/**
	 * Show each borrowing event associated with the item, 
	 * including date borrowed, date returned, borrowing user.
	 * 
	 * @param resourceID The ID of the resource.
	 * @return result Every borrowing event associated with the item.
	 * @throws SQLException
	 */
	public String getBorrowingHistory(int resourceID) throws SQLException {
		//SQL query to get date borrowed, date returned, borrowing user from  the database.
		String query = "select borrowing.borrowDate, borrowing.returnDate, T.username \n" + "from borrowing,\n"
				+ "((select * from returned_his) union all (select * from current_borrowing)) as T\n"
				+ "where borrowing.borrowingID = T.borrowingID " + "and resourceID = '" + resourceID
				+ "' order by borrowDate";

		ResultSet r = sql.nonStaticGet(query);
		String result = String.format("%20s %20s %20s \n", "borrowtime" , "returntime", "user");
		while (r.next()) {
			result += String.format("%20s %20s %20s \n", r.getString("borrowDate") , r.getString("returnDate"), r.getString("username"));
		}
		return result;
	}
		
     /**
      * This method allows a Librarian to add a laptop to Database. 
      * @param title The title of the laptop.
	  * @param year The year the laptop was produced.
	  * @param image Image of the laptop.
	  * @param numAvailableCopies Number of copies available to borrow from the library.
	  * @param duration The duartion of the laptop.
	  * @param manufacturer The manufacturer of the laptop.
	  * @param model The model of the laptop.
	  * @param operatingSystem The operating system running on the laptop.
	  * @throws SQLException
	  */
	public void addLaptop(String title, String year, String image, int numAvCopies, int duration,
			String manufacturer, String model, String operatingSystem) throws SQLException {
		int id = addResource(title, year, image, numAvCopies, duration);

		// SQL query to add a new laptop with the input  values to the database.
		String query = "insert into laptop (resourceID, manufacturer, model, operatingSystem)" + "values('" + id + "','"
				+ model + "','" + manufacturer + "','" + operatingSystem + "');";
		sql.set(query);

	}
	
	/**
	 * This method allows the librarian to edit a resource.
	 * @param resourceId The ID of the resource.
	 * @param title the title of the resource.
	 * @param year The year the resource was released.
	 * @param image An image of the resource.
	 * @param numAvCopies The number of copies available to borrow from the library.
	 * @param duration The duration of the resource.
	 * @throws SQLException
	 */
	public void editResource(int resourceId, String title, String year, String image, int numAvCopies, int duration) throws SQLException {
		
		//Edit the title.
		String newTitle = "null";
		if (!title.isEmpty()) {
			newTitle = "UPDATE resource SET title = '" + title + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newTitle);
		}
		
		//Edit the year.
		String newYear = "null";
		if (!year.isEmpty()) {
			newYear = "UPDATE resource SET year = '" + year + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newYear);
		}
		
		//Edit the image.
		String newImage = "null";
		if (!image.isEmpty()) {
			newImage = "UPDATE resource SET image = '" + image + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newImage);
		}
		
		//Edit the available copies.
		String newNumAvCopies = "null";
		if (numAvCopies != 0) {
			newNumAvCopies = "UPDATE resource SET numAvCopies = '" + numAvCopies + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newNumAvCopies);
		}
		
		//Edit the duration.
		String newDuration = "null";
		if (duration != 0) {
			newDuration = "UPDATE resource SET duration = '" + duration + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newDuration);
		}	
	}
	
	/**
	 * This method allows the librarian to edit a book.
	 * @param resourceId The unique ID of the book.
	 * @param title The title of the book.
	 * @param year The year the book was published.
	 * @param image Image of the book cover.
	 * @param numAvCopies  Number of copies available to borrow in the library.
	 * @param duration The duration of the book.
	 * @param author The author of the book.
	 * @param publisher The publisher of the book.
	 * @param genre The genre of the book.
	 * @param isbn the ISBN number of the book.
	 * @param langauge The language of the book.
	 * @throws SQLException
	 */
	public void editBook(int resourceId, String title, String year, String image, int numAvCopies, int duration, String author,
			String publisher, String genre, String ISBN, String language) throws SQLException {
		
		editResource(resourceId, title, year, image, numAvCopies, duration);
		
		//Edit the author.
		String newAuthor = "null";
		if (!author.isEmpty()) {
			newAuthor = "UPDATE book SET author = '" + author + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newAuthor);
		}
		
		//Edit the publisher.
		String newPublisher = "null";
		if (!publisher.isEmpty()) {
			newPublisher = "UPDATE book SET publisher = '" + publisher + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newPublisher);
		}
		
		//Edit the genre.
		String newGenre = "null";
		if (!publisher.isEmpty()) {
			newGenre = "UPDATE book SET publisher = '" + genre + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newGenre);
		}
		
		//Edit the ISBN.
		String newISBN = "null";
		if (!ISBN.isEmpty()) {
			newISBN = "UPDATE book SET ISBN = '" + ISBN + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newISBN);
		}
		
		//Edit the language.
		String newLanguage = "null";
		if (!language.isEmpty()) {
			newLanguage = "UPDATE book SET language = '" + language + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newLanguage);
		}
	}
	
	
	/**
	 * This methods allows the librarian to edit a DVD.
	 * @param resourceId The ID of the DVD.
	 * @param title The title of the DVD.
	 * @param year The year the DVD was released.
	 * @param image Image of the DVD.
	 * @param numAvCopies Number of copies available to borrow from the library.
	 * @param director The director of the contents of the DVD.
	 * @param runtime The runtime of the content of the DVD.
	 * @param language The language of the DVD.
	 * @param subtitle The subtitles available on the DVD.
	 * @throws SQLException
	 */
	public void editDVD(int resourceId, String title, String year, String image, int numAvCopies, int duration, String director,
			String language, String runtime, String subtitle) throws SQLException {
		
		editResource(resourceId, title, year, image, numAvCopies, duration);
		
		//Edit the director.
		String newDirector = "null";
		if (!director.isEmpty()) {
			newDirector = "UPDATE DVD SET director = '" + director + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newDirector);
		}
		
		//Edit the language.
		String newLanguage = "null";
		if (!language.isEmpty()) {
			newLanguage = "UPDATE DVD SET _language = '" + language + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newLanguage);
		}
		
		//Edit the runtime.
		String newRuntime = "null";
		if (!runtime.isEmpty()) {
			newRuntime = "UPDATE DVD SET runtime = '" + runtime + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newRuntime);
		}
		
		//Edit the subtitles.
		if (subtitle.length() != 0) {
			String query = "delete from DVD_subtitle where resourceID = '"+ resourceId +"';";
			sql.set(query);
			String[] subtitles = subtitle.split("\\s*,\\s*");
			List<String> subtitleLang = new ArrayList<String>(Arrays.asList(subtitles));
				
			for (int i = 0; i < subtitleLang.size(); i++) {
				String subLang = subtitleLang.get(i);
				String query2 = "insert into DVD_subtitle (resourceID, subtitle)" + "values('" + resourceId + "','" + subLang
						+ "');";
				sql.set(query2);
			}
		}
	}
	
	/**
	 * This method allows the librarian to edit a laptop.
	 * @param resourceId The ID of the laptop.
	 * @param title The title of the laptop.
	 * @param year The year the laptop was produced.
	 * @param thumbNailImage Image of the laptop.
	 * @param numAvaCopies Number of copies available to borrow from the library.
	 * @param manufacturer The manufacturer of the laptop.
	 * @param model The model of the laptop.
	 * @param operatingSystem The operating system running on the laptop.
	 * @throws SQLException
	 */
	public void editLaptop (int resourceId, String title, String year, String image, int numAvCopies, int duration,
			String manufacturer, String model, String operatingSystem) throws SQLException {
		
		editResource(resourceId, title, year, image, numAvCopies, duration);
		
		//Edit the manufacturer.
		String newManufacturer = "null";
		if (!manufacturer.isEmpty()) {
			newManufacturer = "UPDATE DVD SET manufacturer = '" + manufacturer + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newManufacturer);
		}
		
		//Edit the model.
		String newModel = "null";
		if (!model.isEmpty()) {
			newModel = "UPDATE DVD SET model = '" + model + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newModel);
		}
		
		//Edit the operating system.
		String newOperatingSystem = "null";
		if (!operatingSystem.isEmpty()) {
			newOperatingSystem = "UPDATE DVD SET model = '" + operatingSystem + "' WHERE resourceID = '" + resourceId + "';";
			sql.set(newOperatingSystem);
		}
	}
	

	/**
	 * This method allows a user to borrow a resource through the Librarian.
	 * 
	 * @param resourceId The ID of the resource being borrowed.
	 * @param userName The name of the user borrowing the resource.
	 * @throws Exception
	 */
	public void borrow(int resourceId, String username) throws Exception {
		NormalUser user = new NormalUser(username);
		SQLHandle s = new SQLHandle();
		user.borrow(resourceId);
		System.out.println("Successfully Borrowed Resource.");
	}
	

	/**
	 * This method allows a NormalUser to return a Resource through the librarian
	 *  and calculates the fine if the resource is overdue.
	 * 
	 * @param borrowNo The identifier of the borrowing event.
	 */
	public void returnResource(int borrowNo) throws Exception {

		// SQL statement to find the username who borrowed the resource.
		String statement = "";
		statement = "Select distinct username from current_borrowing where borrowingID = '" + borrowNo + "';";
		ResultSet r = sql.nonStaticGet(statement);
		String userName = "";
		while (r.next()) {
			userName = r.getString("username");
		}

		// SQL statement to find the resourceID of the borrowed resource.
		String resourceID = "";
		statement = "Select resourceID from current_borrow_his where borrowingID = '" + borrowNo + "';";
		r = sql.nonStaticGet(statement);
		while (r.next()) {
			resourceID = r.getString("resourceID");
		}
		Resource resource = new Resource(Integer.parseInt(resourceID));
		NormalUser u = new NormalUser(userName);
		Borrowing b = new Borrowing(borrowNo);
		b.setReturnDate();

		// If the resource is overdue, it reduces the user's balance by the calculated amount.
		if (b.isOverdue()) {
			u.reduceBalance(b.fine());
			statement = "update normal_user set balance = '" + u.getBalance() + "' where username = '" + userName
					+ "';";
			sql.set(statement);

			statement = "select max(transID) from transaction;";
			r = sql.nonStaticGet(statement);
			int i = 0;
			// Finds the max transID and increases it by one to ensure unique transaction ID.
			while (r.next()) {
				i = r.getInt("max(transID)") + 1;
			}

			// Adds the fine information into the transaction table, the overdue_transaction
			// table and the transaction_his table.
			statement = "INSERT INTO `transaction`(`transID`, `amount`, `date`) VALUES ('"+i+"','"+b.fine()+"','"+getCurrentTime()+"')";
			sql.set(statement);

			statement = "insert into overdue_transaction values('" + i + "','" + b.getBorrowNo() + "');";
			sql.set(statement);
			statement = "insert into transaction_his values('" + u.getUsename() + "','" + i + "');";
			sql.set(statement);

			System.out.println("Fine is reduced from balance which is " + b.fine());
		}

		// Reserve the resource for the next user in the resource's requesting queue.
		resource.reserve();

		// Update the database, remove the user from the current_borrow_his table.
		statement = "delete from current_borrow_his where borrowingID = '" + borrowNo + "';";
		sql.set(statement);
		// removing the user from the current_borrowing table
		statement = "delete from current_borrowing where borrowingID = '" + borrowNo + "' and userName = '" + userName
				+ "';";

		sql.set(statement);
		// Add the user to the returned_his table
		statement = "insert into returned_his values('" + u.getUsername() + "','" + b.getBorrowNo() + "');";
		sql.set(statement);
		//Update borrowing table, resource is not on loan anymore.

		statement = "UPDATE borrowing SET onLoan = 'n' , returnDate = '" + getCurrentDate() + "' WHERE borrowingID ="
				+ borrowNo + ";";
		sql.set(statement);
		System.out.println("Successfully returned Resource.");
	}

	/**
	 * Method to get the current date.
	 * @return The currect date.
	 */
	private String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(new Date());
	}

	/**
	 * Method to get the current time.
	 * @return The current time.
	 */
	private String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	/**
	 * This method allows a User to pay off their fine by interacting with a
	 * librarian.
	 * 
	 * @param amount The amount of the fine.
	 * @param username The name of the user paying the fine.
	 */	
	public void payFine(int amount, String username) throws Exception {
		NormalUser u = new NormalUser(username);
			int newBalance = u.getBalance() + amount;
			
			// SQL query to update the normal_user table with the new balance amount
			String statement = "Update normal_user set balance = " + newBalance + " where username = '"+username+"';";
			sql.set(statement);

			statement = "select count(*) from transaction;";
			ResultSet r = sql.nonStaticGet(statement);
			r.next();
			int sNo = r.getInt("count(*)") + 1;
			//SQL query to update the transaction table with ID, amount and date.
			statement = "INSERT INTO `transaction`(`transID`, `amount`, `date`) VALUES " + "('" + sNo + "','" + amount
					+ "','" + getCurrentTime() + "'); ";

			sql.set(statement);
			//SQL query to update transaction_his table with username and transID.

			statement = "INSERT INTO `transaction_his`(`username`, `transID`) VALUES ('" + username + "','" + sNo + "')";
			sql.set(statement);
			System.out.println("fine paid");
				
		
	}

	/**
	 * This method allows a user to request a resource.
	 * 
	 * @param resourceID The ID of the requested resource.
	 * @param username The user requesting the resource.
	 */
	public void request(int resourceID, String username) throws Exception {
		NormalUser u = new NormalUser(username);
		u.request(resourceID);
	}

	/**
	 * This method allows a librarian to create a new User. Hashcode is stored in the password field.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param mobileNo The mobile number of the user.
	 * @param address The address of the user.
	 * @param image An image of the user.
	 */
	private void newUser(String username, String password, String firstName, String lastName, int mobileNo,
			String address, int image) throws SQLException {

		checkName(username);
		//SQL statement to add new user to the database.
		String statement = "INSERT INTO user_(username, Password, firstname, lastname, mobileNo, address, image)"
				+ " VALUES ('" + username + "','" + password.hashCode() + "','" + firstName + "','" + lastName + "','"
				+ mobileNo + "','" + address + "','" + image + "');";
		sql.set(statement);

	}

	/**
	 * This method allows a librarian to create a new NormalUser.
	 * 
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param mobileNo The mobile number of the user.
	 * @param address The address of the user.
	 * @param image An image of the user. 
	 */
	public void newNormalUser(String username, String password, String firstname, String lastname, int mobileNo,
			String address, int image) throws SQLException {

		// SQL query to add the NormalUser to the database.
		newUser(username, password, firstname, lastname, mobileNo, address, image);
		String statement = "INSERT INTO normal_user(username, balance) VALUES ('" + username + "','" + 0 + "');";
		sql.set(statement);
		System.out.println("NormalUserAdded");
	}

	/**
	 * This method allows a librarian to create a new Librarian.
	 * 
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param mobileNo The mobile number of the user.
	 * @param address The address of the user.
	 * @param image An image of the user. 
	 * @param employmentDate The date the librarian started working.
	 */
	public void newLibrarian(String username, String password, String firstname, String lastname, int mobileNo,
			String address, int image, String employmentDate) throws SQLException {

		newUser(username, password, firstname, lastname, mobileNo, address, image);

		// Get new unique StaffNo by increasing maximum staffNo by 1, ensuring unique ID. 
		String statement = "select max(staffNo) from librarian;";
		ResultSet r = sql.nonStaticGet(statement);
		r.next();
		int sNo = r.getInt("max(staffNo)") + 1;

		// SQL query to add the new Librarian to the database.
		statement = "INSERT INTO librarian(username, employmentDate, staffNo) " + "VALUES ('" + username + "','"
				+ employmentDate + "'," + sNo + ")";
		sql.set(statement);
	}

	/**
	 * This method checks if the entered username is unique and has not been used
	 * before.
	 * 
	 * @param username
	 */
	private void checkName(String username) throws SQLException, IllegalArgumentException {
		String statement = "select username from user_ where username = '" + username + "';";
		ResultSet r = sql.nonStaticGet(statement);
		// If the result set is not empty, means that the username is already in use.

		if (r.next() == true) {
			throw new IllegalArgumentException("Name already being used");
		}
	}

	@Override
	public void store() throws SQLException {
		// TODO Auto-generated method stub

	}
}
