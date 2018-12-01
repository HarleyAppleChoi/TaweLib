
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;

import com.sun.prism.Image;

public class Resource implements Storable{
	protected final int ID;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected int numCopies;
	protected boolean canBorrow;
	protected int numAvailableCopies;
	protected LinkedList<Borrowing> currentBorrow =  new LinkedList<>();
	protected LinkedList<String> request =  new LinkedList<>();
	protected LinkedList<String> reserve =  new LinkedList<>();
	//sql statement
	private String statement;
	
	protected Resource(int ID, String title, int year, Image thumbNailImage
			, int numCopies, int numAvailableCopies) {
		this.ID = ID;
		this.title=title;
		this.year=year;
		this.thumbNailImage=thumbNailImage;
		this.numCopies=numCopies;
		//this.numAvailableCopies=numAvailableCopies;
	}


	public Resource(int resourceId) throws Exception {
		ID = resourceId;
		statement = "select * from resource where resourceID = '" +ID+ "';";
		ResultSet r = SQLHandle.get(statement);
		while (r.next()) {
			title=r.getString("title");
			year=r.getInt("_year");
			//please do a method to change string into image.
			//this.thumbNailImage=thumbNailImage = r.getString("thumbNailImage");
			this.numCopies=numCopies=r.getInt("numAvCopies");
			//this.numAvailableCopies=numAvailableCopies=;
		}
		
		try {
		statement = "select borrowingID from currentBorrowHis where resourceID = '" + resourceId + "';";
		r = SQLHandle
				.get(statement);
		while (r.next()) {
			Borrowing e = new Borrowing(r.getInt("borrowingID"));
			currentBorrow.add(e);
		}
		// requesting queue
		statement="select username from requested where resourceID = '" + ID + "';";
		r = SQLHandle.get(statement);
		while (r.next()) {
			request.add((r.getString("username")));
		}
		// reserved queue
		statement="select username from reserved where resourceID = '" + ID + "';";
		r = SQLHandle.get(statement);
		while (r.next()) {
			reserve.add((r.getString("username")));
		}
		}catch(SQLSyntaxErrorException e) {
			//it can be do nothing when Table 'cw230.resered_item' doesn't exist because sometime 
			//the user dont have any requesting or current borrow or request
		}
	}

	/**
	 * If someone reserved resources, that is not able to borrow.
	 * 
	 * @return
	 */
	private boolean canBorrow() {
		boolean b = true;
		if ((currentBorrow.size() + reserve.size()+request.size()) > numCopies) {
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
		}
		System.out.println("reserved for " + reserve.getLast());
	}

	/**
	 * 
	 * @param userName
	 * @return the Borrowing object of that specific borrow.
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
		statement="insert into current_borrow_his values (" 
				+ this.ID + ","+b.getBorrowNo() +");";
		SQLHandle.set(statement);
		System.out.println("Borrowing added");
		return b;
	}

	/**
	 * Find out if the user is already reserved the item
	 * 
	 * @param user
	 * @return Index of the reserve.-1 if the user do not reserve the item. 
	 */
	private int isReserved(String user) {
		int i = 0;
		for (i = 0; i <= reserve.size(); i++) {
			if (i==reserve.size()) {
				i=-1;
				break;
			}else if (reserve.get(i) == user) {
				break;
			}
		}
		return i;
	}

	public int getId() {
		return ID;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Image getThumbNailImage() {
		return thumbNailImage;
	}

	public void setThumbNailImage(Image thumbNailImage) {
		this.thumbNailImage = thumbNailImage;
	}

	public int getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	public int getNumAvailableCopies() {
		return numAvailableCopies;
	}

	public void setNumAvailableCopies(int numAvailableCopies) {
		this.numAvailableCopies = numAvailableCopies;
	}

	public static boolean resourceAvailable(boolean canBorrow, int id) {

		String query = "select numAvCopies from resource where id = '" + id + "';";
		if (query == "0") {
			canBorrow = false;
		} else {
			canBorrow = true;
		}
		return canBorrow;
		
	}
	
	public void isAvailable() {
		
	}

	@Override
	public void store() throws SQLException {
			
	}
}
