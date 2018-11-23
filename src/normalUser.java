package user;

import java.util.LinkedList;
import java.awt.Image;
import java.util.ArrayList;

public class normalUser extends user {
	
	protected int balance;
	protected int [] transaction; 
	protected LinkedList <resouurse>reserveRequestItm;
	protected LinkedList <resouurse>reserved;
	protected ArrayList<transactionHistory> transaction1;
	
	
	

	
	public normalUser(String username, String firstName,  String lastName,  int mobileNo, Image userImage) {	
    	 super(username, firstName, lastName,  mobileNo, userImage);
	}
	
	public int getBalance() {
		return balance;
	}
    	 
   public void reserve(String resourse) {



	
	
	

}
