
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javafx.scene.image.Image;

public class NormalUser extends User implements Storable{
    
	
	protected int balance;
	protected int[] transaction;
	protected ArrayList<TransactionHistory> transaction;
	protected LinkedList<Integer> requesting;
	protected LinkedList<Integer> reserved;
	protected LinkedList<Borrowing> currentBorrowHistory;
	
	public NormalUser(String username) {
		super.username=username;
		ResultSet r = SQLHandle.get("select * from normal_user where username = '" + username + "';");
		r.getString(username);
		balance = r.getInt("balance");
		
		r = SQLHandle.get("select borrowingID from current_borrowing where username = '" + username + "';");
		while(r.next()) {
			Borrowing b = new Borrowing(r.getInt("borrowingID"));
			currentBorrowHistory.add(b);
		}
		r = SQLHandle.get("select * from resered_item where username = '" + username + "';");
		while(r.next()) {
			reserved.add(r.getInt("ResourceID"));
		}
		r=SQLHandle.get("select * from requesting  where username = '" + username + "';");
		while(r.next()) {
			requesting.add(r.getInt("ResourceID"));
		}
	}
	
	private boolean canBorrow() {
		boolean b = true;
		if (balance < 0) {
			b = false;
		}
		for(int i =0; i< currentBorrowHistory.size();i++) {
			if (currentBorrowHistory.get(i).isOverdue()) {
			b = false;
			break;
			}
		}
		return b;
	}
	
	public void borrow(int resourceID) throws IllegalArgumentException{
		Resource r = new Resource(resourceID);
		if (!canBorrow()) {
			throw new IllegalArgumentException("You cannot borrow either you get fine or have something overdue");
		}else {
			Borrowing b = r.borrow(super.username);
			currentBorrowHistory.add(b);
		}
		
		//storing
		SQLHandle.set("insert into current_borrowing values (" 
		+ this.username + ","+currentBorrowHistory.getLast().getBorrowNo() +");");
		SQLHandle.set("insert into ");
		
	}
	
	
	
	public void getUserinfo(String username, String firstName, 
		String lastName, int mobileNo, Image userImage) {
		super.username = username;
		super.firstName=firstName;
		super.lastName=lastName;
		super.mobileNo=mobileNo;
		super.userImage=userImage;
	}
		
	
	
	public void reserve(String resourse) {

	}

	public Book searchBook(String author) {
		for (Book b : books) {
			if (b.getAuthor().equals(author)) {
				return b;
			}

			return null;
		}
	}

	public void requestBook(Resource r, String title) {
	    

	}

	public void getBook() {

	}

	public void returnBook(Object bookList, Object book) {
		bookList.put(book, bookList.get(book) + 1);

	}
	private void storeBorrow() throws SQLException {
		//when borrowing something	
		//only one item can be borrow each time borrow method is called
		

	}
	
}

}
