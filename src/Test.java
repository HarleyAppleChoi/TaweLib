
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		//should be in main method.
		SQLHandle sql = new SQLHandle();

		ResultSet result = null;
		ResultSet result2 = null;

		
		 try {
			Librarian l = new Librarian();
			
			result = SQLHandle.get("select * from resource");
			while(result.next()) {
				//System.out.println(result.getString("title"));
			}
			
			//l.borrow(1, "apple");
			
			//l.borrow(1, "apple");
			NormalUser u = new NormalUser("apple");
			Librarian b = new Librarian();
			
			b.addBook("Lord of ring", "1998", "x.jpg", 2, 13, "da vinci", "me", "horror", "2001", "french");
			b.addDvd("lotr", "1980", "s.jpg", 60, 300000, "james", "German", "56", "english, french");
			//System.out.println(b.getBorrowingHistory(1));
			
			
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
