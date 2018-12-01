
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;
import com.sun.prism.Image;

/**
 * This class creates a Resource, checks if a resource is available to borrow,
 * allows a user to borrow a resource, allows a user to request a resource where
 * they cannot borrow it, adds an item to a users reserved queue when the
 * resource they requested becomes available,
 * 
 * 
 * @author Emily Studley
 * @modified by Hau Yi Choi
 * @version 2.1
 */
public class Resource implements Storable {
	protected final int ID;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected int numCopies;
	protected boolean canBorrow;
	protected int numAvailableCopies;
	protected LinkedList<Borrowing> currentBorrow = new LinkedList<>();
	protected LinkedList<String> request = new LinkedList<>();
	protected LinkedList<String> reserve = new LinkedList<>();
	// sql statement
	private String statement;

	/**
	 * Constructor to construct a Resource using these parameters.
	 * 
	 * @param ID
	 * @param title
	 * @param year
	 * @param thumbNailImage
	 * @param numCopies
	 * @param numAvailableCopies
	 */
	protected Resource(int ID, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies) {
		this.ID = ID;
		this.title = title;
		this.year = year;
		this.thumbNailImage = thumbNailImage;
		this.numCopies = numCopies;
		// this.numAvailableCopies=numAvailableCopies;
	}

	/**
	 * Selects the resourceId, title, year, image and numAvailableCopies. Checks if
	 * the user is currently borrowing the resource, has it in its resource
	 * requesting queue or has it in their reserved resources queue.
	 * 
	 * @param resourceId
	 * @throws Exception
	 */
	public Resource(int resourceId) throws Exception {
		ID = resourceId;
		statement = "select * from resource where resourceID = '" + ID + "';";
		ResultSet r = SQLHandle.get(statement);
		while (r.next()) {
			title = r.getString("title");
			year = r.getInt("_year");
			// please do a method to change string into image.
			// this.thumbNailImage=thumbNailImage = r.getString("thumbNailImage");
			this.numCopies = numCopies = r.getInt("numAvCopies");
			// this.numAvailableCopies=numAvailableCopies;
		}

		try {
			statement = "select borrowingID from currentBorrowHis where resourceID = '" + resourceId + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				Borrowing e = new Borrowing(r.getInt("borrowingID"));
				currentBorrow.add(e);
			}
			// requesting queue
			statement = "select username from requested where resourceID = '" + ID + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				request.add((r.getString("username")));
			}
			// reserved queue
			statement = "select username from reserved where resourceID = '" + ID + "';";
			r = SQLHandle.get(statement);
			while (r.next()) {
				reserve.add((r.getString("username")));
			}
		} catch (SQLSyntaxErrorException e) {
			// it can be do nothing when Table 'cw230.reserved_item' doesn't exist because
			// sometime
			// the user doesn't have any requesting or current borrow or request
			// This method can do nothing when the table cw
		}
	}

	/**
	 * If someone reserved resources, that is not able to borrow.
	 * 
	 * @return true if user can borrow a resource, false otherwise.
	 */
	private boolean canBorrow() {
		boolean b = true;
		if ((currentBorrow.size() + reserve.size() + request.size()) > numCopies) {
			b = false;
		}
		return b;
	}
	
	
	public void reserve() throws SQLException {
		if (request.isEmpty()) {
			System.out.println("the resource is available now");
		}else {
			reserve.add(request.getFirst());
			statement = "delete from request_item where resourceID = '"+ getId()+"'and username='"+request.removeFirst()+"';"
					+"insert into reserved_item values('"+ reserve.getLast()+"','"+ getId()+"';";
			SQLHandle.set(statement);
			System.out.println("reserved for " + reserve.getLast());
		}
		
	}

	/**
	 * Method to allow a user to borrow a Resource. if the user cannot borrow the
	 * resource, it throws an exception and outputs an error message. If the user
	 * has reserved this resource, then their username is removed from the reserved
	 * queue and they borrow the book.
	 * 
	 * @param userName
	 * @return The Borrowing object of that specific borrow.
	 * @throws IllegalArgumentException
	 * @throws SQLException
	 */
	public Borrowing borrow(String userName) throws IllegalArgumentException, SQLException {
		if (!canBorrow()) {
			throw new IllegalArgumentException("You cannot borrow. Try request it.");
		} else if (isReserved(userName) != -1) {
			reserve.remove(isReserved(userName));
		}
		Borrowing b = new Borrowing(String.valueOf(ID));
		currentBorrow.add(b);
		statement = "insert into current_borrow_his values (" + this.ID + "," + b.getBorrowNo() + ");";
		SQLHandle.set(statement);
		System.out.println("Borrowing added");
		return b;
	}

	/**
	 * This method finds if the user has already reserved this resource.
	 * 
	 * @param user
	 * @return Index of the reserve. -1 if the user do not reserve the item.
	 */
	private int isReserved(String user) {
		int i = 0;
		for (i = 0; i <= reserve.size(); i++) {
			if (i == reserve.size()) {
				i = -1;
				break;
			} else if (reserve.get(i) == user) {
				break;
			}
		}
		return i;
	}

	/**
	 * This method checks if a resource is available to borrow.
	 * 
	 * @param canBorrow
	 * @param id
	 * @return If the number of available copies is 0, then return false, true
	 *         otherwise.
	 */
	public static boolean resourceAvailable(boolean canBorrow, int id) {

		String query = "select numAvCopies from resource where id = '" + id + "';";
		if (query == "0") {
			canBorrow = false;
		} else {
			canBorrow = true;
		}
		return canBorrow;
	}

	/**
	 * @Override
	 */

	public void store() throws SQLException {

	}

	/**
	 * Get method to get the Id.
	 * 
	 * @return Id
	 */
	public int getId() {
		return ID;
	}

	/**
	 * Get method to get the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set method to set the title.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get method to get the year.
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set method to set the year.
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get method to get the thumbnail image.
	 * 
	 * @return thumbNailImage
	 */
	public Image getThumbNailImage() {
		return thumbNailImage;
	}

	/**
	 * Set method to set the thumbnail image.
	 * 
	 * @param thumbNailImage
	 */
	public void setThumbNailImage(Image thumbNailImage) {
		this.thumbNailImage = thumbNailImage;
	}

	/**
	 * Get method to get the number of copies.
	 * 
	 * @return numCopies
	 */
	public int getNumCopies() {
		return numCopies;
	}

	/**
	 * Set method to set the number of copies.
	 * 
	 * @param numCopies
	 */
	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	/**
	 * Get method to get the number of available copies.
	 * 
	 * @return
	 */
	public int getNumAvailableCopies() {
		return numAvailableCopies;
	}

	/**
	 * cSet method to set the number of available copies.
	 * 
	 * @param numAvailableCopies
	 */
	public void setNumAvailableCopies(int numAvailableCopies) {
		this.numAvailableCopies = numAvailableCopies;
	}


	
	public void isAvailable() {
		
	}
}


