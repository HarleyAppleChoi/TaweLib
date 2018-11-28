package user;

import java.util.LinkedList;

public class normalUser extend user {
    
	
	protected int balance;
	protected int[] transaction;
	protected ArrayList<TransactionHistory> transaction1;
	protected LinkedList<Resource> reserveRequestItm;
	protected LinkedList<Resource> reserved;
	
	public normalUser(String username, String firstName, String lastName, int mobileNo, Image userImage) {
		super()
	
	
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
}

}
