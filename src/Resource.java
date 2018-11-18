
public class Resource {
=======
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
	
>>>>>>> d80ec3625049c28db7b501c1539a120047abcb5d
	
}
