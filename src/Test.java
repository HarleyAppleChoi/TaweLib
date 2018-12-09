
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
			//NormalUser u = new NormalUser("overdue");
			
			Librarian b = new Librarian();
			//b.newNormalUser("hau", "123", "choi", "choi", 1231, "afsda", "asdfad");
			//b.newLibrarian("library", "123", "lib", "lib", 3213, "lib", 2, "10-10-10");
			//b.newNormalUser("user", "123", "j", "h", 12, "qwe", 32);
			//b.addDvd("lord of ring", "2014", "56", 45, 25, "Emily", "english", "246", "french,german,spanish");
			//b.addLaptop("laptop", "1996", "o.jpg", 5, 34, "apple", "frea", "MacOS");
			
			
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
