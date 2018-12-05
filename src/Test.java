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
			/*
			result = SQLHandle.get("select * from resource");
			while(result.next()) {
				System.out.println(result.getString("title"));
			}
			
			l.borrow(1, "apple");
			*/
			//l.borrow(1, "apple");
			l.payFine(10, "apple");
			String[] sub = new String[10]; 
			l.addDvd("Lord of ring", "afda", "asdfa", 123, 0, "adsf", "asdf", "asdf",sub);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
