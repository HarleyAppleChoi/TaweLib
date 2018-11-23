
import java.util.LinkedList;

public class Resource{
	protected int ID;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected LinkedList<BorrowItem> whoBorrow;
	protected LinkedList<User> resourceRequesting;
	protected boolean canBorrow;
	protected int numAvailableCopies;
	protected LinkedList<User> reserved;
	
	public int getID() {
		return ID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getnumCopies() {
		return numAvailableCopies;
	}
	
	public boolean getcanborrow() {
		return canBorrow;
	}
	
	
	
	
}
