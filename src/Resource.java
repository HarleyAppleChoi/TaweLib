
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.sun.prism.Image;

public class Resource{
	protected int id;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected int numCopies;
	protected boolean canBorrow;
	protected int numAvailableCopies;
	protected LinkedList<Borrowing> currentBorrow;
	protected LinkedList<String> request;
	protected LinkedList<String> reserve;
	
	public Resource(int resourceId) throws Exception {
		id = resourceId;
		ResultSet r = SQLHandle.get("select borrowingID from currentBorrowHis where resourceID = '" 
		+ resourceId + "';");
		while (r.next()) {
			Borrowing e = new Borrowing(r.getInt("borrowingID"));
			currentBorrow.add(e);
		}
		//requesting queue
		r = SQLHandle.get("select username from requested where resourceID = '" + id + "';");
		while (r.next()) {
			request.add((r.getString("username")));
		}
		//reserved queue
		r = SQLHandle.get("select username from reserved where resourceID = '" + id + "';");
		while (r.next()) {
			reserve.add((r.getString("username")));
		}
	}
	
	/**
	 * If someone reserved resources, that is not able to borrow.
	 * @return
	 */
	public boolean canBorrow() {
		boolean b = true;
		if (currentBorrow.size()+reserve.size() >= numCopies) {
			b = false;
		}
		return b;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		
		String query = "select numAvCopies from resource where id = '" +  id + "';";
		 if (query == "0") {
			 canBorrow = false;
		 } else {
			 canBorrow = true;
		 }
		return canBorrow; 
	}
}
