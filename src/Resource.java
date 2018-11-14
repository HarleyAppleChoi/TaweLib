import java.util.LinkedList;

public class Resource{
	protected int ID;
	protected String title;
	protected int year;
	protected Image thumbNailImage;
	protected LinkedList<BorrowItem> whoBorrow;
	protected LinkedList<User> resourceRequesting;
	protected boolean canborrrow;
	protected int numAvailableCopies;
	protected LinkedList<User> reserved;
	
	
}
